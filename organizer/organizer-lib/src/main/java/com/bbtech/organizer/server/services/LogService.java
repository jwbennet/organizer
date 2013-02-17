package com.bbtech.organizer.server.services;

import java.util.List;

import org.joda.time.DateTime;

import com.bbtech.organizer.server.entities.Log;

public interface LogService {

	List<Log> getAll();
	Log getLog(Long id);
	List<Log> getTodaysLogs();
	List<Log> getLogsByDateRange(DateTime start, DateTime end);
	Log createLog(String subject, String location, String agenda, DateTime date, int duration);
	Log saveLog(Log log);
	void deleteLog(Log log);
	void deleteLogById(Long id);
}
