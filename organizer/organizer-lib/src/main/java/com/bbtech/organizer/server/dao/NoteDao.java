package com.bbtech.organizer.server.dao;

import java.util.List;

import com.bbtech.organizer.server.entities.Note;

public interface NoteDao {
	
	public static final String FIND_ALL = "Note.findAll";
	public static final String FIND_ALL_QUERY = "SELECT n FROM Note n";
	public static final String FIND_BY_ID = "Note.findById";
	public static final String FIND_BY_ID_QUERY = "SELECT n FROM Note n WHERE n.id = :id";
	public static final String DELETE_BY_ID = "Note.deleteById";
	public static final String DELETE_BY_ID_QUERY = "DELETE FROM Note n WHERE n.id = :id";
	
	public static final String ID_PARAM = "id";

	Note findById(Long id);
	List<Note> findAll();
	Note save(Note note);
	void delete(Note note);
	void deleteById(Long id);
}
