package com.bbtech.organizer.server.dao;

import java.util.List;

import org.joda.time.DateTime;

import com.bbtech.organizer.server.entities.Log;

public interface LogDao {
	
	public static final String FIND_ALL = "Log.findAll";
	public static final String FIND_ALL_QUERY = "SELECT l FROM Log l";
	public static final String FIND_BY_ID = "Log.findById";
	public static final String FIND_BY_ID_QUERY = "SELECT l FROM Log l WHERE l.id = :id";
	public static final String FIND_BY_DATE_RANGE = "Log.findByDateRange";
	public static final String FIND_BY_DATE_RANGE_QUERY = "SELECT l FROM Log l WHERE l.date >= :startDate AND l.date < :endDate ORDER BY l.date ASC";
	public static final String DELETE_BY_ID = "Log.deleteById";
	public static final String DELETE_BY_ID_QUERY = "DELETE FROM Log l WHERE l.id = :id";
	
	public static final String ID_PARAM = "id";
	public static final String START_DATE_PARAM = "startDate";
	public static final String END_DATE_PARAM = "endDate";

	Log findById(Long id);
	List<Log> findAll();
	List<Log> findByDateRange(DateTime start, DateTime end);
	Log save(Log log);
	void delete(Log log);
	void deleteById(Long id);
}
