package com.bbtech.organizer.server.controllers;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bbtech.organizer.server.entities.Email;
import com.bbtech.organizer.server.entities.Person;
import com.bbtech.organizer.server.services.EmailService;
import com.bbtech.organizer.server.services.PersonService;

@Controller
@RequestMapping("/emails")
public class EmailController {

	@Autowired
	private EmailService emailService;
	@Autowired
	private PersonService personService;

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@Transactional
	public ModelAndView update(@ModelAttribute("email") Email email, ModelAndView model, ServletRequest request) {
		String personId = request.getParameter("person.id");
		String message = "";
		boolean success = false;
		if(StringUtils.isEmpty(personId)) {
			message = "Missing person ID";
		} else {
			Person person = personService.getPerson(Long.valueOf(personId));
			email.setPerson(person);
			if(email.getId() == null) {
				message = "Email created";
			} else {
				message = "Email updated";
			}
			success = true;
			if(email.isPrimary()) {
				emailService.setPrimary(email);
			}
			email = emailService.saveEmail(email);

		}
		model.addObject("id", email.getId());
		model.addObject("success", success);
		model.addObject("message", message);
		model.setViewName("emails/form-response");
		return model;
	}
	
	@RequestMapping(value = "{emailId}", method = RequestMethod.GET)
	@Transactional
	public ModelAndView display(@PathVariable Long emailId, ModelAndView model) {
		Email email = emailService.getEmail(emailId);
		model.addObject("email", email);
		model.setViewName("emails/display");
		return model;
	}
	
	@RequestMapping(value = "{emailId}", method = RequestMethod.DELETE)
	@Transactional
	public ModelAndView delete(@PathVariable Long emailId, ModelAndView model) {
		emailService.deleteEmailById(emailId);
		model.addObject("id", emailId);
		model.addObject("success", true);
		model.addObject("message", "Email deleted");
		model.setViewName("emails/form-response");
		return model;
	}
}
