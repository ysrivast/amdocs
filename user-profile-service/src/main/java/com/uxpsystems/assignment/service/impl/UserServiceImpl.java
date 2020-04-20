package com.uxpsystems.assignment.service.impl;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.dao.UserRepository;
import com.uxpsystems.assignment.exceptions.UserNotFoundException;
import com.uxpsystems.assignment.model.Profile;
import com.uxpsystems.assignment.model.UserDTO;
import com.uxpsystems.assignment.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public Profile create(Profile user) {
		user.setUpdatedOn(ZonedDateTime.now());
		user.setCreatedOn(ZonedDateTime.now());
		return userRepository.save(user);
	}


	@Override
	public void delete(UserDTO user) throws UserNotFoundException {
		Optional<Profile> dbUser = userRepository.findByUserName(user.getUserName());
		if(!dbUser.isPresent()) {
			throw new UserNotFoundException("User Not exist");
		}
		userRepository.deleteById(dbUser.get().getUserId());
	}

	@Override
	public UserDTO update(UserDTO user) throws UserNotFoundException {
		Optional<Profile> dbUser = userRepository.findByUserName(user.getUserName());
		if(!dbUser.isPresent()) {
			throw new UserNotFoundException("User Not exist");
		}
		Profile persistUser = dbUser.get();
		persistUser.setAddress(user.getAddress());
		persistUser.setPhone(user.getPhone());
		persistUser.setUpdatedOn(ZonedDateTime.now());
		persistUser=userRepository.save(persistUser);
		user.setPhone(persistUser.getPhone());
		user.setAddress(persistUser.getAddress());
		return user;
		
	}


	@Override
	public Profile get(String userName) throws UserNotFoundException {
		Optional<Profile> user = userRepository.findByUserName(userName);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User Not exist");
		}
		return user.get();
	}

}
