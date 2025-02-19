@SmartMetering @Platform @NightlyBuildOnly
Feature: SmartMetering Installation - Administrative Decouple M-Bus Device
  As a grid operator
  I want to be able to administratively decouple an M-Bus device

  Scenario: Administrative Decouple G-meter
    Given a dlms device
      | DeviceIdentification | TEST1024000000001 |
      | DeviceType           | SMART_METER_E     |
    And a dlms device
      | DeviceIdentification        | TESTG102400000001 |
      | DeviceType                  | SMART_METER_G     |
      | GatewayDeviceIdentification | TEST1024000000001 |
      | Channel                     |                 1 |
    When the Administrative Decouple G-meter "TESTG102400000001" request is received
    Then the Administrative Decouple response is "OK"
    And the G-meter "TESTG102400000001" is Decoupled from device "TEST1024000000001"
    And the mbus device "TESTG102400000001" has properties
      | Channel             | null |
      | MbusPrimaryAddress  | null |
      | GatewayDevice       | null |


  Scenario: Administrative Decouple inactive G-meter from E-meter
    Given a dlms device
      | DeviceIdentification | TEST1024000000001 |
      | DeviceType           | SMART_METER_E     |
    And a dlms device
      | DeviceIdentification        | TESTG102400000001 |
      | DeviceType                  | SMART_METER_G     |
      | GatewayDeviceIdentification | TEST1024000000001 |
      | Channel                     |                 1 |
      | DeviceLifecycleStatus       | NEW_IN_INVENTORY  |
    When the Administrative Decouple G-meter "TESTG102400000001" request is received
    Then the Administrative Decouple response is "OK"
    And the mbus device "TESTG102400000001" is not coupled to the device "TEST1024000000001"
    And the mbus device "TESTG102400000001" has properties
      | Channel             | null |
      | MbusPrimaryAddress  | null |
      | GatewayDevice       | null |


  Scenario: Administrative Decouple coupled G-meter "TESTG101205673117" from E-meter "TEST1024000000001"
    Given a dlms device
      | DeviceIdentification | TEST1024000000001 |
      | DeviceType           | SMART_METER_E     |
    And a dlms device
      | DeviceIdentification           | TESTG101205673117 |
      | DeviceType                     | SMART_METER_G     |
      | GatewayDeviceIdentification    | TEST1024000000001 |
      | Channel                        |                 1 |
      | MbusIdentificationNumber       |          12056731 |
      | MbusPrimaryAddress             |                 9 |
      | MbusManufacturerIdentification | LGB               |
      | MbusVersion                    |                66 |
      | MbusDeviceTypeIdentification   |                 3 |
    And device simulation of "TEST1024000000001" with M-Bus client version 0 values for channel 1
      | MbusPrimaryAddress             | 3        |
      | MbusIdentificationNumber       | 12056731 |
      | MbusManufacturerIdentification | LGB      |
      | MbusVersion                    | 66       |
      | MbusDeviceTypeIdentification   | 3        |
    When the Administrative Decouple G-meter "TESTG101205673117" request is received
    Then the Administrative Decouple response is "OK"
    And the mbus device "TESTG101205673117" is not coupled to the device "TEST1024000000001"
    And the mbus device "TESTG101205673117" has properties
      | Channel             | null |
      | MbusPrimaryAddress  | null |
      | GatewayDevice       | null |

  Scenario: Administrative Decouple decoupled G-meter "TESTG101205673117" from E-meter "TEST1024000000001"
    Given a dlms device
      | DeviceIdentification | TEST1024000000001 |
      | DeviceType           | SMART_METER_E     |
    And a dlms device
      | DeviceIdentification           | TESTG101205673117 |
      | DeviceType                     | SMART_METER_G     |
      | MbusIdentificationNumber       |          12056731 |
      | MbusPrimaryAddress             |                 9 |
      | MbusManufacturerIdentification | LGB               |
      | MbusVersion                    |                66 |
      | MbusDeviceTypeIdentification   |                 3 |
    And device simulation of "TEST1024000000001" with M-Bus client version 0 values for channel 1
      | MbusPrimaryAddress             | 3        |
      | MbusIdentificationNumber       | 12056731 |
      | MbusManufacturerIdentification | LGB      |
      | MbusVersion                    | 66       |
      | MbusDeviceTypeIdentification   | 3        |
    When the Administrative Decouple G-meter "TESTG101205673117" request is received
    Then the Administrative Decouple response is "OK"
    And the mbus device "TESTG101205673117" is not coupled to the device "TEST1024000000001"
    And the values for the M-Bus client for channel 1 on device simulator "TEST1024000000001" are
      | MbusPrimaryAddress             | 3        |
      | MbusIdentificationNumber       | 12056731 |
      | MbusManufacturerIdentification | LGB      |
      | MbusVersion                    | 66       |
      | MbusDeviceTypeIdentification   | 3        |
    And the mbus device "TESTG101205673117" has properties
      | Channel             | null |
      | MbusPrimaryAddress  | null |
      | GatewayDevice       | null |
