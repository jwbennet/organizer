package com.bbtech.organizer.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bbtech.organizer.server.dao.PhoneNumberDao;
import com.bbtech.organizer.server.entities.PhoneNumber;

@Repository
public class PhoneNumberDaoImpl implements PhoneNumberDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public PhoneNumber findById(Long id) {
		Query q = entityManager.createNamedQuery(FIND_BY_ID);
		q.setParameter(ID_PARAM, id);
		PhoneNumber phoneNumber = (PhoneNumber)q.getSingleResult();
		return phoneNumber;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PhoneNumber> findByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		return q.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PhoneNumber> findActiveByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_ACTIVE_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		return q.getResultList();
	}

	@Override
	public PhoneNumber findPrimaryByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_PRIMARY_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		return (PhoneNumber)q.getSingleResult();
	}
	
	@Override
	public void setPrimary(PhoneNumber phoneNumber) {
		if(phoneNumber.getPerson() != null && phoneNumber.getPerson().getId() != null) {
			Query q = entityManager.createNamedQuery(RESET_PRIMARY);
			q.setParameter(ID_PARAM, phoneNumber.getPerson().getId());
			q.executeUpdate();
			
			if(phoneNumber.getId() != null) {
				q = entityManager.createNamedQuery(SET_PRIMARY);
				q.setParameter(ID_PARAM, phoneNumber.getId());
				q.executeUpdate();
			}
		}
	}

	@Override
	public PhoneNumber save(PhoneNumber phoneNumber) {
		if(phoneNumber == null) {
			return null;
		}
		try {
            if (phoneNumber.getId() == null) {
                entityManager.persist(phoneNumber);
                entityManager.flush();
            } else {
                entityManager.merge(phoneNumber);
            }
        } catch (OptimisticLockException oe) {
            oe.printStackTrace();
            throw oe;
        }
		return phoneNumber;
	}

	@Override
	public void delete(PhoneNumber phoneNumber) {
		if(phoneNumber == null || phoneNumber.getId() == null) {
			return;
		}
		deleteById(phoneNumber.getId());
	}

	@Override
	public void deleteById(Long id) {
		Query q = entityManager.createNamedQuery(DELETE_BY_ID);
		q.setParameter(ID_PARAM, id);
		q.executeUpdate();
	}
}
