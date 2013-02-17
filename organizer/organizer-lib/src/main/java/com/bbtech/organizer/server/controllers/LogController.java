package com.bbtech.organizer.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bbtech.organizer.server.entities.Log;
import com.bbtech.organizer.server.entities.Person;
import com.bbtech.organizer.server.services.LogService;
import com.bbtech.organizer.server.services.PersonService;

@Controller
@RequestMapping("/logs")
public class LogController {

	@Autowired
	private LogService logService;
	
	@Autowired
	private PersonService personService;

	@Transactional
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView day(ModelAndView model, BindingResult bindingResult) {
		List<Log> logs = logService.getTodaysLogs();
		List<Person> people = personService.getActive();
		model.addObject("logs", logs);
		model.addObject("people", people);
		model.addObject("log", new Log());
		model.setViewName("logs/day");
		return model;
	}
	
	@Transactional
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("log") Log log, ModelAndView model, BindingResult bindingResult) {
		List<Log> logs = logService.getTodaysLogs();
		List<Person> people = personService.getActive();
		model.addObject("logs", logs);
		model.addObject("people", people);
		model.setViewName("logs/day");
		return model;
	}
}
