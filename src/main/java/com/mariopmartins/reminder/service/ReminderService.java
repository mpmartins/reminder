package com.mariopmartins.reminder.service;

import java.time.LocalDate;

import com.mariopmartins.reminder.domain.Reminder;
import com.mariopmartins.reminder.domain.ReminderStatus;

public interface ReminderService {

	/**
	 * Returns a Reminder
	 * @throws ReminderNotFoundException
	 */
	Reminder findById(Long id) throws ReminderNotFoundException;

	/**
	 * Returns a Collection of Reminders filtered by dueDate and/or status.
	 * if any of the parameters is null, that parameter is not considered to filter the search.
	 */
	Iterable<Reminder> findAll(LocalDate dueDate, ReminderStatus status);

	/**
	 * Updates a reminder based on id.
	 * @throws ReminderNotFoundException
	 */
	Reminder update(Long id, Reminder reminder) throws ReminderNotFoundException;
	
	/**
	 * Saves the reminder object.
	 * @return saved Reminder object
	 */
	Reminder save(Reminder reminder);

	/**
	 * Deletes the reminder based on the id
	 */
	void deleteById(Long id);

}
