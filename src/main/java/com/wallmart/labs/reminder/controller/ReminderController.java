package com.wallmart.labs.reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallmart.labs.reminder.domain.Reminder;
import com.wallmart.labs.reminder.repository.ReminderRepository;

@RestController
@RequestMapping("/reminder")
public class ReminderController {

	@Autowired
	private ReminderRepository reminderRepository;

	@PostMapping(value = "/find")
	public ResponseEntity<Iterable<Reminder>> find(@RequestBody Reminder reminder) {
		Example<Reminder> example = Example.of(reminder);
		Iterable<Reminder> list = reminderRepository.findAll(example);
		return new ResponseEntity<Iterable<Reminder>>(list, HttpStatus.OK);
	}

}
