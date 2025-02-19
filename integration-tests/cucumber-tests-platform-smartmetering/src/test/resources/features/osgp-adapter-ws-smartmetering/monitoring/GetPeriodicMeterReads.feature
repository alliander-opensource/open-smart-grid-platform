@SmartMetering @Platform @SmartMeteringMonitoring
Feature: SmartMetering Monitoring - Get Periodic Meter Reads
  As a grid operator
  I want to be able to get periodic meter reads from a device
  So they can be passed on for billing data

  Scenario Outline: Get the periodic meter reads <PeriodType> from a <Protocol> <ProtocolVersion> E-meter
    Given a dlms device
      | DeviceIdentification     | <DeviceId>        |
      | DeviceType               | SMART_METER_E     |
      | Protocol                 | <Protocol>        |
      | ProtocolVersion          | <ProtocolVersion> |
      | Port                     | <Port>            |
    When the get "<PeriodType>" meter reads request is received
      | DeviceIdentification | <DeviceId>   |
      | PeriodType           | <PeriodType> |
      | BeginDate            | <BeginDate>  |
      | EndDate              | <EndDate>    |
    Then the "<PeriodType>" meter reads result should be returned
      | DeviceIdentification | <DeviceId> |

    Examples:
      | PeriodType | BeginDate                | EndDate                  | DeviceId             | Protocol | ProtocolVersion | Port |
      | INTERVAL   | 2015-09-01T00:00:00.000Z | 2015-10-01T00:00:00.000Z | TEST1024000000001    | DSMR     | 4.2.2           | 1024 |
      | MONTHLY    | 2016-01-01T00:00:00.000Z | 2016-09-01T00:00:00.000Z | TEST1024000000001    | DSMR     | 4.2.2           | 1024 |
      | DAILY      | 2022-05-02T00:00:00.000Z | 2022-05-02T00:00:00.000Z | TEST1024000000001    | DSMR     | 4.2.2           | 1024 |
      | INTERVAL   | 2015-09-01T00:00:00.000Z | 2015-10-01T00:00:00.000Z | TEST1027000000001    | SMR      | 5.0.0           | 1027 |
      | MONTHLY    | 2016-01-01T00:00:00.000Z | 2016-09-01T00:00:00.000Z | TEST1027000000001    | SMR      | 5.0.0           | 1027 |
      | DAILY      | 2022-05-02T00:00:00.000Z | 2022-05-02T00:00:00.000Z | TEST1027000000001    | SMR      | 5.0.0           | 1027 |

  Scenario Outline: Get the periodic meter reads <PeriodType> from a <Protocol> <ProtocolVersion> G-meter
    Given a dlms device
      | DeviceIdentification     | <DeviceId>        |
      | DeviceType               | SMART_METER_E     |
      | Protocol                 | <Protocol>        |
      | ProtocolVersion          | <ProtocolVersion> |
      | Port                     | <Port>            |
    And a dlms device
      | DeviceIdentification        | <MBusId>      |
      | DeviceType                  | SMART_METER_G |
      | GatewayDeviceIdentification | <DeviceId>    |
      | Channel                     | 1             |
    When the get "<PeriodType>" meter reads gas request is received
      | DeviceIdentification | <MBusId>     |
      | PeriodType           | <PeriodType> |
      | BeginDate            | <BeginDate>  |
      | EndDate              | <EndDate>    |
    Then the "<PeriodType>" meter reads gas result should be returned
      | DeviceIdentification | <MBusId> |

    Examples:
      | PeriodType | BeginDate                | EndDate                  | MBusId            | DeviceId             | Protocol | ProtocolVersion | Port |
      | INTERVAL   | 2015-09-01T00:00:00.000Z | 2015-10-01T00:00:00.000Z | TESTG102400000001 | TEST1024000000001    | DSMR     | 4.2.2           | 1024 |
      | MONTHLY    | 2016-01-01T00:00:00.000Z | 2016-09-01T00:00:00.000Z | TESTG102400000001 | TEST1024000000001    | DSMR     | 4.2.2           | 1024 |
      | DAILY      | 2022-05-02T00:00:00.000Z | 2022-05-02T00:00:00.000Z | TESTG102400000001 | TEST1024000000001    | DSMR     | 4.2.2           | 1024 |
      | INTERVAL   | 2015-09-01T00:00:00.000Z | 2015-10-01T00:00:00.000Z | TESTG102700000001 | TEST1027000000001    | SMR      | 5.0.0           | 1027 |
      | MONTHLY    | 2016-01-01T00:00:00.000Z | 2016-09-01T00:00:00.000Z | TESTG102700000001 | TEST1027000000001    | SMR      | 5.0.0           | 1027 |
      | DAILY      | 2022-05-02T00:00:00.000Z | 2022-05-02T00:00:00.000Z | TESTG102700000001 | TEST1027000000001    | SMR      | 5.0.0           | 1027 |