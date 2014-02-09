package com.bbtech.organizer.server.deserializers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbtech.organizer.server.entities.Log;
import com.bbtech.organizer.server.services.LogService;
import com.bbtech.organizer.server.util.ServiceLocator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Component
public class LogDeserializer extends JsonDeserializer<Log> {

	@Autowired
	private LogService logService;
	
	@Override
	public Log deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		Long logId = Long.parseLong(parser.getText());
		return this.getLogService().getLog(logId);
	}

	public LogService getLogService() {
		if(logService == null) {
			this.logService = ServiceLocator.getLogService();
		}
		return logService;
	}
}
