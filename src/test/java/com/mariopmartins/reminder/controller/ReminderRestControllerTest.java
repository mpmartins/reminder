package com.mariopmartins.reminder.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mariopmartins.reminder.domain.Reminder;
import com.mariopmartins.reminder.domain.ReminderStatus;
import com.mariopmartins.reminder.service.ReminderService;

@RunWith(SpringRunner.class)
@WebMvcTest(ReminderRestController.class)
public class ReminderRestControllerTest {
 
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private ReminderService reminderService;

	private ObjectWriter jonObjectWriter;
 
	@Before
    public void setup () {
        jonObjectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }
	
	@Test
    public void givenReminders_whenGet_thenReturnJsonArray() throws Exception {
         
    	Reminder reminder = new Reminder();
        reminder.setId(1L);
        reminder.setName("Test");
        reminder.setStatus(ReminderStatus.NOTDONE);
        List<Reminder> all = Arrays.asList(reminder);
     
        when(reminderService.findAll(null, null)).thenReturn(all);
     
        mvc.perform(get("/api/reminders")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].name").value(reminder.getName()));
        
    }
    
    @Test
    public void givenReminders_whenGetWithId_thenReturnJson() throws Exception {
         
    	Reminder reminder = new Reminder();
        reminder.setId(1L);
        reminder.setName("Test");
        reminder.setStatus(ReminderStatus.NOTDONE);
     
        when(reminderService.findById(reminder.getId())).thenReturn(reminder);
     
        mvc.perform(get("/api/reminders/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.name").value(reminder.getName()));
        
    }
    
    @Test
    public void givenReminders_whenAdd_thenReturnJsonArray() throws Exception {
         
    	Reminder reminder = new Reminder();
        reminder.setId(1L);
        reminder.setName("Test");
        reminder.setStatus(ReminderStatus.NOTDONE);
     
        when(reminderService.save(reminder)).thenReturn(reminder);
     
        String requestJson=jonObjectWriter.writeValueAsString(reminder);
        
        mvc.perform(post("/api/reminders")
          .content(requestJson)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.name").value(reminder.getName()));
        
    }

    @Test
    public void givenReminders_whenPutWithId_thenReturnJsonArray() throws Exception {
         
    	Reminder reminder = new Reminder();
        reminder.setId(1L);
        reminder.setName("Test");
        reminder.setStatus(ReminderStatus.NOTDONE);
     
        when(reminderService.findById(reminder.getId())).thenReturn(reminder);
     
        String requestJson=jonObjectWriter.writeValueAsString(reminder);
        
        mvc.perform(put("/api/reminders/1")
          .content(requestJson)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
        
    }
    
}