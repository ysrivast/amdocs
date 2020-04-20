package com.uxpsystems.assignment.exceptions.handler;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.uxpsystems.assignment.exceptions.ErrorDetails;
import com.uxpsystems.assignment.exceptions.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<ErrorDetails> handleRunTimeException(RuntimeException e) {
		ErrorDetails errorDetails = null;
		if (e instanceof DataIntegrityViolationException) {
			errorDetails = new ErrorDetails(new Date(), "USer already present.");
		} else {
			errorDetails = new ErrorDetails(new Date(), "Something went wrong! Kindly contact admin");
		}
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ UserNotFoundException.class })
	public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException e) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
