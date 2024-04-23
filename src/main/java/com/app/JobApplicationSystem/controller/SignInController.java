package com.app.JobApplicationSystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.JobApplicationSystem.dto.SignInRequestDto;
import com.app.JobApplicationSystem.dto.SignInResponseDto;
import com.app.JobApplicationSystem.service.SignInService;

@RestController
@RequestMapping("/signin")
@CrossOrigin(origins = "*")
public class SignInController {

	@Autowired
	private SignInService signInService;
	
	@PostMapping("/User")
	public SignInResponseDto authenticationOfUser(@RequestBody @Valid SignInRequestDto signInReqDto)
	{
		return signInService.authenticationOfUser(signInReqDto);
	}
		
	
}
