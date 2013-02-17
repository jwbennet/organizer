package com.bbtech.organizer.server.services;

import java.util.List;

import com.bbtech.organizer.server.entities.Note;

public interface NoteService {

	List<Note> getAll();
	Note getNote(Long id);
	Note createNote(String text);
	Note saveNote(Note note);
	void deleteNote(Note note);
	void deleteNoteById(Long id);
}
