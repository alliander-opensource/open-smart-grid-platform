/*
 * Copyright 2020 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.domain.da.application.routing;

import org.opensmartgridplatform.adapter.domain.da.infra.jms.kafka.KafkaResponseMessageSender;
import org.opensmartgridplatform.adapter.domain.da.infra.jms.ws.WebServiceResponseMessageSender;
import org.opensmartgridplatform.domain.core.entities.Device;
import org.opensmartgridplatform.domain.core.services.DeviceDomainService;
import org.opensmartgridplatform.domain.core.valueobjects.IntegrationType;
import org.opensmartgridplatform.shared.exceptionhandling.FunctionalException;
import org.opensmartgridplatform.shared.exceptionhandling.FunctionalExceptionType;
import org.opensmartgridplatform.shared.infra.jms.NotificationResponseMessageSender;
import org.opensmartgridplatform.shared.infra.jms.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
 * Handles the routing of the response message. It sends it to the web service adapter, the kafka adapter or both.
 */
@Component(value = "domainDistributionAutomationOutboundResponseMessageRouter")
public class ResponseMessageRouter implements NotificationResponseMessageSender {

  private static final Logger LOGGER = LoggerFactory.getLogger(ResponseMessageRouter.class);

  @Autowired private DeviceDomainService deviceDomainService;

  @Autowired
  @Qualifier(value = "domainDistributionAutomationOutboundWebServiceResponsesMessageSender")
  private WebServiceResponseMessageSender webServiceResponseMessageSender;

  @Autowired
  @Qualifier(value = "domainDistributionAutomationOutboundKafkaResponsesMessageSender")
  private KafkaResponseMessageSender kafkaResponseMessageSender;

  @Override
  public void send(final ResponseMessage responseMessage, final String messageType) {
    final IntegrationType integrationType =
        this.getIntegrationType(responseMessage.getDeviceIdentification());
    this.send(responseMessage, messageType, integrationType);
  }

  public void send(
      final ResponseMessage responseMessage,
      final String messageType,
      final IntegrationType integrationType) {

    switch (integrationType) {
      case BOTH:
        this.kafkaResponseMessageSender.send(responseMessage, messageType);
        this.webServiceResponseMessageSender.send(responseMessage, messageType);
        break;
      case KAFKA:
        this.kafkaResponseMessageSender.send(responseMessage, messageType);
        break;
      default:
        this.webServiceResponseMessageSender.send(responseMessage, messageType);
    }
  }

  private IntegrationType getIntegrationType(final String deviceIdentification) {

    try {
      final Device device = this.deviceDomainService.searchDevice(deviceIdentification);
      return device.getIntegrationType();
    } catch (final FunctionalException e) {
      if (e.getExceptionType() == FunctionalExceptionType.UNKNOWN_DEVICE) {
        return this.integrationTypeForUnknownDevice(deviceIdentification);
      } else {
        return this.integrationTypeForUnexpectedException(e);
      }
    } catch (final Exception e) {
      return this.integrationTypeForUnexpectedException(e);
    }
  }

  private IntegrationType integrationTypeForUnknownDevice(final String deviceIdentification) {
    LOGGER.info(
        "Unable to determine integration type for unknown device {};"
            + " we are using the value KAFKA as this is used in flows where device data is not registered.",
        deviceIdentification);
    return IntegrationType.KAFKA;
  }

  private IntegrationType integrationTypeForUnexpectedException(
      final Exception unexpectedException) {
    LOGGER.error(
        "Could not determine integration type based on the device; we are using the default value WEB_SERVICE",
        unexpectedException);
    return IntegrationType.WEB_SERVICE;
  }
}
