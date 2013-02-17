package com.bbtech.organizer.server.services;

import java.util.List;

import com.bbtech.organizer.server.entities.Person;

public interface PersonService {

	List<Person> getAll();
	List<Person> getActive();
	Person getPerson(Long id);
	Person getPersonByUsername(String username);
	Person createPerson(String username);
	Person savePerson(Person person);
	void deletePerson(Person person);
	void deletePersonById(Long id);
}
