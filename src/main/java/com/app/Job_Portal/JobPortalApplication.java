package com.app.Job_Portal;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.Job_Portal.entities.Admin;
import com.app.Job_Portal.entities.User;
import com.app.Job_Portal.repository.AdminRepository;
import com.app.Job_Portal.Singleton.AdminSingleton;
import com.app.Job_Portal.repository.UserRepository;

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

	// just a utility to populate the data
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
