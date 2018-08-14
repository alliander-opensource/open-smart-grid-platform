/**
 * Copyright 2018 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.ws.microgrids.application.services;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.opensmartgridplatform.adapter.ws.domain.entities.ResponseData;
import org.opensmartgridplatform.adapter.ws.schema.microgrids.notification.NotificationType;
import org.opensmartgridplatform.adapter.ws.shared.services.AbstractResendNotificationService;
import org.opensmartgridplatform.adapter.ws.shared.services.NotificationService;

@Service(value = "resendNotificationServiceMicrogrids")
@Transactional(value = "transactionManager")
public class ResendNotificationService extends AbstractResendNotificationService {

    @Autowired
    private NotificationService microgridsNotificationService;

    @Override
    public void resendNotification(final ResponseData responseData) {

        if (!EnumUtils.isValidEnum(NotificationType.class, responseData.getMessageType())) {
            this.logUnknownNotificationTypeError(responseData.getCorrelationUid(), responseData.getMessageType(),
                    this.microgridsNotificationService.getClass().getName());
            return;
        }

        final NotificationType notificationType = NotificationType.valueOf(responseData.getMessageType());
        this.microgridsNotificationService.sendNotification(responseData.getOrganisationIdentification(), "ZownStream",
                responseData.getDeviceIdentification(), responseData.getResultType().name(),
                responseData.getCorrelationUid(), this.getNotificationMessage(responseData.getMessageType()),
                notificationType);
    }
}
