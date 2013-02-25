package com.bbtech.organizer.server.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bbtech.organizer.server.entities.Note;
import com.bbtech.organizer.server.services.NoteService;
import com.bbtech.organizer.server.services.PersonService;
import com.bbtech.organizer.server.util.JsonResponse;
import com.google.common.base.CaseFormat;

@Controller
@RequestMapping("/notes")
public class NoteController {

	private static final String NOTE_CREATED_MESSAGE = "Note created";
	private static final String NOTE_UPDATED_MESSAGE = "Note updated";
	private static final String NOTE_DELETED_MESSAGE = "Note deleted";

	@Autowired
	private NoteService noteService;

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody JsonResponse create(@ModelAttribute("note") @Valid Note note, BindingResult result) {
		if(result.hasErrors()) {
			return processErrors(result);
		} else {
			return saveNote(note, NOTE_CREATED_MESSAGE);
		}
	}

	@RequestMapping(value = "{noteId}", method = RequestMethod.GET)
	@Transactional
	public ModelAndView display(@PathVariable Long noteId, ModelAndView model) {
		Note note = noteService.getNote(noteId);
		model.addObject("note", note);
		model.setViewName("notes/display");
		return model;
	}

	@RequestMapping(value = "{noteId}.json", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public @ResponseBody Note getNoteJson(@PathVariable Long noteId) {
		Note note = noteService.getNote(noteId);
		return note;
	}

	@RequestMapping(value = "{noteId}", method = RequestMethod.PUT, consumes = "application/json")
	@Transactional
	public @ResponseBody JsonResponse update(@RequestBody @Valid Note note, BindingResult result) {
		if(result.hasErrors()) {
			return processErrors(result);
		} else {
			return saveNote(note, NOTE_UPDATED_MESSAGE);
		}
	}

	@RequestMapping(value = "{noteId}", method = RequestMethod.DELETE)
	@Transactional
	public @ResponseBody JsonResponse delete(@PathVariable Long noteId) {
		noteService.deleteNoteById(noteId);
		return new JsonResponse(noteId, true, NOTE_DELETED_MESSAGE);
	}

	protected JsonResponse saveNote(Note note, String message) {
		note = noteService.saveNote(note);
		return new JsonResponse(note.getId(), true, message, note);
	}

	protected JsonResponse processErrors(BindingResult result) {
		String message = "Errors found:\n";
		Map<String, String> errors = new HashMap<String, String>();
		for(FieldError error : result.getFieldErrors()) {
			errors.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, error.getField()), error.getDefaultMessage());
			message += error.getDefaultMessage() + "\n";
		}
		return new JsonResponse(0L, false, message, errors);
	}

	//	@ExceptionHandler(Exception.class)
	//	public String handleMyException(Exception exception) {
	//		exception.printStackTrace();
	//		return "index";
	//	}
}
