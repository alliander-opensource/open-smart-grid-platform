/*
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.protocol.dlms.infra.messaging.processors;

import java.io.Serializable;
import org.opensmartgridplatform.adapter.protocol.dlms.application.services.InstallationService;
import org.opensmartgridplatform.adapter.protocol.dlms.domain.entities.DlmsDevice;
import org.opensmartgridplatform.adapter.protocol.dlms.infra.messaging.DeviceRequestMessageProcessor;
import org.opensmartgridplatform.dto.valueobjects.smartmetering.SmartMeteringDeviceDto;
import org.opensmartgridplatform.shared.exceptionhandling.OsgpException;
import org.opensmartgridplatform.shared.infra.jms.MessageMetadata;
import org.opensmartgridplatform.shared.infra.jms.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Class for processing add meter request messages */
@Component
public class AddMeterRequestMessageProcessor extends DeviceRequestMessageProcessor {

  @Autowired private InstallationService installationService;

  public AddMeterRequestMessageProcessor() {
    super(MessageType.ADD_METER);
  }

  @Override
  protected boolean usesDeviceConnection() {
    return false;
  }

  @Override
  protected Serializable handleMessage(
      final DlmsDevice device,
      final Serializable requestObject,
      final MessageMetadata messageMetadata)
      throws OsgpException {
    this.assertRequestObjectType(SmartMeteringDeviceDto.class, requestObject);

    final SmartMeteringDeviceDto smartMeteringDevice = (SmartMeteringDeviceDto) requestObject;
    this.installationService.addMeter(messageMetadata, smartMeteringDevice);

    // No return object.
    return null;
  }

  @Override
  protected boolean requiresExistingDevice() {
    return false;
  }
}
