package com.mariopmartins.reminder.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mariopmartins.reminder.domain.ApiError;
import com.mariopmartins.reminder.service.ReminderNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleRuntimeException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(ex.getLocalizedMessage(), ex));
	}

	@ExceptionHandler(ReminderNotFoundException.class)
	protected ResponseEntity<Object> handleReminderNotFoundException(ReminderNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(ex.getLocalizedMessage(), ex));
	}
}