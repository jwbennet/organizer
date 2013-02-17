package com.bbtech.organizer.server.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.bbtech.organizer.server.entities.Person;
import com.bbtech.organizer.server.services.PersonService;

@Component
public class PersonFormatter implements Formatter<Person> {

	@Autowired
	private PersonService personService;
	
	@Override
	public String print(Person person, Locale locale) {
		return person.toString();
	}

	@Override
	public Person parse(String personId, Locale locale) throws ParseException {
		if(StringUtils.isNumeric(personId)) {
			return this.personService.getPerson(Long.parseLong(personId));
		}
		return null;
	}
}
