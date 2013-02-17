package com.bbtech.organizer.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bbtech.organizer.server.dao.SampleDao;
import com.bbtech.organizer.server.entities.Sample;


@Repository
public class SampleDaoImpl implements SampleDao {

    @PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked") 
	public Sample findById(Long id) {
		Query q = entityManager.createQuery("select s from Sample s where s.id = :id");
		q.setParameter("id", id);
		List<Sample> samples = q.getResultList();
 		return samples != null && samples.size() == 1 ? samples.get(0) : null;
	}

	@SuppressWarnings("unchecked") 
	public List<Sample> findAll() {
		Query q = entityManager.createQuery("select s from Sample s");
		return q.getResultList();
	}

	public Sample save(Sample sample) {
        if (sample == null) {
            return null;
        }
        try {
            if (sample.getId() == null) {
                entityManager.persist(sample);
            } else {
                entityManager.merge(sample);
            }
        } catch (OptimisticLockException oe) {
            oe.printStackTrace();
        }
        return sample;	
    }

	public Sample delete(Sample sample) {
		Query q = entityManager.createQuery("delete s from Sample s where s.id = id");
		q.setParameter("id", sample.getId());
		int result = q.executeUpdate();
		if (result == 1) {
			return sample;
		}
		return null;
	}

}
