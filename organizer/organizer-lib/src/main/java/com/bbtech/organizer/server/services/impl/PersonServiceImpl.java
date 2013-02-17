package com.bbtech.organizer.server.services.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbtech.organizer.server.dao.PersonDao;
import com.bbtech.organizer.server.entities.Person;
import com.bbtech.organizer.server.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Person> getAll() {
		return personDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Person> getActive() {
		List<Person> activePeople = personDao.findActive();
		Collections.sort(activePeople, new Comparator<Person>() {
			@Override
			public int compare(Person person1, Person person2) {
				return person1.getDisplayName().toUpperCase().compareTo(person2.getDisplayName().toUpperCase());
			}
		});
		return activePeople;
	}

	@Override
	@Transactional(readOnly=true)
	public Person getPerson(Long id) {
		return personDao.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Person getPersonByUsername(String username) {
		return personDao.findByUsername(username);
	}

	@Override
	public Person createPerson(String username) {
		Person person = new Person();
		person.setUsername(username);
		return personDao.save(person);
	}

	@Override
	@Transactional
	public Person savePerson(Person person) {
		return personDao.save(person);
	}

	@Override
	@Transactional
	public void deletePerson(Person person) {
		personDao.delete(person);
	}

	@Override
	@Transactional
	public void deletePersonById(Long id) {
		personDao.deleteById(id);
	}
}
