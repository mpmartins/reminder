package com.mariopmartins.reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mariopmartins.reminder.domain.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

}
