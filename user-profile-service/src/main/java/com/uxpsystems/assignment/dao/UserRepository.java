package com.uxpsystems.assignment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.model.Profile;

@Repository
public interface UserRepository extends JpaRepository<Profile, Long> {

	Optional<Profile> findByUserName(String userName);

	void deleteByUserName(String userName);

}
