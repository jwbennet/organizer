package com.bbtech.organizer.server.serializers;

import java.io.IOException;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.bbtech.organizer.server.util.Formats;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class DateTimeSerializer extends JsonSerializer<DateTime> {

	@Override
	public void serialize(DateTime date, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
		String formattedDate = date.toString(Formats.DATETIME_FORMAT);
		generator.writeString(formattedDate);
	}
}
