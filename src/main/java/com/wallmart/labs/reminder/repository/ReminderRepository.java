package com.wallmart.labs.reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallmart.labs.reminder.domain.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

}
