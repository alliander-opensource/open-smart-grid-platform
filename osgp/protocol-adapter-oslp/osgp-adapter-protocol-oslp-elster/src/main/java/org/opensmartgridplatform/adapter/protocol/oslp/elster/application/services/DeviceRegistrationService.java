// SPDX-FileCopyrightText: 2023 Contributors to the GXF project
// SPDX-FileCopyrightText: Copyright Contributors to the GXF project
//
// SPDX-License-Identifier: Apache-2.0

package org.opensmartgridplatform.adapter.protocol.oslp.elster.application.services;

import java.net.InetAddress;
import org.apache.commons.codec.binary.Base64;
import org.opensmartgridplatform.adapter.protocol.oslp.elster.application.services.oslp.OslpDeviceSettingsService;
import org.opensmartgridplatform.adapter.protocol.oslp.elster.domain.entities.OslpDevice;
import org.opensmartgridplatform.adapter.protocol.oslp.elster.exceptions.ProtocolAdapterException;
import org.opensmartgridplatform.adapter.protocol.oslp.elster.infra.messaging.OsgpRequestMessageSender;
import org.opensmartgridplatform.dto.valueobjects.DeviceRegistrationDataDto;
import org.opensmartgridplatform.shared.infra.jms.MessageType;
import org.opensmartgridplatform.shared.infra.jms.RequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "oslpDeviceRegistrationService")
@Transactional(value = "transactionManager")
public class DeviceRegistrationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DeviceRegistrationService.class);

  private static final String NO_CORRELATION_UID = "no-correlationUid";

  private static final String NO_ORGANISATION = "no-organisation";

  @Autowired private Integer sequenceNumberWindow;

  @Autowired private Integer sequenceNumberMaximum;

  @Autowired private OslpDeviceSettingsService oslpDeviceSettingsService;

  @Autowired private OsgpRequestMessageSender osgpRequestMessageSender;

  /** Constructor */
  public DeviceRegistrationService() {
    // Parameterless constructor required for transactions...
  }

  public void setSequenceNumberWindow(final Integer sequenceNumberWindow) {
    this.sequenceNumberWindow = sequenceNumberWindow;
  }

  public void setSequenceNumberMaximum(final Integer sequenceNumberMaximum) {
    this.sequenceNumberMaximum = sequenceNumberMaximum;
  }

  public OslpDevice findDevice(final byte[] deviceId) throws ProtocolAdapterException {

    // Convert byte array to String.
    final String deviceUid = Base64.encodeBase64String(deviceId);

    final OslpDevice oslpDevice = this.oslpDeviceSettingsService.getDeviceByUid(deviceUid);
    if (oslpDevice == null) {
      throw new ProtocolAdapterException("Unable to find device using deviceUid: " + deviceUid);
    }

    return oslpDevice;
  }

  public void sendDeviceRegisterRequest(
      final InetAddress inetAddress,
      final String deviceType,
      final boolean hasSchedule,
      final String deviceIdentification) {

    final DeviceRegistrationDataDto deviceRegistrationData =
        new DeviceRegistrationDataDto(inetAddress.getHostAddress(), deviceType, hasSchedule);

    final RequestMessage requestMessage =
        new RequestMessage(
            NO_CORRELATION_UID, NO_ORGANISATION, deviceIdentification, deviceRegistrationData);

    this.osgpRequestMessageSender.send(requestMessage, MessageType.REGISTER_DEVICE.name());
  }

  public void confirmRegisterDevice(
      final byte[] deviceId,
      final Integer newSequenceNumber,
      final Integer randomDevice,
      final Integer randomPlatform)
      throws ProtocolAdapterException {

    this.checkDeviceRandomAndPlatformRandom(deviceId, randomDevice, randomPlatform);
    this.updateDeviceSequenceNumber(deviceId, newSequenceNumber);

    final OslpDevice oslpDevice = this.findDevice(deviceId);

    final RequestMessage requestMessage =
        new RequestMessage(
            NO_CORRELATION_UID, NO_ORGANISATION, oslpDevice.getDeviceIdentification());

    this.osgpRequestMessageSender.send(
        requestMessage, MessageType.DEVICE_REGISTRATION_COMPLETED.name());

    LOGGER.debug("confirmRegisterDevice successful for device with UID: {}.", deviceId);
  }

  private void checkDeviceRandomAndPlatformRandom(
      final byte[] deviceId, final Integer randomDevice, final Integer randomPlatform)
      throws ProtocolAdapterException {
    // Lookup device.
    final OslpDevice oslpDevice = this.findDevice(deviceId);
    // Check the random number generated by the device.
    if (randomDevice == null || oslpDevice.getRandomDevice() == null) {
      throw new ProtocolAdapterException("RandomDevice not set");
    }
    if (oslpDevice.getRandomDevice() - randomDevice != 0) {
      throw new ProtocolAdapterException("RandomDevice incorrect");
    }
    // Check the random number generated by the platform.
    if (randomPlatform == null || oslpDevice.getRandomPlatform() == null) {
      throw new ProtocolAdapterException("RandomPlatform not set");
    }
    if (oslpDevice.getRandomPlatform() - randomPlatform != 0) {
      throw new ProtocolAdapterException("RandomPlatform incorrect");
    }
  }

  public void updateDeviceSequenceNumber(final byte[] deviceId, final int newSequenceNumber)
      throws ProtocolAdapterException {

    // Lookup device.
    final OslpDevice oslpDevice = this.findDevice(deviceId);

    this.checkSequenceNumber(oslpDevice.getSequenceNumber(), newSequenceNumber);

    // Persist the new sequence number.
    oslpDevice.setSequenceNumber(newSequenceNumber);
    this.oslpDeviceSettingsService.updateDevice(oslpDevice);
  }

  public void checkSequenceNumber(final byte[] deviceId, final Integer newSequenceNumber)
      throws ProtocolAdapterException {

    // Lookup device.
    final OslpDevice oslpDevice = this.findDevice(deviceId);

    this.checkSequenceNumber(oslpDevice.getSequenceNumber(), newSequenceNumber);
  }

  public void checkSequenceNumber(
      final Integer currentSequenceNumber, final Integer newSequenceNumber)
      throws ProtocolAdapterException {

    int expectedSequenceNumber = currentSequenceNumber + 1;
    if (expectedSequenceNumber > this.sequenceNumberMaximum) {
      expectedSequenceNumber = 0;
    }

    if (Math.abs(expectedSequenceNumber - newSequenceNumber) <= this.sequenceNumberWindow
        || Math.abs(expectedSequenceNumber - newSequenceNumber)
            > this.sequenceNumberMaximum - this.sequenceNumberWindow) {
      LOGGER.debug("SequenceNumber OK");
    } else {
      LOGGER.debug("SequenceNumber NOT OK");
      throw new ProtocolAdapterException("SequenceNumber incorrect");
    }
  }
}
