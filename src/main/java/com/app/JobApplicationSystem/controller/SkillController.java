package com.app.JobApplicationSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.JobApplicationSystem.dto.SkillDto;
import com.app.JobApplicationSystem.service.SkillService;

@RestController
@RequestMapping("/skill")
@CrossOrigin
public class SkillController {

	@Autowired
	private SkillService skillService;
	
	@GetMapping("/get")
	public List<SkillDto> getAllSkills()
	{
		return skillService.getAllSkills();
	}
	
	
}
