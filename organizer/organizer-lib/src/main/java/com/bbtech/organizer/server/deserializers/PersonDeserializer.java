package com.bbtech.organizer.server.deserializers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbtech.organizer.server.entities.Person;
import com.bbtech.organizer.server.services.PersonService;
import com.bbtech.organizer.server.util.ServiceLocator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Component
public class PersonDeserializer extends JsonDeserializer<Person> {

	@Autowired
	private PersonService personService;
	
	@Override
	public Person deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		Long personId = Long.parseLong(parser.getText());
		return this.getPersonService().getPerson(personId);
	}

	public PersonService getPersonService() {
		if(personService == null) {
			this.personService = ServiceLocator.getPersonService();
		}
		return personService;
	}
}
