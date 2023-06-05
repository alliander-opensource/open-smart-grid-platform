// SPDX-FileCopyrightText: 2023 Contributors to the GXF project
// SPDX-FileCopyrightText: Copyright Contributors to the GXF project
//
// SPDX-License-Identifier: Apache-2.0

package org.opensmartgridplatform.adapter.ws.shared.services;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.opensmartgridplatform.adapter.ws.domain.repositories.ResponseDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "transactionManager")
public class ResponseDataCleanupService {

  @Autowired private ResponseDataRepository responseDataRepository;

  @Autowired private int cleanupJobRetentionTimeInDays;

  public void execute() {

    final DateTime removeBeforeDateTime =
        DateTime.now(DateTimeZone.UTC).minusDays(this.cleanupJobRetentionTimeInDays);
    this.responseDataRepository.removeByCreationTimeBefore(removeBeforeDateTime.toDate());
  }
}
