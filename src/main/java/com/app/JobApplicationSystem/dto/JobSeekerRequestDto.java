package com.app.JobApplicationSystem.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobSeekerRequestDto {

	@Email(message = "Invalid Email")
    @NotNull
    @NotBlank(message="Enter your Email Id")
	private String email;

	@NotNull
    @NotBlank(message="Enter your first name")
    private String firstName;

	@NotNull
    @NotBlank(message="Enter your last name")
    private String lastName;

	
	@Min(value = 0, message = "You should have alteast 0 year of experience.")
    private int yearOfExperience;

    

    private List<EducationalDetailsDto> eduInfo = new ArrayList<>();
	
	
    private List<SkillDto> skills = new ArrayList<>();
}
