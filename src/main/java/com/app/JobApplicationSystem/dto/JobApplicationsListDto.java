package com.app.JobApplicationSystem.dto;

import java.time.LocalDate;
import java.util.List;

import com.app.JobApplicationSystem.entities.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobApplicationsListDto {
	
	private Long jobSeekerId;
	private String firstName;
    private String lastName;
    private List<SkillDto> skills;
    private byte[] resumeFile;
    private LocalDate appliedDate;
    private Status status;
}
