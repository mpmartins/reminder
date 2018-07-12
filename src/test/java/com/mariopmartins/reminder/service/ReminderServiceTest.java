package com.mariopmartins.reminder.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.domain.Example;

import com.mariopmartins.reminder.domain.Reminder;
import com.mariopmartins.reminder.domain.ReminderStatus;
import com.mariopmartins.reminder.repository.ReminderRepository;

@RunWith(SpringRunner.class)
public class ReminderServiceTest {
 
    @Autowired
    private ReminderService reminderService;

    @MockBean
    private ReminderRepository reminderRepository;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public ReminderService employeeService() {
            return new ReminderServiceImpl();
        }
    }
 
    
	@Test
    public void givenReminders_whenFindAll_thenReturnList() throws Exception {
         
    	Reminder reminder = new Reminder();
        reminder.setId(1L);
        reminder.setName("Test");
        reminder.setStatus(ReminderStatus.NOTDONE);
        List<Reminder> all = Arrays.asList(reminder);
     
        Reminder example = new Reminder();
        
        when(reminderRepository.findAll(Example.of(example))).thenReturn(all);
     
        Iterable<Reminder> reminders = reminderService.findAll(null, null);
        assertThat(reminders).isEqualTo(all);
        
    }
    
}