/*
 * Copyright 2022 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.cucumber.platform.distributionautomation.glue.steps.database.ws;

import java.util.List;
import org.opensmartgridplatform.adapter.ws.domain.entities.ApplicationDataLookupKey;
import org.opensmartgridplatform.adapter.ws.domain.entities.NotificationWebServiceConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionAutomationNotificationWebServiceConfigurationRepository
    extends JpaRepository<NotificationWebServiceConfiguration, ApplicationDataLookupKey> {

  List<NotificationWebServiceConfiguration> findByIdOrganisationIdentification(
      String organisationIdentification);
}
