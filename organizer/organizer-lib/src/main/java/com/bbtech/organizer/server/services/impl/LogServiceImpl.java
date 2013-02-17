package com.bbtech.organizer.server.services.impl;

import java.util.List;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbtech.organizer.server.dao.LogDao;
import com.bbtech.organizer.server.entities.Log;
import com.bbtech.organizer.server.services.LogService;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogDao logDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Log> getAll() {
		return logDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Log getLog(Long id) {
		return logDao.findById(id);
	}

	@Override
	public List<Log> getTodaysLogs() {
		DateTime midnightToday = DateMidnight.now().toDateTime();
		DateTime midnightTomorrow = new DateTime(midnightToday.getMillis() + DateTimeConstants.MILLIS_PER_DAY);
		return this.getLogsByDateRange(midnightToday, midnightTomorrow);
	}

	@Override
	public List<Log> getLogsByDateRange(DateTime start, DateTime end) {
		return logDao.findByDateRange(start, end);
	}

	@Override
	@Transactional
	public Log createLog(String subject, String location, String agenda, DateTime date, int duration) {
		Log log = new Log();
		log.setSubject(subject);
		log.setDate(date);
		log.setDuration(duration);
		return logDao.save(log);
	}

	@Override
	@Transactional
	public Log saveLog(Log log) {
		return logDao.save(log);
	}

	@Override
	@Transactional
	public void deleteLog(Log log) {
		logDao.delete(log);
	}

	@Override
	@Transactional
	public void deleteLogById(Long id) {
		logDao.deleteById(id);
	}
}
