package com.bbtech.organizer.server.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbtech.organizer.server.dao.NoteDao;
import com.bbtech.organizer.server.entities.Note;
import com.bbtech.organizer.server.services.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteDao noteDao;

	@Override
	@Transactional(readOnly=true)
	public List<Note> getAll() {
		return noteDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Note getNote(Long id) {
		return noteDao.findById(id);
	}

	@Override
	@Transactional
	public Note createNote(String text) {
		Note note = new Note();
		note.setText(text);
		return noteDao.save(note);
	}

	@Override
	@Transactional
	public Note saveNote(Note note) {
		return noteDao.save(note);
	}

	@Override
	@Transactional
	public void deleteNote(Note note) {
		noteDao.delete(note);
	}

	@Override
	@Transactional
	public void deleteNoteById(Long id) {
		noteDao.deleteById(id);
	}
}
