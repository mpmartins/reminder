package com.mariopmartins.reminder.service;

import java.time.LocalDate;

import com.mariopmartins.reminder.domain.Reminder;
import com.mariopmartins.reminder.domain.ReminderStatus;

public interface ReminderService {

	Reminder findById(Long id);

	Iterable<Reminder> findAll(LocalDate dueDate, ReminderStatus status);

	Reminder update(Long id, Reminder reminder);
	
	Reminder save(Reminder reminder);

	void deleteById(Long id);

}
