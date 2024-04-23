package com.app.JobApplicationSystem.service;

import com.app.JobApplicationSystem.dto.JobseekerSignUpRequestDto;
import com.app.JobApplicationSystem.dto.RecruiterSignUpDto;

public interface SignUpService {

	
	String registrationOfRecruiter(RecruiterSignUpDto recruiterDto);

    String registrationOfJobseeker(JobseekerSignUpRequestDto seekerDto);
}
