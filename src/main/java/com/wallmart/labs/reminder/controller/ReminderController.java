package com.wallmart.labs.reminder.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.wallmart.labs.reminder.domain.Reminder;
import com.wallmart.labs.reminder.domain.ReminderStatus;
import com.wallmart.labs.reminder.repository.ReminderRepository;

@RestController
@RequestMapping("/reminder")
public class ReminderController {

	@Autowired
	private ReminderRepository reminderRepository;

	@GetMapping("/{id}")
	public ResponseEntity<Reminder> get(@PathVariable("id") Long id) {
		Reminder reminder = reminderRepository.getOne(id);
		return new ResponseEntity<Reminder>(reminder, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Iterable<Reminder>> all() {
		return new ResponseEntity<Iterable<Reminder>>(reminderRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/filter")
	public ResponseEntity<Iterable<Reminder>> filter(
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dueDate, 
			@RequestParam(required=false) ReminderStatus status) {
		
		Reminder reminder = new Reminder();
		reminder.setDueDate(dueDate);
		reminder.setStatus(status);

		Iterable<Reminder> list = reminderRepository.findAll(Example.of(reminder), Sort.by("dueDate"));
		
		
		return new ResponseEntity<Iterable<Reminder>>(list, HttpStatus.OK);
	
	}

	@PostMapping(value = "/add")
	public ResponseEntity<Void> add(
			@RequestBody Reminder reminder, 
			UriComponentsBuilder builder) {
		Reminder savedReminder = reminderRepository.save(reminder);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/reminder/{id}").buildAndExpand(savedReminder.getReminderId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        	
	}
	
	@PutMapping("/update")
	public ResponseEntity<Reminder> updateArticle(@RequestBody Reminder reminder) {
		reminderRepository.save(reminder);
		return new ResponseEntity<Reminder>(reminder, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		reminderRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}		
}
