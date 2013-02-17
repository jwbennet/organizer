package com.bbtech.organizer.server.dao;

import java.util.List;

import com.bbtech.organizer.server.entities.Name;

public interface NameDao {

	public static final String FIND_BY_ID = "Name.findById";
	public static final String FIND_BY_ID_QUERY = "SELECT n FROM Name n WHERE n.id = :id";
	public static final String FIND_BY_USERNAME = "Name.findByUsername";
	public static final String FIND_BY_USERNAME_QUERY = "SELECT n FROM Name n WHERE n.person.username = :username";
	public static final String FIND_ACTIVE_BY_USERNAME = "Name.findActiveByUsername";
	public static final String FIND_ACTIVE_BY_USERNAME_QUERY = FIND_BY_USERNAME_QUERY + " AND n.active = 'Y'";
	public static final String FIND_PRIMARY_BY_USERNAME = "Name.findActiveByUsername";
	public static final String FIND_PRIMARY_BY_USERNAME_QUERY = FIND_ACTIVE_BY_USERNAME_QUERY + " AND n.primary = 'Y'";
	public static final String SET_PRIMARY = "Name.setPrimary";
	public static final String SET_PRIMARY_QUERY = "UPDATE Name n SET n.primary = 'Y' WHERE n.id = :id";
	public static final String RESET_PRIMARY = "Name.resetPrimary";
	public static final String RESET_PRIMARY_QUERY = "UPDATE Name n SET n.primary = 'N' WHERE n.person.id = :id";
	public static final String DELETE_BY_ID = "Name.deleteById";
	public static final String DELETE_BY_ID_QUERY = "UPDATE Name n SET n.active = 'N' WHERE n.id = :id";
	
	public static final String ID_PARAM = "id";
	public static final String USERNAME_PARAM = "username";
	
	Name findById(Long id);
	List<Name> findByUsername(String username);
	List<Name> findActiveByUsername(String username);
	Name findPrimaryByUsername(String username);
	void setPrimary(Name name);
	Name save(Name name);
	void delete(Name name);
	void deleteById(Long id);
}
