package com.dakor.app;

import com.dakor.app.data.entity.UserRole;
import com.dakor.app.model.UserDto;
import com.dakor.app.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoopMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoopMeApplication.class, args);
	}

	@Bean
	public CommandLineRunner getCommandLineRunner(IUserService userService) {
		return args -> {
			UserDto admin = userService.getUserByName("admin");
			if (admin == null) {
				// create an initial administrator
				admin = new UserDto();
				admin.setUserName("admin");
				admin.setPassword("admin");
				admin.setRole(UserRole.ADMIN);
				userService.save(admin);
			}
		};
	}
}
