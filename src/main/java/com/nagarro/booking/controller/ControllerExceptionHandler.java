package com.nagarro.booking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nagarro.booking.dto.ResponseTO;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ResponseTO<String>> processExceptionHandler(Exception ex) {
		ResponseTO<String> errorMessageTOResponseTO = new ResponseTO<>();
		errorMessageTOResponseTO.setError(true);
		List<Error> errors = new ArrayList<>();
		Error error = new Error(ex.getMessage());
		errors.add(error);
		errorMessageTOResponseTO.setErrors(errors);
		return new ResponseEntity<>(errorMessageTOResponseTO, HttpStatus.NOT_FOUND);
	}
}
