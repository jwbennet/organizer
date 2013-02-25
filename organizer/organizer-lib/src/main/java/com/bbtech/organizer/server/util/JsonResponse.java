package com.bbtech.organizer.server.util;

import java.util.HashMap;
import java.util.Map;

public class JsonResponse {

	private Long id;
	private boolean success;
	private String message;
	private Map<String, String> errors;
	private Object object;
	
	public JsonResponse() {
		this.errors = new HashMap<String, String>();
	}
	
	public JsonResponse(Long id, boolean success, String message) {
		this();
		this.setId(id);
		this.setSuccess(success);
		this.setMessage(message);
	}
	
	public JsonResponse(Long id, boolean success, String message, Map<String, String> errors) {
		this(id, success, message);
		this.setErrors(errors);
	}
	
	public JsonResponse(Long id, boolean success, String message, Object object) {
		this(id, success, message);
		this.setObject(object);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public Object getObject() {
		return object;
	}
	
	public void setObject(Object object) {
		this.object = object;
	}
}
