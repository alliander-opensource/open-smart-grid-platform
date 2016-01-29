/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.domain.core.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.alliander.osgp.domain.core.valueobjects.DeviceFunctionGroup;
import com.alliander.osgp.shared.domain.entities.AbstractEntity;

@Entity
public class DeviceAuthorization extends AbstractEntity {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 1468328289658974067L;

    @ManyToOne()
    @JoinColumn()
    @Cascade(value = { CascadeType.ALL })
    private Device device;

    @ManyToOne()
    @JoinColumn()
    private Organisation organisation;

    @Column()
    private DeviceFunctionGroup functionGroup;

    public DeviceAuthorization() {
        // Default constructor
    }

    public DeviceAuthorization(final Device device, final Organisation organisation,
            final DeviceFunctionGroup functionGroup) {
        this.device = device;
        this.organisation = organisation;
        this.functionGroup = functionGroup;
    }

    public Device getDevice() {
        return this.device;
    }

    public Organisation getOrganisation() {
        return this.organisation;
    }

    public DeviceFunctionGroup getFunctionGroup() {
        return this.functionGroup;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceAuthorization)) {
            return false;
        }
        final DeviceAuthorization authorization = (DeviceAuthorization) o;
        // Only comparing the device and organisation identifications (and not
        // the complete objects) to prevent stack overflow errors when comparing
        // devices (which contain device authorizations).
        final boolean isDeviceEqual = Objects.equals(this.device, authorization.device);
        final boolean isOrganisationEqual = Objects.equals(this.organisation, authorization.organisation);
        final boolean isDeviceFunctionGroupEqual = Objects.equals(this.functionGroup.name(),
                authorization.functionGroup.name());

        return isDeviceEqual && isOrganisationEqual && isDeviceFunctionGroupEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.device, this.organisation, this.functionGroup.name());
    }
}
