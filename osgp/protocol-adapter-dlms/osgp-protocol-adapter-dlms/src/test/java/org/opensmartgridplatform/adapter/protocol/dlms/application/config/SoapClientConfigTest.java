/*
 * Copyright 2023 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.protocol.dlms.application.config;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

class SoapClientConfigTest {
  @Test
  void testHttpComponentsMessageSender() throws Exception {
    final SoapClientConfig soapClientConfig = new SoapClientConfig();

    final Resource keyStore = new ClassPathResource("trust.jks");
    ReflectionTestUtils.setField(soapClientConfig, "keyStore", keyStore, Resource.class);
    ReflectionTestUtils.setField(soapClientConfig, "keyStorePassword", "123456", String.class);
    ReflectionTestUtils.setField(soapClientConfig, "trustStore", keyStore, Resource.class);
    ReflectionTestUtils.setField(soapClientConfig, "trustStorePassword", "123456", String.class);
    ReflectionTestUtils.setField(soapClientConfig, "keyPassword", "keyPassword", String.class);
    ReflectionTestUtils.setField(soapClientConfig, "maxConnPerRoute", 3);
    ReflectionTestUtils.setField(soapClientConfig, "maxConnTotal", 18);

    final HttpComponentsMessageSender httpComponentsMessageSender =
        soapClientConfig.httpComponentsMessageSender();
    assertThat(httpComponentsMessageSender).isNotNull();
  }
}
