package com.bbtech.organizer.server.serializers;

import java.io.IOException;

import com.bbtech.organizer.server.entities.Log;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LogSerializer extends JsonSerializer<Log> {

	@Override
	public void serialize(Log log, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
		generator.writeNumber(log.getId());
	}
}
