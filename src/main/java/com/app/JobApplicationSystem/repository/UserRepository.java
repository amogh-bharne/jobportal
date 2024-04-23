package com.app.JobApplicationSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.JobApplicationSystem.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	// boolean findByEmails(String email);
	//boolean 

	User findByRole(String string);
}
