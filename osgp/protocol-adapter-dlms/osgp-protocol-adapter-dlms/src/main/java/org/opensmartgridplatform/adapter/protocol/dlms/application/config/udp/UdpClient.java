/*
 * Copyright 2022 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.opensmartgridplatform.adapter.protocol.dlms.application.config.udp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UdpClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(UdpClient.class);

  private final UnicastSendingMessageHandler udpSendingAdapter;

  @Autowired
  public UdpClient(final UnicastSendingMessageHandler udpSendingAdapter) {
    this.udpSendingAdapter = udpSendingAdapter;
  }

  public void sendMessage(final String message) {
    LOGGER.info("Sending UDP message: {}", message);
    this.udpSendingAdapter.handleMessage(MessageBuilder.withPayload(message).build());
  }
}
