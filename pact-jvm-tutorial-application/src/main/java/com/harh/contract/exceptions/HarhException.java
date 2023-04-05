package com.harh.contract.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "cause", "stackTrace", "suppressed", "localizedMessage" })
public class HarhException extends Exception {
	
	private static final long serialVersionUID = -3900627160774195729L;
	
	public HarhException(String message) {
		super(message);
	}
}
