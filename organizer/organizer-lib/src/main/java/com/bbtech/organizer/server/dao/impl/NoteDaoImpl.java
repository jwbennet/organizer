package com.bbtech.organizer.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbtech.organizer.server.dao.NoteDao;
import com.bbtech.organizer.server.entities.Note;
import com.bbtech.organizer.server.services.WikiService;

@Repository
public class NoteDaoImpl implements NoteDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private WikiService wikiService;
	
	@Override
	public Note findById(Long id) {
		Query q = entityManager.createNamedQuery(FIND_BY_ID);
		q.setParameter(ID_PARAM, id);
		Note note = (Note)q.getSingleResult();
		parseWikiText(note);
		return note;
	}

	@Override
	public List<Note> findAll() {
		Query q = entityManager.createNamedQuery(FIND_ALL);
		List<?> results = q.getResultList();
		List<Note> notes = new ArrayList<Note>(results.size());
		for(Object result : results) {
			if(result instanceof Note) {
				Note note = (Note)result;
				parseWikiText(note);
				notes.add(note);
			}
		}
		return notes;
	}

	@Override
	public Note save(Note note) {
		if(note == null) {
			return null;
		}
		try {
            if (note.getId() == null) {
                entityManager.persist(note);
                entityManager.flush();
            } else {
                entityManager.merge(note);
            }
        } catch (OptimisticLockException oe) {
            oe.printStackTrace();
            throw oe;
        }
		parseWikiText(note);
		return note;
	}

	@Override
	public void delete(Note note) {
		if(note == null || note.getId() == null) {
			return;
		}
		deleteById(note.getId());
	}

	@Override
	public void deleteById(Long id) {
		Query q = entityManager.createNamedQuery(DELETE_BY_ID);
		q.setParameter(ID_PARAM, id);
		q.executeUpdate();
	}
	
	private void parseWikiText(Note note) {
//		note.setWikiText(wikiService.parse(note.getText()));
	}
}
