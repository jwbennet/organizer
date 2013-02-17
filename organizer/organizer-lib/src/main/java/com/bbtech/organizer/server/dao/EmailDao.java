package com.bbtech.organizer.server.dao;

import java.util.List;

import com.bbtech.organizer.server.entities.Email;

public interface EmailDao {

	public static final String FIND_BY_ID = "Email.findById";
	public static final String FIND_BY_ID_QUERY = "SELECT e FROM Email e WHERE e.id = :id";
	public static final String FIND_BY_USERNAME = "Email.findByUsername";
	public static final String FIND_BY_USERNAME_QUERY = "SELECT e FROM Email e WHERE e.person.username = :username";
	public static final String FIND_ACTIVE_BY_USERNAME = "Email.findActiveByUsername";
	public static final String FIND_ACTIVE_BY_USERNAME_QUERY = FIND_BY_USERNAME_QUERY + " AND e.active = 'Y'";
	public static final String FIND_PRIMARY_BY_USERNAME = "Email.findActiveByUsername";
	public static final String FIND_PRIMARY_BY_USERNAME_QUERY = FIND_ACTIVE_BY_USERNAME_QUERY + " AND e.primary = 'Y'";
	public static final String SET_PRIMARY = "Email.setPrimary";
	public static final String SET_PRIMARY_QUERY = "UPDATE Email e SET e.primary = 'Y' WHERE e.id = :id";
	public static final String RESET_PRIMARY = "Email.resetPrimary";
	public static final String RESET_PRIMARY_QUERY = "UPDATE Email e SET e.primary = 'N' WHERE e.person.id = :id";
	public static final String DELETE_BY_ID = "Email.deleteById";
	public static final String DELETE_BY_ID_QUERY = "UPDATE Email e SET e.active = 'N' WHERE e.id = :id";
	
	public static final String ID_PARAM = "id";
	public static final String USERNAME_PARAM = "username";
	
	Email findById(Long id);
	List<Email> findByUsername(String username);
	List<Email> findActiveByUsername(String username);
	Email findPrimaryByUsername(String username);
	void setPrimary(Email email);
	Email save(Email email);
	void delete(Email email);
	void deleteById(Long id);
}
