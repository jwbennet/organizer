package com.bbtech.organizer.server.deserializers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbtech.organizer.server.entities.Note;
import com.bbtech.organizer.server.services.NoteService;
import com.bbtech.organizer.server.util.ServiceLocator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Component
public class NoteDeserializer extends JsonDeserializer<Note> {

	@Autowired
	private NoteService noteService;
	
	@Override
	public Note deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		Long noteId = Long.parseLong(parser.getText());
		return this.getNoteService().getNote(noteId);
	}

	public NoteService getNoteService() {
		if(noteService == null) {
			this.noteService = ServiceLocator.getNoteService();
		}
		return noteService;
	}
}
