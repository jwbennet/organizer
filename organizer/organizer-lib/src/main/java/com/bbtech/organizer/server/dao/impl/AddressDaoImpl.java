package com.bbtech.organizer.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bbtech.organizer.server.dao.AddressDao;
import com.bbtech.organizer.server.entities.Address;

@Repository
public class AddressDaoImpl implements AddressDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Address findById(Long id) {
		Query q = entityManager.createNamedQuery(FIND_BY_ID);
		q.setParameter(ID_PARAM, id);
		Address address = (Address)q.getSingleResult();
		return address;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Address> findByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		return q.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Address> findActiveByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_ACTIVE_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		return q.getResultList();
	}

	@Override
	public Address findPrimaryByUsername(String username) {
		Query q = entityManager.createNamedQuery(FIND_PRIMARY_BY_USERNAME);
		q.setParameter(USERNAME_PARAM, username);
		return (Address)q.getSingleResult();
	}
	
	@Override
	public void setPrimary(Address address) {
		if(address.getPerson() != null && address.getPerson().getId() != null) {
			Query q = entityManager.createNamedQuery(RESET_PRIMARY);
			q.setParameter(ID_PARAM, address.getPerson().getId());
			q.executeUpdate();
			
			if(address.getId() != null) {
				q = entityManager.createNamedQuery(SET_PRIMARY);
				q.setParameter(ID_PARAM, address.getId());
				q.executeUpdate();
			}
		}
	}

	@Override
	public Address save(Address address) {
		if(address == null) {
			return null;
		}
		try {
            if (address.getId() == null) {
                entityManager.persist(address);
                entityManager.flush();
            } else {
                entityManager.merge(address);
            }
        } catch (OptimisticLockException oe) {
            oe.printStackTrace();
            throw oe;
        }
		return address;
	}

	@Override
	public void delete(Address address) {
		if(address == null || address.getId() == null) {
			return;
		}
		deleteById(address.getId());
	}

	@Override
	public void deleteById(Long id) {
		Query q = entityManager.createNamedQuery(DELETE_BY_ID);
		q.setParameter(ID_PARAM, id);
		q.executeUpdate();
	}
}
