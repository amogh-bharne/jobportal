package com.app.JobApplicationSystem.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.JobApplicationSystem.dto.SkillDto;
import com.app.JobApplicationSystem.entities.Skill;
import com.app.JobApplicationSystem.repository.SkillRepository;


@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<SkillDto>getAllSkills()
	{
		List<Skill>allSkills=skillRepo.findAll();
		
		return allSkills.stream().map((skills)->{
			SkillDto allSkillsHolder=mapper.map(skills, SkillDto.class);
			
			return allSkillsHolder;
		}).collect(Collectors.toList());
		

		
	}
}
