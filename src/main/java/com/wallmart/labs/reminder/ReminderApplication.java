package com.wallmart.labs.reminder;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import com.wallmart.labs.reminder.domain.Reminder;
import com.wallmart.labs.reminder.domain.ReminderStatus;
import com.wallmart.labs.reminder.repository.ReminderRepository;

@SpringBootApplication
public class ReminderApplication implements ApplicationListener<ApplicationReadyEvent> {

	public static void main(String[] args) {
		SpringApplication.run(ReminderApplication.class, args);
	}

	@Autowired
	private ReminderRepository reminderRepository;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		seedData();
	}

	private void seedData() {
		Reminder reminder = new Reminder("Finish first version", "", LocalDate.of(2018, 7, 7), ReminderStatus.DONE);
		reminderRepository.save(reminder);
		
		reminderRepository.save(new Reminder("Add Swagger-UI", "", LocalDate.of(2018, 7, 7)));
		
		reminderRepository.save(new Reminder("Send e-mail with repository link", "", LocalDate.of(2018, 7, 12)));
		
		reminderRepository.save(new Reminder("Wash clothes", "", LocalDate.of(2018, 7, 7), ReminderStatus.DONE));
		
		reminderRepository.save(new Reminder("Finish barbeque invitations", "", LocalDate.of(2018, 7, 9)));
	}
}
