package com.app.JobApplicationSystem.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.JobApplicationSystem.dto.JobApplicationResponseDto;
import com.app.JobApplicationSystem.dto.JobListDto;
import com.app.JobApplicationSystem.dto.JobSeekerRequestDto;
import com.app.JobApplicationSystem.dto.JobSeekerResponseDto;
import com.app.JobApplicationSystem.service.JobSeekerService;
import com.app.JobApplicationSystem.service.ResumeService;

@RestController
@RequestMapping("/jobseeker")
@CrossOrigin(origins = "http://localhost:3000")
public class JobseekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private ResumeService resumeService;


    @GetMapping("/get/all")
    public List<JobListDto> getAllJobs() {
        return jobSeekerService.getAllJobs();
    }


    @GetMapping("/get/jobtype/{jobType}")
    public List<JobListDto> getJobsByJobType(@PathVariable String jobType) {

        return jobSeekerService.getJobsWithGivenType(jobType);

    }

    @GetMapping("/get/title/{title}")
    public List<JobListDto> getJobsByJobTitle(@PathVariable String title) {

        return jobSeekerService.getJobsWithGivenTitle(title);

    }


    @GetMapping("/get/applied/{jobSeekerId}")
    public List<JobApplicationResponseDto> getAllAppliedJobs(@PathVariable Long jobSeekerId) {

        return jobSeekerService.getAppliedJobs(jobSeekerId);

    }


    @GetMapping("/get/{status}/{jobSeekerId}")
    public List<JobApplicationResponseDto> getAllJobsWithGivenStatus(@PathVariable Long jobSeekerId, @PathVariable String status) {

        return jobSeekerService.getAllJobsWithGivenStatus(jobSeekerId, status);

    }


    @GetMapping("/apply/{jobId}/{jobSeekerId}")
    public ResponseEntity<String> applyForAJob(@PathVariable Long jobId, @PathVariable Long jobSeekerId) {
        return new ResponseEntity<String>(jobSeekerService.applyForAJob(jobId, jobSeekerId), HttpStatus.OK);
    }



    @DeleteMapping("/withdraw-application/{jobId}/{jobSeekerId}")
    public ResponseEntity<String> withDrawAnApplication(@PathVariable Long jobId, @PathVariable Long jobSeekerId) {
        return new ResponseEntity<String>(jobSeekerService.withDrawAnApplication(jobId, jobSeekerId), HttpStatus.OK);
    }


    @PostMapping("/create-profile")
    public ResponseEntity<String> createProfile(@RequestBody @Valid JobSeekerRequestDto seekerDto) {
        return new ResponseEntity<String>(jobSeekerService.createProfile(seekerDto), HttpStatus.OK);
    }


    @PutMapping("/update-profile/{jobSeekerId}")
    public ResponseEntity<String> updateProfile(@PathVariable Long jobSeekerId, @RequestBody @Valid JobSeekerRequestDto seekerDto) {
        return new ResponseEntity<String>(jobSeekerService.updateProfile(seekerDto, jobSeekerId), HttpStatus.OK);
    }


    @DeleteMapping("/remove-profile/{jobSeekerId}")
    public ResponseEntity<String> removeProfile(@PathVariable Long jobSeekerId) {
        return new ResponseEntity<String>(jobSeekerService.deleteProfile(jobSeekerId), HttpStatus.OK);
    }



    @GetMapping("/get-profile/{jobSeekerId}")
    public JobSeekerResponseDto getProfile(@PathVariable Long jobSeekerId) {
        return jobSeekerService.getProfile(jobSeekerId);
    }


  
    @GetMapping("/get-profile/resume/{jobSeekerId}")
    public void getResume(@PathVariable Long jobSeekerId, HttpServletResponse res) throws IOException {
        InputStream resource = resumeService.getResume(jobSeekerId);
        res.setContentType(String.valueOf(MediaType.APPLICATION_PDF));
        StreamUtils.copy(resource, res.getOutputStream());
    }

    @PostMapping("/upload/resume/{jobSeekerId}")
    public ResponseEntity<String> uploadResume(@PathVariable Long jobSeekerId,@RequestPart MultipartFile resume) throws IOException {
        //Long jobSeekerId = 1L;
        //log file name
        System.out.println(resume.getOriginalFilename());
        return new ResponseEntity<String>(resumeService.uploadResume(resume,jobSeekerId), HttpStatus.OK);
    }


    @DeleteMapping("/remove/resume/{jobSeekerId}")
    public ResponseEntity<String> removeResume(@PathVariable Long jobSeekerId) {
        return new ResponseEntity<String>(resumeService.removeResume(jobSeekerId), HttpStatus.OK);
    }

}
