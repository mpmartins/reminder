package com.mariopmartins.reminder.controller.advice;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mariopmartins.reminder.controller.ReminderRestController;
import com.mariopmartins.reminder.service.ReminderNotFoundException;
import com.mariopmartins.reminder.service.ReminderService;

@RunWith(SpringRunner.class)
@WebMvcTest(ReminderRestController.class)
public class RestExceptionHandlerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ReminderService reminderService;

	@Test
	public void thatRuntimeExceptionHappens() throws Exception {
		String message = "Test message";
		when(reminderService.findById(1L)).thenThrow(new RuntimeException(message));

		mvc.perform(get("/api/reminders/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message").value(message));
	}

	@Test
	public void thatReminderNotFoundExceptionHappens() throws Exception {
		ReminderNotFoundException exception = new ReminderNotFoundException();
		when(reminderService.findById(1L)).thenThrow(exception);

		mvc.perform(get("/api/reminders/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value(exception.getMessage()));
	}
}
