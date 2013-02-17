package com.bbtech.organizer.server.dao;

import java.util.List;

import com.bbtech.organizer.server.entities.PhoneNumber;

public interface PhoneNumberDao {

	public static final String FIND_BY_ID = "PhoneNumber.findById";
	public static final String FIND_BY_ID_QUERY = "SELECT pn FROM PhoneNumber pn WHERE pn.id = :id";
	public static final String FIND_BY_USERNAME = "PhoneNumber.findByUsername";
	public static final String FIND_BY_USERNAME_QUERY = "SELECT pn FROM PhoneNumber pn WHERE pn.person.username = :username";
	public static final String FIND_ACTIVE_BY_USERNAME = "PhoneNumber.findActiveByUsername";
	public static final String FIND_ACTIVE_BY_USERNAME_QUERY = FIND_BY_USERNAME_QUERY + " AND pn.active = 'Y'";
	public static final String FIND_PRIMARY_BY_USERNAME = "PhoneNumber.findActiveByUsername";
	public static final String FIND_PRIMARY_BY_USERNAME_QUERY = FIND_ACTIVE_BY_USERNAME_QUERY + " AND pn.primary = 'Y'";
	public static final String SET_PRIMARY = "PhoneNumber.setPrimary";
	public static final String SET_PRIMARY_QUERY = "UPDATE PhoneNumber pn SET pn.primary = 'Y' WHERE pn.id = :id";
	public static final String RESET_PRIMARY = "PhoneNumber.resetPrimary";
	public static final String RESET_PRIMARY_QUERY = "UPDATE PhoneNumber pn SET pn.primary = 'N' WHERE pn.person.id = :id";
	public static final String DELETE_BY_ID = "PhoneNumber.deleteById";
	public static final String DELETE_BY_ID_QUERY = "UPDATE PhoneNumber pn SET pn.active = 'N' WHERE pn.id = :id";
	
	public static final String ID_PARAM = "id";
	public static final String USERNAME_PARAM = "username";
	
	PhoneNumber findById(Long id);
	List<PhoneNumber> findByUsername(String username);
	List<PhoneNumber> findActiveByUsername(String username);
	PhoneNumber findPrimaryByUsername(String username);
	void setPrimary(PhoneNumber phoneNumber);
	PhoneNumber save(PhoneNumber phoneNumber);
	void delete(PhoneNumber phoneNumber);
	void deleteById(Long id);
}
