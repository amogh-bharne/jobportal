package com.app.JobApplicationSystem.service;

import java.util.List;

import com.app.JobApplicationSystem.dto.JobApplicationsListDto;
import com.app.JobApplicationSystem.dto.JobListDto;

public interface JobService {
	List<JobListDto> getAllJobs();
	List<JobApplicationsListDto> getListOfJobApplications(Long jobId);
	List<JobListDto>JobsWithTitle(String jobTiltle);
}
