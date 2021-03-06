package com.uxpsystems.assignment.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.uxpsystems.assignment.model.User;

@DataJpaTest
class UserRepositoryTest {


	@Autowired
	private UserRepository userRepository;

	@Test
	void testForUserSave() {
		long countBefore = userRepository.count();
		String userName = "TESTUSER1";
		User user = new User();
		user.setUserId(100l);
		user.setUserName(userName);
		user.setPassword("$2a$10$mEMC1tvQYnu5Pt/.p4k4Kurad9axup73GqU/KkQ0Zeo6Kt5AWKZeO");
		userRepository.save(user);
		assertThat(userRepository.count()).isEqualTo(countBefore + 1);
		assertThat(userRepository.findByUserName(userName).get()).isNotNull();
	}

}
