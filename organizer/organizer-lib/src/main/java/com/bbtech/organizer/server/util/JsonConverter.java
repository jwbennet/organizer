package com.bbtech.organizer.server.util;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
	
	private static final Logger LOG = Logger.getLogger(JsonConverter.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static String convert(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			LOG.error("JSON exception serializing object", e);
			return "";
		}
	}
}
