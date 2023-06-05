-- SPDX-FileCopyrightText: 2023 Contributors to the GXF project
-- SPDX-FileCopyrightText: Contributors to the GXF project
--
-- SPDX-License-Identifier: Apache-2.0

COMMENT ON COLUMN device_log_item.decoded_message IS 'The decoded message of this device log item.';
COMMENT ON COLUMN device_log_item.device_identification IS 'The identification of a device for this device log item.';
COMMENT ON COLUMN device_log_item.device_uid IS 'The unique ID of a device for this device log item.';
COMMENT ON COLUMN device_log_item.encoded_message IS 'The encoded message of this device log item.';
COMMENT ON COLUMN device_log_item.incoming IS 'Indicates if the message is incoming or outgoing for this device log item.';
COMMENT ON COLUMN device_log_item.organisation_identification IS 'Identification of an organisation for this device log item.';
COMMENT ON COLUMN device_log_item.valid IS 'Indicates whether or not this is a valid message for this device log item.';
COMMENT ON COLUMN device_log_item.data_size IS 'The data size in bytes of the encoded message of this device log item.';
COMMENT ON COLUMN logging_event.timestmp IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.formatted_message IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.logger_name IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.level_string IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.thread_name IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.reference_flag IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.arg0 IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.arg1 IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.arg2 IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.arg3 IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.caller_filename IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.caller_class IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.caller_method IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.caller_line IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event.event_id IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event_exception.event_id IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event_exception.i IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event_exception.trace_line IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event_property.event_id IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event_property.mapped_key IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN logging_event_property.mapped_value IS 'Used by Logback (if database logging is enabled).';
COMMENT ON COLUMN offloading_data.table_name IS 'The name of the table which was offloaded.';
COMMENT ON COLUMN offloading_data.end_last_date_block IS 'The timestamp which indicates when a table was offloaded for the last time.';
COMMENT ON COLUMN schema_version.installed_rank IS 'Installed rank indicates the order of applied migrations. Used by Flyway.';
COMMENT ON COLUMN schema_version.version IS 'Version indicates the numbered prefix of migration files. Used by Flyway.';
COMMENT ON COLUMN schema_version.description IS 'Description indicates the name of migration files. Used by Flyway.';
COMMENT ON COLUMN schema_version.type IS 'Type can be SQL in case of SQL migration files or JDBC in case of migrating programmatically using Java. Used by Flyway.';
COMMENT ON COLUMN schema_version.script IS 'Full name of the migration script; version and description are derived from the full name. Used by Flyway.';
COMMENT ON COLUMN schema_version.checksum IS 'Hash of the content of a migration script. Used by Flyway.';
COMMENT ON COLUMN schema_version.installed_by IS 'User name of the database user who has run the migration script. Used by Flyway.';
COMMENT ON COLUMN schema_version.installed_on IS 'Timestamp indicating when the migration has been applied. Used by Flyway.';
COMMENT ON COLUMN schema_version.execution_time IS 'Duration of the migration in milliseconds. Used by Flyway.';
COMMENT ON COLUMN schema_version.success IS 'State indicating whether or not the migration was successfully applied. Used by Flyway.';
COMMENT ON COLUMN web_service_monitor_log.time_stamp IS 'Timestamp which indicates when a web service request was received.';
COMMENT ON COLUMN web_service_monitor_log.class_name IS 'The name of the endpoint class which received a web service request.';
COMMENT ON COLUMN web_service_monitor_log.method_name IS 'The name of the method in the endpoint class which received a web service request.';
COMMENT ON COLUMN web_service_monitor_log.organisation_identification IS 'Identification of an organisation which issued a web service request';
COMMENT ON COLUMN web_service_monitor_log.request_device_identification IS 'Identification of a device for this web service monitor log.';
COMMENT ON COLUMN web_service_monitor_log.correlation_uid IS 'The correlation UID which was generated after receiving a web service request.';
COMMENT ON COLUMN web_service_monitor_log.response_result IS 'The response state [OK, NOT_OK, NOT_FOUND, SOAP_FAULT] of the web service response.';
COMMENT ON COLUMN web_service_monitor_log.response_data_size IS 'The size in bytes of the web service response.';
COMMENT ON COLUMN web_service_monitor_log.user_name IS 'The user name of the user who issued the web service request.';
COMMENT ON COLUMN web_service_monitor_log.application_name IS 'The name of the application which issued the web service request.';