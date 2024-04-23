package com.app.JobApplicationSystem.service;


import java.util.List;

import com.app.JobApplicationSystem.dto.JobApplicationResponseDto;
import com.app.JobApplicationSystem.dto.JobListDto;
import com.app.JobApplicationSystem.dto.JobSeekerRequestDto;
import com.app.JobApplicationSystem.dto.JobSeekerResponseDto;


public interface JobSeekerService {

    List<JobListDto> getAllJobs();

    List<JobListDto> getJobsWithGivenType(String jobType);

    List<JobListDto> getJobsWithGivenTitle(String title);

    List<JobApplicationResponseDto> getAppliedJobs(Long jobSeekerId);

    List<JobApplicationResponseDto> getAllJobsWithGivenStatus(Long jobSeekerId, String status);

    String applyForAJob(Long jobId, Long jobSeekerId);

    String withDrawAnApplication(Long jobId, Long jobSeekerId);

    String createProfile(JobSeekerRequestDto seekerDto);

    String updateProfile(JobSeekerRequestDto seekerDto, Long jobSeekerId);

    String deleteProfile(Long jobSeekerId);

    JobSeekerResponseDto getProfile(Long jobSeekerId);
}
