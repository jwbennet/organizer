package com.bbtech.organizer.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bbtech.organizer.server.dao.EmailDao;
import com.bbtech.organizer.server.entities.Email;

@Repository
public class EmailDaoImpl implements EmailDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Email findById(Long id) {
		Query q = entityManager.createNamedQuery(FIND_BY_ID);
		q.setParameter(ID_PARAM, id);
		Email email = (Email)q.getSingleResult();
		return email;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Email> findByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		return q.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Email> findActiveByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_ACTIVE_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		return q.getResultList();
	}

	@Override
	public Email findPrimaryByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_PRIMARY_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		return (Email)q.getSingleResult();
	}
	
	@Override
	public void setPrimary(Email email) {
		if(email.getPerson() != null && email.getPerson().getId() != null) {
			Query q = entityManager.createNamedQuery(RESET_PRIMARY);
			q.setParameter(ID_PARAM, email.getPerson().getId());
			q.executeUpdate();
			
			if(email.getId() != null) {
				q = entityManager.createNamedQuery(SET_PRIMARY);
				q.setParameter(ID_PARAM, email.getId());
				q.executeUpdate();
			}
		}
	}

	@Override
	public Email save(Email email) {
		if(email == null) {
			return null;
		}
		try {
            if (email.getId() == null) {
                entityManager.persist(email);
                entityManager.flush();
            } else {
                entityManager.merge(email);
            }
        } catch (OptimisticLockException oe) {
            oe.printStackTrace();
            throw oe;
        }
		return email;
	}

	@Override
	public void delete(Email email) {
		if(email == null || email.getId() == null) {
			return;
		}
		deleteById(email.getId());
	}

	@Override
	public void deleteById(Long id) {
		Query q = entityManager.createNamedQuery(DELETE_BY_ID);
		q.setParameter(ID_PARAM, id);
		q.executeUpdate();
	}
}
