package com.uxpsystems.assignment.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.kafka.producer.EventProducer;
import com.uxpsystems.assignment.model.UserDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProfileController {

	@Autowired
	private EventProducer eventProducer;
	
	@PutMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO sum(@RequestBody UserDTO user) throws InterruptedException, ExecutionException {
		log.info("Profile update request for : {}" + user.getUserName());
		user.setAdditionalProperty("Operation", "Update");
		return eventProducer.publish(user);
	}

	@DeleteMapping(value = "/profile")
	public UserDTO delete(@RequestParam("userName") String userName) throws InterruptedException, ExecutionException {
		log.info("Profile delete request for : {} : " + userName);
		UserDTO user = new UserDTO();
		user.setUserName(userName);
		user.setAdditionalProperty("Operation", "Delete");
		return eventProducer.publish(user);
	}
}
