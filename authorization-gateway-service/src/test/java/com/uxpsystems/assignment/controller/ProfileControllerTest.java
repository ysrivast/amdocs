package com.uxpsystems.assignment.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.uxpsystems.assignment.kafka.producer.EventProducer;
import com.uxpsystems.assignment.model.UserDTO;

class ProfileControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private ProfileController profileController;
	
	@Mock
	private EventProducer producer;

	@BeforeEach
	public void setup() {
		profileController = new ProfileController();
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();

	}

	@Test
	void testForUserUpdate() throws Exception {
		UserDTO user = new UserDTO();
		user.setUserName("TEST1");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(user);
				mockMvc.perform(
				MockMvcRequestBuilders.put("/profile").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
