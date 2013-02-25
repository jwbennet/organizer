package com.bbtech.organizer.server.serializers;

import java.io.IOException;

import com.bbtech.organizer.server.entities.Person;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PersonSerializer extends JsonSerializer<Person> {

	@Override
	public void serialize(Person person, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
		generator.writeNumber(person.getId());
	}
}
