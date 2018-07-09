package com.wallmart.labs.reminder.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.wallmart.labs.reminder.domain.Reminder;

public interface ReminderService {

	Reminder getOne(Long id);

	Iterable<Reminder> findAll(Example<Reminder> example, Sort sort);

	Iterable<Reminder> findAll();

	Reminder save(Reminder reminder);

	void deleteById(Long id);

	
}
