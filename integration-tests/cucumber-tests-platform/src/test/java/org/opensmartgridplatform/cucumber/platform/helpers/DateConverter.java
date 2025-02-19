/*
 * Copyright 2017 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.cucumber.platform.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateConverter {
  private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

  private DateConverter() {}

  public static XMLGregorianCalendar createXMLGregorianCalendar(final Date date) {
    try {
      final GregorianCalendar gregorianCalendar = new GregorianCalendar();
      gregorianCalendar.setTime(date);
      return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    } catch (final DatatypeConfigurationException e) {
      throw new RuntimeException(e);
    }
  }

  public static XMLGregorianCalendar parseDate(final String dateString) {
    final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    try {
      return createXMLGregorianCalendar(sdf.parse(dateString));
    } catch (final ParseException e) {
      throw new IllegalArgumentException(
          String.format("Date not correctly formatted %s as %s: ", dateString, DATE_FORMAT), e);
    }
  }
}
