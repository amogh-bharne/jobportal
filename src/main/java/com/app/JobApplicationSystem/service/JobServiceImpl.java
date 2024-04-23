package com.app.JobApplicationSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.JobApplicationSystem.dto.JobApplicationsListDto;
import com.app.JobApplicationSystem.dto.JobListDto;
import com.app.JobApplicationSystem.dto.SkillDto;
import com.app.JobApplicationSystem.entities.Job;
import com.app.JobApplicationSystem.entities.JobApplication;
import com.app.JobApplicationSystem.entities.Skill;
import com.app.JobApplicationSystem.exceptions.ResourceNotFoundException;
import com.app.JobApplicationSystem.repository.JobApplicationRepository;
import com.app.JobApplicationSystem.repository.JobRepository;
import com.app.JobApplicationSystem.repository.JobSeekerRepository;
import com.app.JobApplicationSystem.repository.RecruiterRepository;
import com.app.JobApplicationSystem.repository.SkillRepository;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JobRepository jobRepo;

	@Autowired
	private RecruiterRepository recruiterRepository;

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private JobApplicationRepository jobApplicationRepo;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepo;
	
	

	@Override
	public List<JobListDto> getAllJobs() {

		List<Job> list= jobRepo.findAll();
		
		return list.stream()
				.map(l -> {
					JobListDto joblistDto =	mapper.map(l, JobListDto.class);
					
					joblistDto.setRecruiterName(l.getPostedBy().getFirstName());
					return joblistDto;
				})
				.collect(Collectors.toList());
	}	



	@Override
	public List<JobApplicationsListDto> getListOfJobApplications(Long jobId) {
		//to save result and return
		List<JobApplicationsListDto> jobAppListDto = new ArrayList<>();
		
		//Taking job on which list of applications are finding
		Job job = jobRepo.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("job not found with ID: " + jobId));
		
		List<JobApplication> listOfApplications =job.getApplications();
		
		listOfApplications.forEach(jobApp -> {
			//mapping attributes
		    JobApplicationsListDto dto = mapper.map(jobApp, JobApplicationsListDto.class);
		    dto.setJobSeekerId(jobApp.getJobSeeker().getJobSeekerId());
		    dto.setFirstName(jobApp.getJobSeeker().getFirstName());
		    dto.setLastName(jobApp.getJobSeeker().getLastName());
		    
		    Long jobSeekerId = jobApp.getJobSeeker().getJobSeekerId();

	        // Fetch skills using skillRepository's custom query method
	        List<Skill> jobSeekerSkills = skillRepository.findAllSkillsByJobSeekerId(jobSeekerId);

	        // Create a list to hold SkillDto instances
	        List<SkillDto> skillDtos = new ArrayList<>();

	        // Convert Skill entities to SkillDto instances
	        for (Skill skill : jobSeekerSkills) {
	            SkillDto skillDto = new SkillDto(skill.getSkillId(), skill.getName(), skill.getDescription());
	            skillDtos.add(skillDto);
	        }
	        
		    dto.setSkills(skillDtos);
		    
		    jobAppListDto.add(dto);
		});
		
		return jobAppListDto;
	}

	public List<JobListDto>JobsWithTitle(String jobTitle)
	{
		List<Job> jobLists=jobRepo.findAll();
		
		List<Job>jobWithSpecificTitle=jobLists.stream().filter(sjt->sjt.getJobTitle().contains(jobTitle)).collect(Collectors.toList());
		
		return jobWithSpecificTitle.stream().map((job)->{
			JobListDto jobHolder= mapper.map(job, JobListDto.class);
			jobHolder.setJobId(job.getJobId());
			jobHolder.setCompanyName(job.getPostedBy().getCompanyName());
			jobHolder.setJobTitle(job.getJobTitle());
			jobHolder.setJobDescription(job.getJobDescription());
			jobHolder.setPostedDate(job.getPostedDate());
			jobHolder.setDeadLineDate(job.getDeadLineDate());
			jobHolder.setNoOfJobPositions(job.getNoOfJobPositions());
			jobHolder.setJobType(job.getJobType());
			jobHolder.setSalary(job.getSalary());
			jobHolder.setRecruiterName(job.getPostedBy().getFirstName());
			
			List<Skill>listOfSkills=jobRepo.findSkillsByJobId(job.getJobId());
			listOfSkills.forEach(skill->{
				jobHolder.getSkillsForJob().add(mapper.map(skill, SkillDto.class));
			});
			
			return jobHolder;
		}).collect(Collectors.toList());
		
		
	}

	   
	

	
}
