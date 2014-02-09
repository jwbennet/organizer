package com.bbtech.organizer.server.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
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

import com.bbtech.organizer.server.entities.Log;
import com.bbtech.organizer.server.entities.Person;
import com.bbtech.organizer.server.services.LogService;
import com.bbtech.organizer.server.services.PersonService;
import com.bbtech.organizer.server.util.Formats;
import com.bbtech.organizer.server.util.JsonConverter;
import com.bbtech.organizer.server.util.JsonResponse;
import com.bbtech.organizer.server.util.MimeTypes;
import com.google.common.base.CaseFormat;

@Controller
@RequestMapping("/logs")
public class LogController {
	
	private static final String LOG_CREATED_MESSAGE = "Log created";
	private static final String LOG_UPDATED_MESSAGE = "Log updated";
	private static final String LOG_DELETED_MESSAGE = "Log deleted";

	@Autowired
	private LogService logService;
	
	@Autowired
	private PersonService personService;

	@Transactional
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView today(ModelAndView model, BindingResult bindingResult) {
		List<Log> logs = logService.getTodaysLogs();
		Map<Long, Log> logsMap = new HashMap<Long, Log>();
		for(Log log : logs) {
			logsMap.put(log.getId(), log);
		}
		List<Person> people = personService.getActive();
		model.addObject("logs", logs);
		model.addObject("logsJson", JsonConverter.convert(logsMap));
		model.addObject("people", people);
		model.setViewName("logs/day");
		return model;
	}
	
	@Transactional
	@RequestMapping(value = "{key}", method = RequestMethod.GET)
	public ModelAndView day(@PathVariable String key, ModelAndView model, BindingResult bindingResult) {
		if(StringUtils.isNumeric(key)) {
			Long logId = Long.parseLong(key);
			Log log = logService.getLog(logId);
			model.addObject("log", log);
			model.setViewName("logs/detail");
		} else {
			DateTime start = Formats.DATE_FORMAT.parseDateTime(key);
			DateTime end = new DateTime(start.getMillis() + DateTimeConstants.MILLIS_PER_DAY);
			List<Log> logs = logService.getLogsByDateRange(start, end);
			Map<Long, Log> logsMap = new HashMap<Long, Log>();
			for(Log log : logs) {
				logsMap.put(log.getId(), log);
			}
			List<Person> people = personService.getActive();
			model.addObject("logs", logs);
			model.addObject("logsJson", JsonConverter.convert(logsMap));
			model.addObject("people", people);
			model.setViewName("logs/day");
		}
		return model;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MimeTypes.JSON)
	@Transactional
	public @ResponseBody JsonResponse create(@RequestBody @Valid Log log, BindingResult result) {
		if(result.hasErrors()) {
			return processErrors(result);
		} else {
			return saveLog(log, LOG_CREATED_MESSAGE);
		}
	}
	
	@RequestMapping(value = "{logId}", method = RequestMethod.PUT, consumes = MimeTypes.JSON)
	@Transactional
	public @ResponseBody JsonResponse update(@RequestBody @Valid Log log, BindingResult result) {
		if(result.hasErrors()) {
			return processErrors(result);
		} else {
			return saveLog(log, LOG_UPDATED_MESSAGE);
		}
	}

	@RequestMapping(value = "{logId}", method = RequestMethod.DELETE)
	@Transactional
	public @ResponseBody JsonResponse delete(@PathVariable Long logId) {
		logService.deleteLogById(logId);
		return new JsonResponse(logId, true, LOG_DELETED_MESSAGE);
	}
	
	@RequestMapping(value = "{logId}/display", method = RequestMethod.GET)
	@Transactional
	public ModelAndView display(@PathVariable Long logId, ModelAndView model) {
		Log log = logService.getLog(logId);
		model.addObject("log", log);
		model.setViewName("logs/display");
		return model;
	}
	
	protected JsonResponse saveLog(Log log, String message) {
		log = logService.saveLog(log);
		return new JsonResponse(log.getId(), true, message, log);
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
