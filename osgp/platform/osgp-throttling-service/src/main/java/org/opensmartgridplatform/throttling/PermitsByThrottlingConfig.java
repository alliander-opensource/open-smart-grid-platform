/*
 * Copyright 2021 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.throttling;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.PostConstruct;
import org.opensmartgridplatform.throttling.entities.ThrottlingConfig;
import org.opensmartgridplatform.throttling.repositories.PermitRepository;
import org.opensmartgridplatform.throttling.repositories.ThrottlingConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class PermitsByThrottlingConfig {
  private static final Logger LOGGER = LoggerFactory.getLogger(PermitsByThrottlingConfig.class);

  private final ConcurrentMap<Short, PermitsPerNetworkSegment> permitsPerSegmentByConfig =
      new ConcurrentHashMap<>();

  private final ThrottlingConfigRepository throttlingConfigRepository;
  private final PermitRepository permitRepository;

  public PermitsByThrottlingConfig(
      final ThrottlingConfigRepository throttlingConfigRepository,
      final PermitRepository permitRepository) {

    this.throttlingConfigRepository = throttlingConfigRepository;
    this.permitRepository = permitRepository;
  }

  /** Clears all cached permit counts and initializes the cached information from the database. */
  @PostConstruct
  public void initialize() {
    final StopWatch stopWatch = new StopWatch(this.getClass().getSimpleName());
    stopWatch.start();

    final List<Short> throttlingConfigIdsInDb =
        this.throttlingConfigRepository.findAll().stream().map(ThrottlingConfig::getId).toList();

    /* Create new config */
    throttlingConfigIdsInDb.forEach(
        throttlingConfigId ->
            this.permitsPerSegmentByConfig.putIfAbsent(
                throttlingConfigId, new PermitsPerNetworkSegment(this.permitRepository)));

    /* Update config */
    this.permitsPerSegmentByConfig.entrySet().parallelStream()
        .forEach(entry -> entry.getValue().initialize(entry.getKey()));

    /* Remove config not in database */
    final List<Short> throttlingConfigIdsToBeRemoved =
        this.permitsPerSegmentByConfig.keySet().stream()
            .filter(configId -> !throttlingConfigIdsInDb.contains(configId))
            .toList();
    throttlingConfigIdsToBeRemoved.forEach(this.permitsPerSegmentByConfig::remove);

    stopWatch.stop();
    LOGGER.info("Initialize of all configs took {}ms", stopWatch.getLastTaskTimeMillis());
  }

  public Map<Short, PermitsPerNetworkSegment> permitsPerNetworkSegmentByConfig() {
    return new TreeMap<>(this.permitsPerSegmentByConfig);
  }

  public boolean requestPermit(
      final short throttlingConfigId,
      final int clientId,
      final int baseTransceiverStationId,
      final int cellId,
      final int requestId,
      final int maxConcurrency) {

    final PermitsPerNetworkSegment permitsPerNetworkSegment =
        this.permitsPerSegmentByConfig.computeIfAbsent(
            throttlingConfigId, this::createAndInitialize);

    return permitsPerNetworkSegment.requestPermit(
        throttlingConfigId, clientId, baseTransceiverStationId, cellId, requestId, maxConcurrency);
  }

  private PermitsPerNetworkSegment createAndInitialize(final short throttlingConfigId) {
    final PermitsPerNetworkSegment permitsPerNetworkSegment =
        new PermitsPerNetworkSegment(this.permitRepository);
    permitsPerNetworkSegment.initialize(throttlingConfigId);
    return permitsPerNetworkSegment;
  }

  public void newThrottlingConfigCreated(final short throttlingConfigId) {
    /*
     * No need to initialize the new PermitsPerNetworkSegment with existing permits from the
     * database as this method should be called before any permits are granted for the new
     * throttling configuration.
     */
    this.permitsPerSegmentByConfig.putIfAbsent(
        throttlingConfigId, new PermitsPerNetworkSegment(this.permitRepository));
  }

  public boolean releasePermit(
      final short throttlingConfigId,
      final int clientId,
      final int baseTransceiverStationId,
      final int cellId,
      final int requestId) {

    final PermitsPerNetworkSegment permitsPerNetworkSegment =
        this.permitsPerSegmentByConfig.get(throttlingConfigId);
    return permitsPerNetworkSegment != null
        && permitsPerNetworkSegment.releasePermit(
            throttlingConfigId, clientId, baseTransceiverStationId, cellId, requestId);
  }

  public boolean discardPermit(final int clientId, final int requestId) {
    return this.permitRepository
        .findByClientIdAndRequestId(clientId, requestId)
        .map(
            permit ->
                this.releasePermit(
                    permit.getThrottlingConfigId(),
                    permit.getClientId(),
                    permit.getBaseTransceiverStationId(),
                    permit.getCellId(),
                    permit.getRequestId()))
        .orElse(false);
  }
}
