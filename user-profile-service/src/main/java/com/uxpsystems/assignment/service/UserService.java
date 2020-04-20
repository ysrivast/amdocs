package com.uxpsystems.assignment.service;

import com.uxpsystems.assignment.exceptions.UserNotFoundException;
import com.uxpsystems.assignment.model.Profile;
import com.uxpsystems.assignment.model.UserDTO;

public interface UserService {

	Profile create(Profile user);
	void delete(UserDTO user) throws UserNotFoundException;
	UserDTO update(UserDTO request) throws UserNotFoundException;
	Profile get(String userName) throws UserNotFoundException;
}
