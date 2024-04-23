package com.app.JobApplicationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.JobApplicationSystem.entities.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {


}
