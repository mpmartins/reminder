package com.wallmart.labs.reminder.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wallmart.labs.reminder.domain.Reminder;
import com.wallmart.labs.reminder.repository.ReminderRepository;

@Service
@Transactional
public class ReminderServiceImpl implements ReminderService {

	@Autowired
	private ReminderRepository reminderRepository;
	
	@Override
	public Reminder getOne(Long id) {
		return reminderRepository.getOne(id);
	}

	@Override
	public Iterable<Reminder> findAll(Example<Reminder> example, Sort sort) {
		return reminderRepository.findAll(example, sort);
	}

	@Override
	public Iterable<Reminder> findAll() {
		return reminderRepository.findAll();
	}

	@Override
	public Reminder save(Reminder reminder) {
		return reminderRepository.save(reminder);
	}

	@Override
	public void deleteById(Long id) {
		reminderRepository.deleteById(id);
	}
	
}
