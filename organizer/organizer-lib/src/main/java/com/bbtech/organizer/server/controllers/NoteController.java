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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bbtech.organizer.server.entities.Note;
import com.bbtech.organizer.server.services.NoteService;
import com.bbtech.organizer.server.services.PersonService;
import com.google.common.base.CaseFormat;

import flexjson.JSONSerializer;

@Controller
@RequestMapping("/notes")
public class NoteController {

	@Autowired
	private NoteService noteService;
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@Transactional
	public ModelAndView update(@ModelAttribute("note") @Valid Note note, BindingResult result, ModelAndView model) {
		String message = "";
		boolean success = false;
		if(result.hasErrors()) {
			message = "Errors found:\\n";
			Map<String, String> errors = new HashMap<String, String>();
			for(FieldError error : result.getFieldErrors()) {
				System.out.println(error.getObjectName() + " " + error.getField() + ": " + error.getDefaultMessage());
				errors.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, error.getField()), error.getDefaultMessage());
				message += error.getDefaultMessage() + "\\n";
			}
			System.out.println(new JSONSerializer().serialize(errors));
		} else {
			if(note.getId() == null) {
				message = "Note created";
			} else {
				message = "Note updated";
			}
			success = true;
			note = noteService.saveNote(note);
		}
		model.addObject("id", note.getId());
		System.out.println("BENNETT: success = " + success);
		model.addObject("success", success);
		System.out.println("BENNETT: message = " + message);
		model.addObject("message", message);
		model.setViewName("notes/form-response");
		return model;
	}
	
	@ModelAttribute("note")
	public Note getNote(@RequestParam(value = "id", required = false) Long id) {
		if(id == null) {
			return new Note();
		} else {
		    return this.noteService.getNote(id);
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
	
	@RequestMapping(value = "{noteId}", method = RequestMethod.DELETE)
	@Transactional
	public ModelAndView delete(@PathVariable Long noteId, ModelAndView model) {
		noteService.deleteNoteById(noteId);
		model.addObject("id", noteId);
		model.addObject("success", true);
		model.addObject("message", "Note deleted");
		model.setViewName("notes/form-response");
		return model;
	}
	
//	@ExceptionHandler(Exception.class)
//	public String handleMyException(Exception  exception) {
//		exception.printStackTrace();
//		return "index";
//	}
}
