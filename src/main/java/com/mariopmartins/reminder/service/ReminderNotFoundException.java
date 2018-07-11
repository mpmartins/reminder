package com.mariopmartins.reminder.service;

public class ReminderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3764387355573531220L;

	public ReminderNotFoundException() {
		super("Reminder Not Found");
	}
}
