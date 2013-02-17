package com.bbtech.organizer.server.dao;

import java.util.List;

import com.bbtech.organizer.server.entities.Address;

public interface AddressDao {

	public static final String FIND_BY_ID = "Address.findById";
	public static final String FIND_BY_ID_QUERY = "SELECT a FROM Address a WHERE a.id = :id";
	public static final String FIND_BY_USERNAME = "Address.findByUsername";
	public static final String FIND_BY_USERNAME_QUERY = "SELECT a FROM Address a WHERE a.person.username = :username";
	public static final String FIND_ACTIVE_BY_USERNAME = "Address.findActiveByUsername";
	public static final String FIND_ACTIVE_BY_USERNAME_QUERY = FIND_BY_USERNAME_QUERY + " AND a.active = 'Y'";
	public static final String FIND_PRIMARY_BY_USERNAME = "Address.findActiveByUsername";
	public static final String FIND_PRIMARY_BY_USERNAME_QUERY = FIND_ACTIVE_BY_USERNAME_QUERY + " AND a.primary = 'Y'";
	public static final String SET_PRIMARY = "Address.setPrimary";
	public static final String SET_PRIMARY_QUERY = "UPDATE Address a SET a.primary = 'Y' WHERE a.id = :id";
	public static final String RESET_PRIMARY = "Address.resetPrimary";
	public static final String RESET_PRIMARY_QUERY = "UPDATE Address a SET a.primary = 'N' WHERE a.person.id = :id";
	public static final String DELETE_BY_ID = "Address.deleteById";
	public static final String DELETE_BY_ID_QUERY = "UPDATE Address a SET a.active = 'N' WHERE a.id = :id";
	
	public static final String ID_PARAM = "id";
	public static final String USERNAME_PARAM = "username";
	
	Address findById(Long id);
	List<Address> findByUsername(String username);
	List<Address> findActiveByUsername(String username);
	Address findPrimaryByUsername(String username);
	void setPrimary(Address address);
	Address save(Address address);
	void delete(Address address);
	void deleteById(Long id);
}
