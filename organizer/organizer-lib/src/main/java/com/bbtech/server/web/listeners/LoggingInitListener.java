package com.bbtech.server.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LoggingInitListener implements ServletContextListener {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LoggingInitListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		org.apache.log4j.PropertyConfigurator.configureAndWatch("classpath:log4j.properties", 5 * 60 * 1000);
		LOG.info("Finished initializing log4j");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {}

}
