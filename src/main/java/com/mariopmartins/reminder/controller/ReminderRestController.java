package com.mariopmartins.reminder.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mariopmartins.reminder.domain.Reminder;
import com.mariopmartins.reminder.domain.ReminderStatus;
import com.mariopmartins.reminder.repository.ReminderRepository;

@RestController
@RequestMapping("/api/reminders")
public class ReminderRestController {

	@Autowired
	private ReminderRepository reminderRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<Reminder> get(@PathVariable(required=true) Long id) {
		return ResponseEntity.ok(reminderRepository.getOne(id));
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Reminder>> list(
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dueDate,
			@RequestParam(required = false) ReminderStatus status) {

		Reminder reminder = new Reminder();
		reminder.setDueDate(dueDate);
		reminder.setStatus(status);
		
		Iterable<Reminder> list = reminderRepository.findAll(Example.of(reminder));
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<Reminder> add(@RequestBody @Valid Reminder reminder) {
		return ResponseEntity.ok(reminderRepository.save(reminder));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Reminder> update(@PathVariable(required=true) Long id, @RequestBody @Valid Reminder reminder) {
		Optional<Reminder> reminderOptional = reminderRepository.findById(id);
		if (!reminderOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		reminder.setId(id);
		Reminder savedReminder = reminderRepository.save(reminder);
		return ResponseEntity.ok(savedReminder);
	}

}