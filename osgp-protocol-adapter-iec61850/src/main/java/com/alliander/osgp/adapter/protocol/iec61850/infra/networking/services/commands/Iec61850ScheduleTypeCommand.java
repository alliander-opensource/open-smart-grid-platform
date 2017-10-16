/**
 * Copyright 2016 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.adapter.protocol.iec61850.infra.networking.services.commands;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openmuc.openiec61850.Fc;

import com.alliander.osgp.adapter.protocol.iec61850.device.rtu.RtuReadCommand;
import com.alliander.osgp.adapter.protocol.iec61850.device.rtu.RtuWriteCommand;
import com.alliander.osgp.adapter.protocol.iec61850.exceptions.NodeException;
import com.alliander.osgp.adapter.protocol.iec61850.exceptions.NodeNotFoundException;
import com.alliander.osgp.adapter.protocol.iec61850.exceptions.NodeWriteException;
import com.alliander.osgp.adapter.protocol.iec61850.infra.networking.Iec61850Client;
import com.alliander.osgp.adapter.protocol.iec61850.infra.networking.helper.DataAttribute;
import com.alliander.osgp.adapter.protocol.iec61850.infra.networking.helper.DeviceConnection;
import com.alliander.osgp.adapter.protocol.iec61850.infra.networking.helper.LogicalDevice;
import com.alliander.osgp.adapter.protocol.iec61850.infra.networking.helper.LogicalNode;
import com.alliander.osgp.adapter.protocol.iec61850.infra.networking.helper.NodeContainer;
import com.alliander.osgp.adapter.protocol.iec61850.infra.networking.helper.SubDataAttribute;
import com.alliander.osgp.dto.valueobjects.microgrids.MeasurementDto;
import com.alliander.osgp.dto.valueobjects.microgrids.SetPointDto;

public class Iec61850ScheduleTypeCommand implements RtuReadCommand<MeasurementDto>, RtuWriteCommand<SetPointDto> {

    private static final String NODE_NAME = "DSCH";
    private static final DataAttribute DATA_ATTRIBUTE = DataAttribute.SCHEDULE_TYPE;
    private static final SubDataAttribute SUB_DATA_ATTRIBUTE = SubDataAttribute.SETPOINT_VALUE;
    private static final Fc FC = Fc.SP;

    private final LogicalNode logicalNode;
    private final int index;

    public Iec61850ScheduleTypeCommand(final int index) {
        this.index = index;
        this.logicalNode = LogicalNode.fromString(NODE_NAME + index);
    }

    @Override
    public MeasurementDto execute(final Iec61850Client client, final DeviceConnection connection,
            final LogicalDevice logicalDevice, final int logicalDeviceIndex)
            throws NodeNotFoundException, NodeException {
        final NodeContainer containingNode = connection.getFcModelNode(logicalDevice, logicalDeviceIndex,
                this.logicalNode, DATA_ATTRIBUTE, FC);
        client.readNodeDataValues(connection.getConnection().getClientAssociation(), containingNode.getFcmodelNode());
        return this.translate(containingNode);
    }

    @Override
    public MeasurementDto translate(final NodeContainer containingNode) {
        return new MeasurementDto(this.index, DATA_ATTRIBUTE.getDescription(), 0, new DateTime(DateTimeZone.UTC),
                containingNode.getInteger(SUB_DATA_ATTRIBUTE).getValue());
    }

    @Override
    public void executeWrite(final Iec61850Client client, final DeviceConnection connection,
            final LogicalDevice logicalDevice, final int logicalDeviceIndex, final SetPointDto setPoint)
            throws NodeException {

        final int value = this.checkValue(setPoint.getValue());

        final NodeContainer containingNode = connection.getFcModelNode(logicalDevice, logicalDeviceIndex,
                this.logicalNode, DATA_ATTRIBUTE, FC);
        containingNode.writeInteger(SUB_DATA_ATTRIBUTE, value);
    }

    private int checkValue(final double value) throws NodeWriteException {
        int result;
        try {
            result = (int) value;
        } catch (final ClassCastException e) {
            throw new NodeWriteException(String.format("Invalid value %f.", value), e);
        }
        return result;
    }

}
