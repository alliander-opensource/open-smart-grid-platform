/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package org.osgp.adapter.protocol.dlms.infra.networking;

import java.net.InetSocketAddress;
import java.util.UUID;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.osgp.adapter.protocol.dlms.infra.messaging.OsgpRequestMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alliander.osgp.dlms.DlmsPushNotification;
import com.alliander.osgp.dto.valueobjects.DeviceFunction;
import com.alliander.osgp.dto.valueobjects.smartmetering.PushNotificationAlarm;
import com.alliander.osgp.dto.valueobjects.smartmetering.PushNotificationSms;
import com.alliander.osgp.shared.infra.jms.RequestMessage;

public class DlmsChannelHandlerServer extends DlmsChannelHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DlmsChannelHandlerServer.class);

    private static final String PUSH_ALARM_TRIGGER = "Push alarm monitor";
    private static final String PUSH_SMS_TRIGGER = "Push sms wakeup";

    @Autowired
    private OsgpRequestMessageSender osgpRequestMessageSender;

    public DlmsChannelHandlerServer() {
        super(LOGGER);
    }

    @Override
    public void messageReceived(final ChannelHandlerContext ctx, final MessageEvent e) throws Exception {

        final DlmsPushNotification message = (DlmsPushNotification) e.getMessage();

        final String correlationId = UUID.randomUUID().toString().replace("-", "");
        final String deviceIdentification = message.getEquipmentIdentifier();
        final String ipAddress = this.retrieveIpAddress(ctx, deviceIdentification);

        if (PUSH_SMS_TRIGGER.equals(message.getTriggerType())) {
            this.logMessage(message);

            final PushNotificationSms pushNotificationSms = new PushNotificationSms(deviceIdentification, ipAddress);

            final RequestMessage requestMessage = new RequestMessage(correlationId, "no-organisation",
                    deviceIdentification, ipAddress, pushNotificationSms);

            LOGGER.info("Sending push notification sms wakeup to OSGP with correlation ID: " + correlationId);
            this.osgpRequestMessageSender.send(requestMessage, DeviceFunction.PUSH_NOTIFICATION_SMS.name());

        } else if (PUSH_ALARM_TRIGGER.equals(message.getTriggerType())) {
            this.logMessage(message);

            final PushNotificationAlarm pushNotificationAlarm = new PushNotificationAlarm(deviceIdentification,
                    message.getAlarms());

            final RequestMessage requestMessage = new RequestMessage(correlationId, "no-organisation",
                    deviceIdentification, ipAddress, pushNotificationAlarm);

            LOGGER.info("Sending push notification alarm to OSGP with correlation ID: " + correlationId);
            this.osgpRequestMessageSender.send(requestMessage, DeviceFunction.PUSH_NOTIFICATION_ALARM.name());

        } else {
            LOGGER.info("Unknown received message, skip processing");
        }
    }

    private String retrieveIpAddress(final ChannelHandlerContext ctx, final String deviceIdentification) {
        String ipAddress = null;
        try {
            ipAddress = ((InetSocketAddress) ctx.getChannel().getRemoteAddress()).getHostString();
            LOGGER.info("Push notification for device {} received from IP address {}", deviceIdentification, ipAddress);
        } catch (final Exception ex) {
            LOGGER.info("Unable to determine IP address of the meter sending a push notification: ", ex);
        }
        return ipAddress;
    }
}
