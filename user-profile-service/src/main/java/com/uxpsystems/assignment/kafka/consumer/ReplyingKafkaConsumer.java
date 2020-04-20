package com.uxpsystems.assignment.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.uxpsystems.assignment.exceptions.UserNotFoundException;
import com.uxpsystems.assignment.model.UserDTO;
import com.uxpsystems.assignment.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReplyingKafkaConsumer {
	@Autowired
	private UserService userService;

	@KafkaListener(topics = "${kafka.topic.request-topic}")
	@SendTo("${kafka.topic.requestreply-topic}")
	public UserDTO update(UserDTO user) throws InterruptedException {
		log.info("Sending reply result : " + user.getUserName());
		String Operation = (String) user.getAdditionalProperties().get("Operation");
		if ("Update".equalsIgnoreCase(Operation)) {
			try {
				userService.update(user);
				user.setAdditionalProperty("success", "User Updated success");
			} catch (UserNotFoundException e) {
				log.error("Exception in updating username : {} {} ", user.getUserName(), e.getMessage());
				user.setAdditionalProperty("error", e.getMessage());
				e.printStackTrace();
			}
		} else if ("Delete".equalsIgnoreCase(Operation)) {
			try {
				userService.delete(user);
				user.setAdditionalProperty("success", "User delete success");
			} catch (UserNotFoundException e) {
				log.error("Exception in deleteing username : {} {} ", user.getUserName(), e.getMessage());
				e.printStackTrace();
				user.setAdditionalProperty("error", e.getMessage());
			}
		}
		return user;
	}

}
