package com.app.JobApplicationSystem;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.JobApplicationSystem.entities.Admin;
import com.app.JobApplicationSystem.entities.User;
import com.app.JobApplicationSystem.repository.AdminRepository;
import com.app.JobApplicationSystem.Singleton.AdminSingleton;
import com.app.JobApplicationSystem.repository.UserRepository;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JobPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalApplication.class, args);
	}
	@Bean
	public ModelMapper mapper()
	{
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}

	@Bean
	CommandLineRunner run(AdminRepository adminRepo, UserRepository userRepo) {
		return args -> {
			System.out.println("Starting Job Application system...");

			User existingAdmin = userRepo.findByRole("ROLE_ADMIN");

			if(existingAdmin == null) {

				Admin admin = AdminSingleton.getInstance();

				adminRepo.save(admin);

				User user = new User();
				user.setEmail(admin.getEmail());
				user.setPassword(admin.getPassword());
				user.setRole("ROLE_ADMIN");
				userRepo.save(user);
			}


		};
	}

}
