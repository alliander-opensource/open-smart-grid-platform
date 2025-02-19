/*
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.domain.core.application.config;

import org.opensmartgridplatform.adapter.domain.core.application.config.messaging.InboundOsgpCoreRequestsMessagingConfig;
import org.opensmartgridplatform.adapter.domain.core.application.config.messaging.InboundOsgpCoreResponsesMessagingConfig;
import org.opensmartgridplatform.adapter.domain.core.application.config.messaging.InboundWebServiceRequestsMessagingConfig;
import org.opensmartgridplatform.adapter.domain.core.application.config.messaging.OutboundOsgpCoreRequestsMessagingConfig;
import org.opensmartgridplatform.adapter.domain.core.application.config.messaging.OutboundOsgpCoreResponsesMessagingConfig;
import org.opensmartgridplatform.adapter.domain.core.application.config.messaging.OutboundWebServiceResponsesMessagingConfig;
import org.opensmartgridplatform.shared.application.config.AbstractConfig;
import org.opensmartgridplatform.shared.application.config.messaging.DefaultJmsConfiguration;
import org.opensmartgridplatform.shared.infra.jms.JmsMessageCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:osgp-adapter-domain-core.properties")
@PropertySource(value = "file:${osgp/Global/config}", ignoreResourceNotFound = true)
@PropertySource(value = "file:${osgp/AdapterDomainCore/config}", ignoreResourceNotFound = true)
@Import(
    value = {
      InboundOsgpCoreRequestsMessagingConfig.class,
      InboundOsgpCoreResponsesMessagingConfig.class,
      InboundWebServiceRequestsMessagingConfig.class,
      OutboundOsgpCoreRequestsMessagingConfig.class,
      OutboundOsgpCoreResponsesMessagingConfig.class,
      OutboundWebServiceResponsesMessagingConfig.class
    })
public class MessagingConfig extends AbstractConfig {

  @Bean
  public DefaultJmsConfiguration defaultJmsConfiguration() {
    return new DefaultJmsConfiguration();
  }

  @Bean
  JmsMessageCreator jmsMessageCreator(final DefaultJmsConfiguration defaultJmsConfiguration) {
    return new JmsMessageCreator(defaultJmsConfiguration.getBrokerType());
  }
}
