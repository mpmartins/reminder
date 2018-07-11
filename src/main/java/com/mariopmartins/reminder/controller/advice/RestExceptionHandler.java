package com.mariopmartins.reminder.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mariopmartins.reminder.domain.ApiError;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(ex.getLocalizedMessage(), ex));
	}

}