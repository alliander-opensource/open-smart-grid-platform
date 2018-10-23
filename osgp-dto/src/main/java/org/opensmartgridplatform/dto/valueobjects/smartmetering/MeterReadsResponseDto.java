/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.dto.valueobjects.smartmetering;

import java.util.Date;

public class MeterReadsResponseDto extends ActionResponseDto {
    private static final long serialVersionUID = -297320204916085999L;

    private final Date logTime;

    private final ActiveEnergyValuesDto activeEnergyValues;

    public MeterReadsResponseDto(final Date logTime, final ActiveEnergyValuesDto activeEnergyValues) {
        this.logTime = new Date(logTime.getTime());
        this.activeEnergyValues = activeEnergyValues;
    }

    public Date getLogTime() {
        return new Date(this.logTime.getTime());
    }

    public DlmsMeterValueDto getActiveEnergyImportTariffOne() {
        return this.activeEnergyValues.getActiveEnergyImportTariffOne();
    }

    public DlmsMeterValueDto getActiveEnergyImportTariffTwo() {
        return this.activeEnergyValues.getActiveEnergyImportTariffTwo();
    }

    public DlmsMeterValueDto getActiveEnergyExportTariffOne() {
        return this.activeEnergyValues.getActiveEnergyExportTariffOne();
    }

    public DlmsMeterValueDto getActiveEnergyExportTariffTwo() {
        return this.activeEnergyValues.getActiveEnergyExportTariffTwo();
    }

    public DlmsMeterValueDto getActiveEnergyImport() {
        return this.activeEnergyValues.getActiveEnergyImport();
    }

    public DlmsMeterValueDto getActiveEnergyExport() {
        return this.activeEnergyValues.getActiveEnergyExport();
    }

    public ActiveEnergyValuesDto getActiveEnergyValues() {
        return this.activeEnergyValues;
    }

    @Override
    public String toString() {
        return "MeterReads[logTime=" + this.logTime + ", " + this.activeEnergyValues + ", activeEnergyExport="
                + this.getActiveEnergyExport() + ", activeEnergyImportTariffOne="
                + this.getActiveEnergyImportTariffOne() + ", activeEnergyImportTariffTwo="
                + this.getActiveEnergyImportTariffTwo() + ", activeEnergyExportTariffOne="
                + this.getActiveEnergyExportTariffOne() + ", activeEnergyExportTariffTwo="
                + this.getActiveEnergyExportTariffTwo() + "]";
    }

}
