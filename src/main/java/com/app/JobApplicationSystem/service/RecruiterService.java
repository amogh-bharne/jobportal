package com.app.JobApplicationSystem.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.app.JobApplicationSystem.dto.JobApplicationsListDto;
import com.app.JobApplicationSystem.dto.JobListDto;
import com.app.JobApplicationSystem.dto.JobStatusDto;
import com.app.JobApplicationSystem.dto.PostJobRequestDto;
import com.app.JobApplicationSystem.dto.RecruiterDto;
import com.app.JobApplicationSystem.dto.RecruiterRequestDto;
import com.app.JobApplicationSystem.dto.UpdateJobRequestDto;


public interface RecruiterService {

	 List<JobListDto> getAllJobsPosted(Long recruiterId);
	
	String postJob(PostJobRequestDto postJobRequestDto);
	
	Map<String, Object> deleteJobByRecruiter(Long jobId, Long recruiterId);
	
	 String updateJobByRecruiter(Long jobId, UpdateJobRequestDto updateJobRequestDto, Long recruiterId);
	
	
	  String updateApplicationStatusByRecruiter(Long jobId, Long jobSeekerId, JobStatusDto jobStatus,
				Long recruiterId);
	
	  List<JobApplicationsListDto> getListOfJobApplications(Long jobId, Long loggedInRecruiterId);

	  String updateProfile(RecruiterRequestDto recruiterDto, Long recuiterId);
	  
	  RecruiterRequestDto recrutierById(Long recuiterId );

	  List<RecruiterDto> getAllRecruiters(Pageable pageable);
	  
	  
}
