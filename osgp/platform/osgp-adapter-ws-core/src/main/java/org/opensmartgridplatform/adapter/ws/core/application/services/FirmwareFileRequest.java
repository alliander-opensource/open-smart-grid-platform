/*
 * Copyright 2018 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.ws.core.application.services;

import lombok.Getter;

@Getter
public class FirmwareFileRequest {
  private final String identification;
  private final String description;
  private final String fileName;
  private final boolean pushToNewDevices;
  private final boolean active;
  private final byte[] imageIdentifier;

  public FirmwareFileRequest(
      final String identification,
      final String description,
      final String fileName,
      final boolean pushToNewDevices,
      final boolean active,
      final byte[] imageIdentifier) {
    this.identification = identification;
    this.description = description;
    this.fileName = fileName;
    this.pushToNewDevices = pushToNewDevices;
    this.active = active;
    this.imageIdentifier = imageIdentifier;
  }
}
