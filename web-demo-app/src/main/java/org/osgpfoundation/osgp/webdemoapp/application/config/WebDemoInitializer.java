/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package org.osgpfoundation.osgp.webdemoapp.application.config;

import java.util.TimeZone;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
//import org.jboss.netty.logging.InternalLoggerFactory;
//import org.jboss.netty.logging.Slf4JLoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.WebApplicationInitializer;

import org.osgpfoundation.osgp.webdemoapp.application.config.ApplicationContext;

/**
 * Web application Java configuration class. The usage of web application
 * initializer requires Spring Framework 3.1 and Servlet 3.0.
 */
public class WebDemoInitializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String DISPATCHER_SERVLET_MAPPING = "/";

    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        try {
            // Force the timezone of application to UTC (required for
            // Hibernate/JDBC)
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

            final Context initialContext = new InitialContext();

//            final String logLocation = (String) initialContext
//                    .lookup("java:comp/env/osp/webDeviceSimulator/log-config");
//            LogbackConfigurer.initLogging(logLocation);
            //InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());

            final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
            rootContext.register(ApplicationContext.class);

            final ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
                    new DispatcherServlet(rootContext));
            dispatcher.setLoadOnStartup(1);
            dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);

            servletContext.addListener(new ContextLoaderListener(rootContext));
        } catch (final NamingException e) {
            throw new ServletException("naming exception", e);
        //} catch (final FileNotFoundException e) {
            //throw new ServletException("Logging file not found", e);
        //} catch (final JoranException e) {
            //throw new ServletException("Logback exception", e);
        }
    }
}
