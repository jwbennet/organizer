package com.bbtech.organizer.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bbtech.organizer.server.dao.NameDao;
import com.bbtech.organizer.server.entities.Name;

@Repository
public class NameDaoImpl implements NameDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Name findById(Long id) {
		Query q = entityManager.createNamedQuery(FIND_BY_ID);
		q.setParameter(ID_PARAM, id);
		Name name = (Name)q.getSingleResult();
		return name;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Name> findByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		return q.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Name> findActiveByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_ACTIVE_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		return q.getResultList();
	}

	@Override
	public Name findPrimaryByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_PRIMARY_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		return (Name)q.getSingleResult();
	}

	@Override
	public void setPrimary(Name name) {
		if(name.getPerson() != null && name.getPerson().getId() != null) {
			Query q = entityManager.createNamedQuery(RESET_PRIMARY);
			q.setParameter(ID_PARAM, name.getPerson().getId());
			q.executeUpdate();
			
			if(name.getId() != null) {
				q = entityManager.createNamedQuery(SET_PRIMARY);
				q.setParameter(ID_PARAM, name.getId());
				q.executeUpdate();
			}
		}
	}

	@Override
	public Name save(Name name) {
		if(name == null) {
			return null;
		}
		try {
            if (name.getId() == null) {
                entityManager.persist(name);
                entityManager.flush();
            } else {
                entityManager.merge(name);
            }
        } catch (OptimisticLockException oe) {
            oe.printStackTrace();
            throw oe;
        }
		return name;
	}

	@Override
	public void delete(Name name) {
		if(name == null || name.getId() == null) {
			return;
		}
		deleteById(name.getId());
	}

	@Override
	public void deleteById(Long id) {
		Query q = entityManager.createNamedQuery(DELETE_BY_ID);
		q.setParameter(ID_PARAM, id);
		q.executeUpdate();
	}
}
