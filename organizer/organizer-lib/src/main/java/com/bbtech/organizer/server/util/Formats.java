package com.bbtech.organizer.server.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class Formats {

	private Formats() {}
	
	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("YYYY-MM-dd");
	public static final DateTimeFormatter TIME_FORMAT = DateTimeFormat.forPattern("HH:mm");
	public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm");
}
