package com.wallmart.labs.reminder;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import com.wallmart.labs.reminder.domain.Reminder;
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
    	reminderRepository.save(new Reminder("Wash clothes", "", LocalDate.now()));
    	reminderRepository.save(new Reminder("Finish party invitations", "", LocalDate.now()));
    }
}
