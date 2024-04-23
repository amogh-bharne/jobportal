package com.app.JobApplicationSystem.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobSeekerResponseDto {
	
	private Long jobSeekerId;
	
    private String email;

    private String firstName;

    private String lastName;

    private int yearOfExperience;

    private List<EducationalDetailsDto> eduInfo = new ArrayList<>();

    private List<SkillDto> skills = new ArrayList<>();


    public void setEduInfo(EducationalDetailsDto edu) {
        eduInfo.add(edu);
    }

    public void setSkills(SkillDto skill) {
        skills.add(skill);
    }


}
