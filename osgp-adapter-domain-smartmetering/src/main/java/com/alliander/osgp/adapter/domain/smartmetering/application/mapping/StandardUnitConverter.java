/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.adapter.domain.smartmetering.application.mapping;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alliander.osgp.domain.core.valueobjects.smartmetering.OsgpMeterValue;
import com.alliander.osgp.domain.core.valueobjects.smartmetering.OsgpUnit;
import com.alliander.osgp.dto.valueobjects.smartmetering.DlmsMeterValue;
import com.alliander.osgp.dto.valueobjects.smartmetering.DlmsUnit;

/**
 * Calculate a meter value:
 *
 * <pre>
 * - determine the multiplier for the DlmsUnit
 * - apply the multiplier
 * - determine the standardized unit
 * </pre>
 *
 */
@Component(value = "standardUnitConverter")
public class StandardUnitConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandardUnitConverter.class);

    /**
     * Return a meterValue in standardized unit. When the argument value is
     * null, null is returned.
     *
     * @param meterValue
     * @param dlmsMeterValue
     * @return
     */
    public OsgpMeterValue calculateStandardizedValue(final DlmsMeterValue dlmsMeterValue) {
        if (dlmsMeterValue == null) {
            return null;
        }
        final BigDecimal multiplier = this.getMultiplierToOsgpUnit(dlmsMeterValue.getDlmsUnit(),
                this.toStandardUnit(dlmsMeterValue.getDlmsUnit()));
        final BigDecimal calculated = dlmsMeterValue.getValue().multiply(multiplier);
        LOGGER.debug(String.format("calculated %s from %s", calculated, dlmsMeterValue));
        return new OsgpMeterValue(calculated, this.toStandardUnit(dlmsMeterValue.getDlmsUnit()));
    }

    /**
     * return the multiplier to get from a dlms unit to a osgp unit
     *
     * @throws IllegalArgumentException
     *             when no multiplier is found
     * @param dlmsUnit
     * @return
     */
    public BigDecimal getMultiplierToOsgpUnit(final DlmsUnit dlmsUnit, final OsgpUnit osgpUnit) {
        switch (dlmsUnit) {
        case WH:
            switch (osgpUnit) {
            case KWH:
                return BigDecimal.valueOf(0.001d);
            default:
                break;
            }
        case M3:
        case M3COR:
        case UNDEFINED:
            switch (osgpUnit) {
            case M3:
            case UNDEFINED:
                return BigDecimal.valueOf(1d);
            default:
                break;
            }
        }
        throw new IllegalArgumentException(String.format("calculating %s from %s not supported yet", osgpUnit.name(),
                dlmsUnit.name()));
    }

    /**
     * return the osgp unit that corresponds to a dlms unit
     *
     * @throws IllegalArgumentException
     *             when no osgp unit is found
     */
    public OsgpUnit toStandardUnit(final DlmsUnit dlmsUnit) {
        switch (dlmsUnit) {
        case WH:
            return OsgpUnit.KWH;
        case M3:
        case M3COR:
            return OsgpUnit.M3;
        case UNDEFINED:
            return OsgpUnit.UNDEFINED;
        default:
            throw new IllegalArgumentException(String.format("dlms unit %s not supported yet", dlmsUnit.name()));
        }
    }

}
