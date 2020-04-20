package com.uxpsystems.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.model.Profile;
import com.uxpsystems.assignment.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/profile")
	public ResponseEntity<Profile> create(@RequestBody Profile user) {
		log.info("request for user : " + user);
		Profile result = userService.create(user);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
