package com.bbtech.organizer.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bbtech.organizer.server.dao.PersonDao;
import com.bbtech.organizer.server.entities.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Person findById(Long id) {
		Query q = entityManager.createNamedQuery(FIND_BY_ID);
		q.setParameter(ID_PARAM, id);
		Person person = (Person)q.getSingleResult();
		return person;
	}

	@Override
	public Person findByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		Person person = (Person)q.getSingleResult();
		return person;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Person> findAll() {
		Query q = entityManager.createNamedQuery(FIND_ALL);
		return q.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Person> findActive() {
		Query q = entityManager.createNamedQuery(FIND_ACTIVE);
		return q.getResultList();
	}

	@Override
	public Person save(Person person) {
		if(person == null) {
			return null;
		}
		try {
            if (person.getId() == null) {
                entityManager.persist(person);
                entityManager.flush();
            } else {
                entityManager.merge(person);
            }
        } catch (OptimisticLockException oe) {
            oe.printStackTrace();
            throw oe;
        }
		return person;
	}

	@Override
	public void delete(Person person) {
		if(person == null || person.getId() == null) {
			return;
		}
		deleteById(person.getId());
	}

	@Override
	public void deleteById(Long id) {
		Query q = entityManager.createNamedQuery(DELETE_BY_ID);
		q.setParameter(ID_PARAM, id);
		q.executeUpdate();
	}

}
