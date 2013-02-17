package com.bbtech.organizer.server.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class Formats {

	private Formats() {}
	
	public static final DateTimeFormatter TIME_FORMAT = DateTimeFormat.forPattern("HH:mm");
}
