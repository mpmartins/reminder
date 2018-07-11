package com.mariopmartins.reminder.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mariopmartins.reminder.domain.Reminder;
import com.mariopmartins.reminder.domain.ReminderStatus;
import com.mariopmartins.reminder.repository.ReminderRepository;

@Service
public class ReminderServiceImpl implements ReminderService {

	@Autowired
	private ReminderRepository reminderRepository;

	@Override
	public Reminder findById(Long id) {
		Optional<Reminder> reminderOptional = reminderRepository.findById(id);
		if (!reminderOptional.isPresent()) {
			throw new ReminderNotFoundException();
		}
		return reminderOptional.get();
	}

	@Override
	public Iterable<Reminder> findAll(LocalDate dueDate, ReminderStatus status) {
		Reminder example = new Reminder();
		example.setDueDate(dueDate);
		example.setStatus(status);

		return reminderRepository.findAll(Example.of(example));
	}

	@Override
	public Reminder update(Long id, Reminder reminder) {
		Optional<Reminder> reminderOptional = reminderRepository.findById(id);
		if (!reminderOptional.isPresent()) {
			throw new ReminderNotFoundException();
		}
		reminder.setId(id);
		return save(reminder);
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
