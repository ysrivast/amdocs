package com.uxpsystems.assignment.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.uxpsystems.assignment.model.Profile;

@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void testForUserSave() {
		long countBefore = userRepository.count();
		String userName = "TESTUSER1";
		Profile user = new Profile();
		user.setUserId(100l);
		user.setUserName(userName);
		userRepository.save(user);
		assertThat(userRepository.count()).isEqualTo(countBefore + 1);
		assertThat(userRepository.findByUserName(userName).get()).isNotNull();
	}

}
