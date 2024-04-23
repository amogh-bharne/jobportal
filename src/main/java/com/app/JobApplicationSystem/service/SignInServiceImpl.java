package com.app.JobApplicationSystem.service;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.JobApplicationSystem.dto.SignInRequestDto;
import com.app.JobApplicationSystem.dto.SignInResponseDto;
import com.app.JobApplicationSystem.entities.User;
import com.app.JobApplicationSystem.exceptions.ResourceNotFoundException;
import com.app.JobApplicationSystem.repository.UserRepository;


@Service
@Transactional
public class SignInServiceImpl implements SignInService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private ModelMapper mapper;
	
	public  SignInResponseDto authenticationOfUser(SignInRequestDto signInDto)
	{
		User specificUser=userRepo.findByEmail(signInDto.getUserName()).orElseThrow(()-> new ResourceNotFoundException("Invalid Username"));
	
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String userProvidedPassword=signInDto.getPassword();
		String storedHashedPassword=specificUser.getPassword();
		boolean passwordsMatch = passwordEncoder.matches(userProvidedPassword, storedHashedPassword);
		
		if (!passwordsMatch) {
            throw new RuntimeException("Login failed.Password is incorrect");
        } 
		
		SignInResponseDto userHolder=mapper.map(specificUser, SignInResponseDto.class);
		
		
		userHolder.setEmail(specificUser.getEmail());
		userHolder.setUserId(specificUser.getUserId());
		if(specificUser.getRole().equals("ROLE_RECRUITER"))
			{
				userHolder.setRecruiterId(specificUser.getRecruiter().getRecruiterId());
			}
			if(specificUser.getRole().equals("ROLE_JOBSEEKER"))
			{
				userHolder.setJobSeekerId(specificUser.getJobSeeker().getJobSeekerId());
			}
			
		
			return userHolder;
		
	
	
	
	
	
	}
	
	
	
}
