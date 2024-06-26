package com.app.JobApplicationSystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.JobApplicationSystem.dto.JobApplicationsListDto;
import com.app.JobApplicationSystem.dto.JobListDto;
import com.app.JobApplicationSystem.dto.JobStatusDto;
import com.app.JobApplicationSystem.dto.PostJobRequestDto;
import com.app.JobApplicationSystem.dto.RecruiterDto;
import com.app.JobApplicationSystem.dto.RecruiterRequestDto;
import com.app.JobApplicationSystem.dto.SkillDto;
import com.app.JobApplicationSystem.dto.UpdateJobRequestDto;
import com.app.JobApplicationSystem.entities.Job;
import com.app.JobApplicationSystem.entities.JobApplication;
import com.app.JobApplicationSystem.entities.JobSeeker;
import com.app.JobApplicationSystem.entities.Recruiter;
import com.app.JobApplicationSystem.entities.Skill;
import com.app.JobApplicationSystem.entities.Status;
import com.app.JobApplicationSystem.exceptions.ResourceNotFoundException;
import com.app.JobApplicationSystem.repository.JobApplicationRepository;
import com.app.JobApplicationSystem.repository.JobRepository;
import com.app.JobApplicationSystem.repository.JobSeekerRepository;
import com.app.JobApplicationSystem.repository.RecruiterRepository;
import com.app.JobApplicationSystem.repository.SkillRepository;
@Service
@Transactional
public class RecruiterServiceImpl implements RecruiterService{

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
	
	public List<JobListDto> getAllJobsPosted(Long recruiterId) {
		Recruiter recruiter= recruiterRepository.findById(recruiterId).orElseThrow(
				() -> new ResourceNotFoundException("recruiter not found with ID: " + recruiterId));;
		
				List<JobListDto> jobList=new ArrayList<JobListDto>();
				
				recruiter.getJobListings().forEach(job->{
					JobListDto newJob=mapper.map(job, JobListDto.class);
					newJob.setRecruiterName(recruiter.getFirstName()+" "+recruiter.getLastName());
					
					List<String> skillsDtos=skillRepository.findSkillNamesByJobId(job.getJobId());
					newJob.setSkillsForJob_strings(skillsDtos);
					jobList.add(newJob);
				});
		return jobList;
	}


	
	
	@Override
	public Map<String, Object> deleteJobByRecruiter(Long jobId, Long recruiterId) {
	    Map<String, Object> response = new HashMap<>();

	    
	    Job job = jobRepo.findById(jobId)
	            .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));

	    
	    if (!job.getPostedBy().getRecruiterId().equals(recruiterId)) {
	        response.put("success", false);
	        response.put("message", "Job not found or not posted by the specified recruiter.");
	        return response;
	    }

	    
	    jobRepo.delete(job);

	    response.put("success", true);
	    response.put("message", "Job deleted successfully.");
	    return response;
	}

	
	@Override
	public String postJob(PostJobRequestDto postJobRequestDto) {
		
		
		Job job = mapper.map(postJobRequestDto, Job.class);
		
		if (recruiterRepository.existsById(postJobRequestDto.getRecruiterId())
				&& postJobRequestDto.getSkillIds() != null && !postJobRequestDto.getSkillIds().isEmpty()) {
			job.setPostedBy(recruiterRepository.findById(postJobRequestDto.getRecruiterId()).orElseThrow(null));
			List<Skill> skills = skillRepository.findAllById(postJobRequestDto.getSkillIds());
			job.setSkills(skills);
			job.setPostedDate(LocalDate.now());
			jobRepo.save(job);
			return "successfully job added";
		}
		return "not added job!!";
	}
	
	@Override
	public String updateJobByRecruiter(Long jobId, UpdateJobRequestDto updateJobRequestDto, Long recruiterId) {
	    Job job = jobRepo.findById(jobId)
	            .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));

	    
	    if (!job.getPostedBy().getRecruiterId().equals(recruiterId)) {
	        return "Job not updated. It was not posted by the specified recruiter.";
	    }

	    if (recruiterRepository.existsById(updateJobRequestDto.getRecruiterId())
	            && updateJobRequestDto.getSkillIds() != null && !updateJobRequestDto.getSkillIds().isEmpty()) {
	        job.setJobDescription(updateJobRequestDto.getJobDescription());
	        job.setDeadLineDate(updateJobRequestDto.getDeadLineDate());
	        job.setPostedDate(updateJobRequestDto.getPostedDate());
	        job.setJobType(updateJobRequestDto.getJobType());
	        job.setNoOfJobPositions(updateJobRequestDto.getNoOfJobPositions());
	        job.setSalary(updateJobRequestDto.getSalary());
	        List<Skill> skills = skillRepository.findAllById(updateJobRequestDto.getSkillIds());
	        job.setSkills(skills);
	        jobRepo.save(job);
	        return "Job updated successfully.";
	    }

	    return "Job not updated.";
	}


	
	public String updateApplicationStatusByRecruiter(Long jobId, Long jobSeekerId, JobStatusDto jobStatus, Long recruiterId) {
	    Job job = jobRepo.findById(jobId)
	            .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));

	    
	    if (!job.getPostedBy().getRecruiterId().equals(recruiterId)) {
	        return "Job application status not updated. Job was not posted by the specified recruiter.";
	    }

	    JobSeeker jobSeeker = jobSeekerRepo.findById(jobSeekerId)
	            .orElseThrow(() -> new ResourceNotFoundException("Job Seeker not found with ID: " + jobSeekerId));

	    List<JobApplication> jobApplications = jobSeeker.getJobApplications();
	    for (JobApplication application : jobApplications) {
	        if (application.getJob().getJobId().equals(jobId)) {
	            application.setStatus(Status.valueOf(jobStatus.getJobStatus()));
	        }
	    }

	    jobSeekerRepo.save(jobSeeker);

	    return "Job application status updated.";
	}

	 public List<JobApplicationsListDto> getListOfJobApplications(Long jobId, Long loggedInRecruiterId) {
	        
	        List<JobApplicationsListDto> jobAppListDto = new ArrayList<>();
	        
	        
	        Job job = jobRepo.findById(jobId)
	                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));
	        
	        
	        if (!loggedInRecruiterId.equals(job.getPostedBy().getRecruiterId())) {
	            throw new ResourceNotFoundException("You are not authorized to view job applications for this job.");
	        }
	        
	        List<JobApplication> listOfApplications = job.getApplications();
	        
	        listOfApplications.forEach(jobApp -> {
	            
	            JobApplicationsListDto dto = mapper.map(jobApp, JobApplicationsListDto.class);
	            dto.setJobSeekerId(jobApp.getJobSeeker().getJobSeekerId());
	            dto.setFirstName(jobApp.getJobSeeker().getFirstName());
	            dto.setLastName(jobApp.getJobSeeker().getLastName());
	            
	            
	            Long jobSeekerId = jobApp.getJobSeeker().getJobSeekerId();
	            
	            
	            List<Skill> jobSeekerSkills = skillRepository.findAllSkillsByJobSeekerId(jobSeekerId);

	            
	            List<SkillDto> skillDtos = new ArrayList<>();

	            
	            for (Skill skill : jobSeekerSkills) {
	                SkillDto skillDto = new SkillDto(skill.getSkillId(), skill.getName(), skill.getDescription());
	                skillDtos.add(skillDto);
	            }
	            
	            dto.setSkills(skillDtos);
	            
	            jobAppListDto.add(dto);
	        });
	        
	        return jobAppListDto;
	    }

	 
	 @Override
	 public String updateProfile(RecruiterRequestDto recruiterDto, Long recuiterId)
	 {
		 


				Recruiter recruiter = recruiterRepository.findById(recuiterId)
			.orElseThrow(() -> new ResourceNotFoundException("Recruiter with the given ID not found"));

			recruiter.setFirstName(recruiterDto.getFirstName());
			recruiter.setLastName(recruiterDto.getLastName());
			recruiter.setPhoneNo(recruiterDto.getPhoneNo());
			recruiter.setRecruiterBio(recruiterDto.getRecruiterBio());
			recruiter.setCompanyName(recruiterDto.getCompanyName());
			recruiterRepository.save(recruiter);


		 
		 return "profile updated succefully !!!"; 
	 }
	 
	 @Override
	 public RecruiterRequestDto recrutierById(Long recuiterId )
	 {
		 Recruiter recruiter=recruiterRepository.findById(recuiterId).orElseThrow(()->new ResourceNotFoundException("recruiter with given id not found"));
		 RecruiterRequestDto recru=mapper.map(recruiter, RecruiterRequestDto.class);
			return recru;
	 
	 }
	 
	 
	 @Override
	 public List<RecruiterDto> getAllRecruiters(Pageable pageable) {
		    
		    
		     
		    Page<Recruiter> recruiters = recruiterRepository.findAll(pageable);

		    
		     
		    List<RecruiterDto> recruiterDtos = new ArrayList<>();

		    
		     
		    for (Recruiter recruiter : recruiters.getContent()) {
		        
		         
		        RecruiterDto recruiterHolder = mapper.map(recruiter, RecruiterDto.class);

		        
		         
		        JobListDto jobList = mapper.map(recruiter.getJobListings(), JobListDto.class);
		        recruiterHolder.setJobListing(jobList);

		        
		         
		        recruiterDtos.add(recruiterHolder);
		    }
		    
		     
		    return recruiterDtos;
		}

}
