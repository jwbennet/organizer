package com.bbtech.organizer.server.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.bbtech.organizer.server.entities.Note;
import com.bbtech.organizer.server.services.NoteService;

@Component
public class NoteFormatter implements Formatter<Note> {

	@Autowired
	private NoteService noteService;
	
	@Override
	public String print(Note note, Locale locale) {
		return note.toString();
	}

	@Override
	public Note parse(String noteId, Locale locale) throws ParseException {
		if(StringUtils.isNumeric(noteId)) {
			return this.noteService.getNote(Long.parseLong(noteId));
		}
		return new Note();
	}
}
