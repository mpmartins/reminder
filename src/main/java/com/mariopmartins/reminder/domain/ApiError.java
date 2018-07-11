package com.mariopmartins.reminder.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;

	public ApiError(String message, Throwable ex) {
		this.timestamp = LocalDateTime.now();
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}