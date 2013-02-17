package com.bbtech.organizer.server.util;

import org.springframework.context.ApplicationContext;

import com.bbtech.organizer.server.services.WikiService;

public final class ServiceLocator {

	private static final ServiceLocator INSTANCE = new ServiceLocator();

	private ApplicationContext applicationContext;

	private ServiceLocator() {}

	public static WikiService getWikiService() {
		return INSTANCE.getApplicationContext().getBean(WikiService.class);
	}


	public ApplicationContext getApplicationContext() {
		if(this.applicationContext == null) {
			this.applicationContext = ApplicationContextProvider.getApplicationContext();
		}
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
