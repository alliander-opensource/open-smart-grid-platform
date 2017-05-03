/**
 * Copyright 2017 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package org.osgpfoundation.osgp.dto.da;

import org.osgpfoundation.osgp.dto.da.iec61850.PhysicalDeviceDto;

import java.io.Serializable;

public class GetDeviceModelResponseDto implements Serializable {
    private static final long serialVersionUID = 4776483459295812759L;

    private final PhysicalDeviceDto physicalDevice;

    public GetDeviceModelResponseDto(final PhysicalDeviceDto physicalDevice) {
        this.physicalDevice = physicalDevice;
    }

    public PhysicalDeviceDto getPhysicalDevice() {
        return this.physicalDevice;
    }
}
