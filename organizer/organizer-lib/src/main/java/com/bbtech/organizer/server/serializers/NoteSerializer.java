package com.bbtech.organizer.server.serializers;

import java.io.IOException;

import com.bbtech.organizer.server.entities.Note;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class NoteSerializer extends JsonSerializer<Note> {

	@Override
	public void serialize(Note note, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
		generator.writeNumber(note.getId());
	}
}
