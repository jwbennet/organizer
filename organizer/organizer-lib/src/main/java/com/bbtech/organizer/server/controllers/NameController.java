package com.bbtech.organizer.server.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bbtech.organizer.server.entities.Name;
import com.bbtech.organizer.server.services.NameService;
import com.bbtech.organizer.server.util.JsonResponse;
import com.bbtech.organizer.server.util.MimeTypes;
import com.google.common.base.CaseFormat;

@Controller
@RequestMapping("/names")
public class NameController {
	
	private static final String NAME_CREATED_MESSAGE = "Name created";
	private static final String NAME_UPDATED_MESSAGE = "Name updated";
	private static final String NAME_DELETED_MESSAGE = "Name deleted";

	@Autowired
	private NameService nameService;
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MimeTypes.JSON)
	@Transactional
	public @ResponseBody JsonResponse create(@RequestBody @Valid Name name, BindingResult result) {
		if(result.hasErrors()) {
			return processErrors(result);
		} else {
			return saveName(name, NAME_CREATED_MESSAGE);
		}
	}
	
	@RequestMapping(value = "{nameId}/display", method = RequestMethod.GET)
	@Transactional
	public ModelAndView display(@PathVariable Long nameId, ModelAndView model) {
		Name name = nameService.getName(nameId);
		model.addObject("name", name);
		model.setViewName("names/display");
		return model;
	}

	@RequestMapping(value = "{nameId}", method = RequestMethod.GET, produces = MimeTypes.JSON)
	@Transactional
	public @ResponseBody Name getNameJson(@PathVariable Long nameId) {
		Name name = nameService.getName(nameId);
		return name;
	}
	
	@RequestMapping(value = "{nameId}", method = RequestMethod.PUT, consumes = MimeTypes.JSON)
	@Transactional
	public @ResponseBody JsonResponse update(@RequestBody @Valid Name name, BindingResult result) {
		if(result.hasErrors()) {
			return processErrors(result);
		} else {
			return saveName(name, NAME_UPDATED_MESSAGE);
		}
	}
	
	@RequestMapping(value = "{nameId}", method = RequestMethod.DELETE)
	@Transactional
	public ModelAndView delete(@PathVariable Long nameId, ModelAndView model) {
		nameService.deleteNameById(nameId);
		model.addObject("id", nameId);
		model.addObject("success", true);
		model.addObject("message", NAME_DELETED_MESSAGE);
		model.setViewName("names/form-response");
		return model;
	}
	
	protected JsonResponse saveName(Name name, String message) {
		name = nameService.saveName(name);
		if(name.isPrimary()) {
			nameService.setPrimary(name);
		}
		return new JsonResponse(name.getId(), true, message, name);
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
	
	@ExceptionHandler(Exception.class)
	public String handleMyException(Exception exception) {
		exception.printStackTrace();
		return "index";
	}
}
