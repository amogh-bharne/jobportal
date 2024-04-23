package com.app.JobApplicationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.JobApplicationSystem.entities.EducationalDetails;

public interface EducationalDetailsRepository extends JpaRepository<EducationalDetails, Long> {
}
