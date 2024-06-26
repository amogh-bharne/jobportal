package com.app.JobApplicationSystem.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.JobApplicationSystem.entities.JobSeeker;
import com.app.JobApplicationSystem.entities.Resume;
import com.app.JobApplicationSystem.exceptions.ResourceNotFoundException;
import com.app.JobApplicationSystem.repository.JobSeekerRepository;
import com.app.JobApplicationSystem.repository.ResumeRepository;

import org.springframework.util.StringUtils;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepo;

    @Autowired
    private JobSeekerRepository jobSeekerRepo;

    public String uploadResume(MultipartFile file, Long jobSeekerId) throws IOException {
        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("Job seeker with given id doesn't exists"));
        if(resumeRepo.findResumeByJobSeekerId(jobSeekerId).isPresent()){
            return "resume already uploaded!! remove first to add new";
        }
        if (file.isEmpty()) {
            return "file is empty";
        }


        Resume resume = new Resume();
        resume.setJobseeker(seeker);
        resume.setResumeFile(file.getBytes());
        resumeRepo.save(resume);
        return "file uploaded";
    }


    public InputStream getResume(Long id) {
        Resume resume = resumeRepo.findResumeByJobSeekerId(id).orElseThrow(() -> new ResourceNotFoundException("resume hasn't uploaded yet"));
        return new ByteArrayInputStream(resume.getResumeFile());
    }

    public String removeResume(Long jobSeekerId) {
        Optional<Resume> resumeHolder = resumeRepo.findResumeByJobSeekerId(jobSeekerId);
        if(!resumeHolder.isPresent()){
            return "there is nothing to remove";
        }

        Resume resume = resumeHolder.get();
        resumeRepo.delete(resume);
        return "resume removed succefully";
    }

}
