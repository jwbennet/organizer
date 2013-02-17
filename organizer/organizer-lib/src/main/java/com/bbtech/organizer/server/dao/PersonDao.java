package com.bbtech.organizer.server.dao;

import java.util.List;

import com.bbtech.organizer.server.entities.Person;

public interface PersonDao {

	public static final String FIND_ALL = "Person.findAll";
	public static final String FIND_ALL_QUERY = "SELECT p FROM Person p";
	public static final String FIND_ACTIVE = "Person.findActive";
	public static final String FIND_ACTIVE_QUERY = "SELECT p FROM Person p WHERE p.active = 'Y'";
	public static final String FIND_BY_ID = "Person.findById";
	public static final String FIND_BY_ID_QUERY = "SELECT p FROM Person p WHERE p.id = :id";
	public static final String FIND_BY_USERNAME = "Person.findByUsername";
	public static final String FIND_BY_USERNAME_QUERY = "SELECT p FROM Person p WHERE p.username = :username";
	public static final String DELETE_BY_ID = "Person.deleteById";
	public static final String DELETE_BY_ID_QUERY = "UPDATE Person p SET p.active = 'N' WHERE p.id = :id";
	
	public static final String ID_PARAM = "id";
	public static final String USERNAME_PARAM = "username";
	
	Person findById(Long id);
	Person findByUsername(String username);
	List<Person> findAll();
	List<Person> findActive();
	Person save(Person person);
	void delete(Person person);
	void deleteById(Long id);
}
