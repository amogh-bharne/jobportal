package com.app.Job_Portal.dto;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// import com.app.Job_Portal.entities.JobType;

// import lombok.Getter;
// import lombok.Setter;
// import lombok.ToString;

// @Getter
// @Setter
// @ToString
// public class JobListDto {
	
// 	private Long jobId;
	
// 	 private String companyName;

// 	private String jobTitle;

// 	private String jobDescription;

// 	private LocalDate postedDate;

// 	private LocalDate deadLineDate;

// 	private int noOfJobPositions;

// 	private double salary;

// 	private JobType jobType;

// 	//private Recruiter postedBy;
// 	private String recruiterName;
	
//  private List<SkillDto> skillsForJob = new ArrayList<>();
//  private List<String> skillsForJob_strings = new ArrayList<>();

// }

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.app.Job_Portal.entities.JobType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobListDto {
    
    private Long jobId;
    private String companyName;
    private String jobTitle;
    private String jobDescription;
    private LocalDate postedDate;
    private LocalDate deadLineDate;
    private int noOfJobPositions;
    private double salary;
    private JobType jobType;
    private String recruiterName;
    private List<SkillDto> skillsForJob = new ArrayList<>();
    private List<String> skillsForJob_strings = new ArrayList<>();

    private JobListDto() {
        // Private constructor to enforce builder usage
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long jobId;
        private String companyName;
        private String jobTitle;
        private String jobDescription;
        private LocalDate postedDate;
        private LocalDate deadLineDate;
        private int noOfJobPositions;
        private double salary;
        private JobType jobType;
        private String recruiterName;
        private List<SkillDto> skillsForJob = new ArrayList<>();
        private List<String> skillsForJob_strings = new ArrayList<>();

        public Builder jobId(Long jobId) {
            this.jobId = jobId;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder jobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public Builder jobDescription(String jobDescription) {
            this.jobDescription = jobDescription;
            return this;
        }

        public Builder postedDate(LocalDate postedDate) {
            this.postedDate = postedDate;
            return this;
        }

        public Builder deadLineDate(LocalDate deadLineDate) {
            this.deadLineDate = deadLineDate;
            return this;
        }

        public Builder noOfJobPositions(int noOfJobPositions) {
            this.noOfJobPositions = noOfJobPositions;
            return this;
        }

        public Builder salary(double salary) {
            this.salary = salary;
            return this;
        }

        public Builder jobType(JobType jobType) {
            this.jobType = jobType;
            return this;
        }

        public Builder recruiterName(String recruiterName) {
            this.recruiterName = recruiterName;
            return this;
        }

        public Builder skillForJob(SkillDto skillDto) {
            this.skillsForJob.add(skillDto);
            return this;
        }

        public Builder skillForJob_string(String skillString) {
            this.skillsForJob_strings.add(skillString);
            return this;
        }

        public JobListDto build() {
            JobListDto jobListDto = new JobListDto();
            jobListDto.setJobId(this.jobId);
            jobListDto.setCompanyName(this.companyName);
            jobListDto.setJobTitle(this.jobTitle);
            jobListDto.setJobDescription(this.jobDescription);
            jobListDto.setPostedDate(this.postedDate);
            jobListDto.setDeadLineDate(this.deadLineDate);
            jobListDto.setNoOfJobPositions(this.noOfJobPositions);
            jobListDto.setSalary(this.salary);
            jobListDto.setJobType(this.jobType);
            jobListDto.setRecruiterName(this.recruiterName);
            jobListDto.setSkillsForJob(this.skillsForJob);
            jobListDto.setSkillsForJob_strings(this.skillsForJob_strings);
            return jobListDto;
        }
    }
}
