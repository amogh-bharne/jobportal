package com.app.JobApplicationSystem.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.JobApplicationSystem.dto.JobseekerSignUpRequestDto;
import com.app.JobApplicationSystem.dto.RecruiterSignUpDto;
import com.app.JobApplicationSystem.entities.JobSeeker;
import com.app.JobApplicationSystem.entities.Recruiter;
import com.app.JobApplicationSystem.entities.User;
import com.app.JobApplicationSystem.exceptions.ValidationRule;
import com.app.JobApplicationSystem.repository.AdminRepository;
import com.app.JobApplicationSystem.repository.JobSeekerRepository;
import com.app.JobApplicationSystem.repository.RecruiterRepository;
import com.app.JobApplicationSystem.repository.UserRepository;

@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private RecruiterRepository recruiterRepo;

    @Autowired
    private JobSeekerRepository jobSeekerRepo;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private UserRepository userRepo;

    public String registrationOfRecruiter(RecruiterSignUpDto recruiterDto) {
        //validation of Email via Global exception
        //validation of PhoneNumber via Custom exception
        ValidationRule.validationOfPhoneNumber(recruiterDto.getPhoneNo());


        Recruiter newRecruiter = Recruiter.builder()
    .email(recruiterDto.getEmail())
    .firstName(recruiterDto.getFirstName())
    .lastName(recruiterDto.getLastName())
    .recruiterBio(recruiterDto.getRecruiterBio())
    .companyName(recruiterDto.getCompanyName())
    .phoneNo(recruiterDto.getPhoneNo())
    .build();

        
        recruiterRepo.save(newRecruiter);


        String cryptPassword=new BCryptPasswordEncoder().encode(recruiterDto.getPassword());

        User newUser = User.builder()
        .email(recruiterDto.getEmail())
        .password(cryptPassword)
        .recruiter(newRecruiter)
        .role(recruiterDto.getRole())
        .build();
        
        userRepo.save(newUser);

        return "Registration Completed Successfully"
                ;
    }

    @Override
    public String registrationOfJobseeker(JobseekerSignUpRequestDto seekerDto) {
        JobSeeker seekerProfile = new JobSeeker();
        seekerProfile.setFirstName(seekerDto.getFirstName());
        seekerProfile.setLastName(seekerDto.getLastName());
        seekerProfile.setEmail(seekerDto.getEmail());
        seekerProfile.setYearOfExperience(seekerDto.getYearOfExperience());


       

        

        JobSeeker persitanceSeeker = jobSeekerRepo.save(seekerProfile);

        User newUser = new User();

        newUser.setEmail(seekerDto.getEmail());
        String cryptPassword=new BCryptPasswordEncoder().encode(seekerDto.getPassword());
        newUser.setPassword(cryptPassword);
        newUser.setJobSeeker(persitanceSeeker);
        newUser.setRole("ROLE_JOBSEEKER");

        userRepo.save(newUser);

        return "profile created succefully";
    }
}
