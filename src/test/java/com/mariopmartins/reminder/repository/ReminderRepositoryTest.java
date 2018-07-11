package com.mariopmartins.reminder.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.mariopmartins.reminder.domain.Reminder;
import com.mariopmartins.reminder.domain.ReminderStatus;
import com.mariopmartins.reminder.repository.ReminderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReminderRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private ReminderRepository reminderRepository;
 
    @Test
    public void whenFindOne_thenReturnReminder() {
        // given
        Reminder reminder = new Reminder();
        reminder.setId(1L);
        reminder.setName("Test");
        reminder.setStatus(ReminderStatus.NOTDONE);
        entityManager.persist(reminder);
        entityManager.flush();
     
        // when
        Optional<Reminder> found = reminderRepository.findById(reminder.getId());
     
        // then
        assertThat(found.get().getName()).isEqualTo(reminder.getName());
    }
 
}