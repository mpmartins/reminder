package com.wallmart.labs.reminder.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallmart.labs.reminder.domain.Reminder;
import com.wallmart.labs.reminder.domain.ReminderStatus;
import com.wallmart.labs.reminder.repository.ReminderRepository;

@RestController
@RequestMapping("/reminder")
public class ReminderController {

	@Autowired
	private ReminderRepository reminderRepository;

	@PostMapping(value = "/find")
	public ResponseEntity<Iterable<Reminder>> find(
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dueDate, 
			@RequestParam(required=false) ReminderStatus status) {
		
		Reminder reminder = new Reminder();
		reminder.setDueDate(dueDate);
		reminder.setStatus(status);

		Iterable<Reminder> list = reminderRepository.findAll(Example.of(reminder), Sort.by("dueDate"));
		
		return new ResponseEntity<Iterable<Reminder>>(list, HttpStatus.OK);
	
	}

}
