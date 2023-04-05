package com.harh.contract.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.harh.contract.commons.User;
import com.harh.contract.exceptions.HarhException;
import com.harh.contract.service.impl.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUsers() {		
		return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }
	
	@PostMapping(value = "/api/v1/user", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE
	        )
	public ResponseEntity<Object> addUser(@Valid @RequestBody User newUser) {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<User>> violations = validator.validate(newUser);
		List<String> validationErrors = new ArrayList<String>();
		for (ConstraintViolation<User> violation : violations) {    	    
			validationErrors.add(violation.getMessage()); 
    	}
		if ( validationErrors.size() > 0 ) {
			return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
		}

		if (userServiceImpl.addUser(newUser)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(new HarhException("User already exists"), HttpStatus.BAD_REQUEST);
		
	}
	
	@DeleteMapping(path="/api/v1/users")
	public ResponseEntity<Object> deleteUsers() {
		userServiceImpl.resetUsers();
		return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
