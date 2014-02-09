package com.bbtech.organizer.server.util;

import org.springframework.context.ApplicationContext;

import com.bbtech.organizer.server.services.LogService;
import com.bbtech.organizer.server.services.NoteService;
import com.bbtech.organizer.server.services.PersonService;
import com.bbtech.organizer.server.services.WikiService;

public final class ServiceLocator {

	private static final ServiceLocator INSTANCE = new ServiceLocator();

	private ApplicationContext applicationContext;

	private ServiceLocator() {}

	public static WikiService getWikiService() {
		return INSTANCE.getApplicationContext().getBean(WikiService.class);
	}
	
	public static PersonService getPersonService() {
		return INSTANCE.getApplicationContext().getBean(PersonService.class);
	}
	
	public static LogService getLogService() {
		return INSTANCE.getApplicationContext().getBean(LogService.class);
	}
	
	public static NoteService getNoteService() {
		return INSTANCE.getApplicationContext().getBean(NoteService.class);
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
