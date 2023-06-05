// SPDX-FileCopyrightText: 2023 Contributors to the GXF project
// SPDX-FileCopyrightText: Copyright Contributors to the GXF project
//
// SPDX-License-Identifier: Apache-2.0

package org.opensmartgridplatform.adapter.ws.publiclighting.application.config;

import org.opensmartgridplatform.adapter.ws.publiclighting.application.config.messaging.InboundDomainResponsesMessagingConfig;
import org.opensmartgridplatform.adapter.ws.publiclighting.application.config.messaging.OutboundDomainRequestsMessagingConfig;
import org.opensmartgridplatform.adapter.ws.publiclighting.application.config.messaging.OutboundLoggingRequestsMessagingConfig;
import org.opensmartgridplatform.shared.application.config.AbstractConfig;
import org.opensmartgridplatform.shared.application.config.messaging.DefaultJmsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:osgp-adapter-ws-publiclighting.properties")
@PropertySource(value = "file:${osgp/Global/config}", ignoreResourceNotFound = true)
@PropertySource(
    value = "file:${osgp/AdapterWsPublicLighting/config}",
    ignoreResourceNotFound = true)
@Import(
    value = {
      InboundDomainResponsesMessagingConfig.class,
      OutboundDomainRequestsMessagingConfig.class,
      OutboundLoggingRequestsMessagingConfig.class
    })
public class MessagingConfig extends AbstractConfig {

  @Bean
  public DefaultJmsConfiguration defaultJmsConfiguration() {
    return new DefaultJmsConfiguration();
  }
}
