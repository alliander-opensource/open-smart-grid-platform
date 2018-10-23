/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.dto.valueobjects.smartmetering;

public enum AlarmTypeDto {

    CLOCK_INVALID,
    REPLACE_BATTERY,
    POWER_UP,
    PROGRAM_MEMORY_ERROR,
    RAM_ERROR,
    NV_MEMORY_ERROR,
    MEASUREMENT_SYSTEM_ERROR,
    WATCHDOG_ERROR,
    FRAUD_ATTEMPT,
    COMMUNICATION_ERROR_M_BUS_CHANNEL_1,
    COMMUNICATION_ERROR_M_BUS_CHANNEL_2,
    COMMUNICATION_ERROR_M_BUS_CHANNEL_3,
    COMMUNICATION_ERROR_M_BUS_CHANNEL_4,
    FRAUD_ATTEMPT_M_BUS_CHANNEL_1,
    FRAUD_ATTEMPT_M_BUS_CHANNEL_2,
    FRAUD_ATTEMPT_M_BUS_CHANNEL_3,
    FRAUD_ATTEMPT_M_BUS_CHANNEL_4,
    NEW_M_BUS_DEVICE_DISCOVERED_CHANNEL_1,
    NEW_M_BUS_DEVICE_DISCOVERED_CHANNEL_2,
    NEW_M_BUS_DEVICE_DISCOVERED_CHANNEL_3,
    NEW_M_BUS_DEVICE_DISCOVERED_CHANNEL_4;

}
