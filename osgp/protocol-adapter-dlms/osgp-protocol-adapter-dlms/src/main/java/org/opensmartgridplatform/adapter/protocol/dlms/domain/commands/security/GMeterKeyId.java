/*
 * Copyright 2022 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.protocol.dlms.domain.commands.security;

import java.util.Arrays;
import org.opensmartgridplatform.adapter.protocol.dlms.domain.entities.SecurityKeyType;

public enum GMeterKeyId {
  P2_USER_KEY(0, SecurityKeyType.G_METER_ENCRYPTION),
  FIRMWARE_UPDATE_AUTHENTICATION_KEY(1, SecurityKeyType.G_METER_FIRMWARE_UPDATE_AUTHENTICATION),
  P0_ACTIVATION_KEY(2, SecurityKeyType.G_METER_OPTICAL_PORT_KEY),
  RESERVED(3, null);

  private final int keyId;
  private final SecurityKeyType securityKeyType;

  GMeterKeyId(final int keyId, final SecurityKeyType securityKeyType) {
    this.keyId = keyId;
    this.securityKeyType = securityKeyType;
  }

  public int getKeyId() {
    return this.keyId;
  }

  public static GMeterKeyId fromSecurityKeyType(final SecurityKeyType securityKeyType) {
    return Arrays.stream(GMeterKeyId.values())
        .filter(keyId -> keyId.securityKeyType.equals(securityKeyType))
        .findFirst()
        .orElseThrow(
            () ->
                new IllegalArgumentException(
                    "Could not get key id from security key type " + securityKeyType));
  }
}
