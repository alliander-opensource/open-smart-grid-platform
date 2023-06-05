// SPDX-FileCopyrightText: 2023 Contributors to the GXF project
// SPDX-FileCopyrightText: Copyright Contributors to the GXF project
//
// SPDX-License-Identifier: Apache-2.0

package org.opensmartgridplatform.adapter.protocol.iec61850.device.ssld.responses;

import org.opensmartgridplatform.adapter.protocol.iec61850.device.DeviceMessageStatus;
import org.opensmartgridplatform.adapter.protocol.iec61850.device.DeviceRequest;
import org.opensmartgridplatform.dto.valueobjects.ConfigurationDto;

public class GetConfigurationDeviceResponse extends EmptyDeviceResponse {

  ConfigurationDto configuration;

  public GetConfigurationDeviceResponse(
      final DeviceRequest deviceRequest,
      final DeviceMessageStatus status,
      final ConfigurationDto configuration) {
    super(
        deviceRequest.getOrganisationIdentification(),
        deviceRequest.getDeviceIdentification(),
        deviceRequest.getCorrelationUid(),
        deviceRequest.getMessagePriority(),
        status);
    this.configuration = configuration;
  }

  public ConfigurationDto getConfiguration() {
    return this.configuration;
  }
}
