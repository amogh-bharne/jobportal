package com.app.JobApplicationSystem.service;

import com.app.JobApplicationSystem.dto.SignInRequestDto;
import com.app.JobApplicationSystem.dto.SignInResponseDto;

public interface SignInService {

	SignInResponseDto authenticationOfUser(SignInRequestDto signInDto);
}
