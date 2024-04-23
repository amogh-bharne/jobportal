package com.app.JobApplicationSystem.service;

import java.util.List;

import com.app.JobApplicationSystem.dto.JobSeekerResponseDto;
import com.app.JobApplicationSystem.dto.RecruiterDto;

public interface AdminService {

	List<JobSeekerResponseDto> getAllJobseekers();

	List<RecruiterDto> getAllRecruiters();
	
	String deleteJobSeekerProfile(Long jobSeekerId);
	
	String deleteRecruiterProfile(Long recruiterId);
}
