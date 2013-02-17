package com.bbtech.organizer.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import com.bbtech.organizer.server.dao.LogDao;
import com.bbtech.organizer.server.entities.Log;

@Repository
public class LogDaoImpl implements LogDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Log findById(Long id) {
		Query q = entityManager.createNamedQuery(FIND_BY_ID);
		q.setParameter(ID_PARAM, id);
		Log log = (Log)q.getSingleResult();
		return log;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Log> findAll() {
		Query q = entityManager.createNamedQuery(FIND_ALL);
		return q.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Log> findByDateRange(DateTime start, DateTime end) {
		Query q = entityManager.createNamedQuery(FIND_BY_DATE_RANGE);
		q.setParameter(START_DATE_PARAM, start);
		q.setParameter(END_DATE_PARAM, end);
		return q.getResultList();
	}

	@Override
	public Log save(Log log) {
		if(log == null) {
			return null;
		}
		try {
            if (log.getId() == null) {
                entityManager.persist(log);
                entityManager.flush();
            } else {
                entityManager.merge(log);
            }
        } catch (OptimisticLockException oe) {
            oe.printStackTrace();
            throw oe;
        }
		return log;
	}

	@Override
	public void delete(Log log) {
		if(log == null || log.getId() == null) {
			return;
		}
		deleteById(log.getId());
	}

	@Override
	public void deleteById(Long id) {
		Query q = entityManager.createNamedQuery(DELETE_BY_ID);
		q.setParameter(ID_PARAM, id);
		q.executeUpdate();
	}
}
