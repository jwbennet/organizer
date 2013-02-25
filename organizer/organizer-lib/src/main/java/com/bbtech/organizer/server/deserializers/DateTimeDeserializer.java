package com.bbtech.organizer.server.deserializers;

import java.io.IOException;

import org.joda.time.DateTime;

import com.bbtech.organizer.server.util.Formats;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateTimeDeserializer extends JsonDeserializer<DateTime> {

	@Override
	public DateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		String formattedDate = parser.getText();
		return Formats.DATETIME_FORMAT.parseDateTime(formattedDate);
	}
}
