package com.semulea.agrisemu.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.semulea.agrisemu.entties.User;
import com.semulea.agrisemu.entties.UserAdmin;
import com.semulea.agrisemu.repositories.UserAdminRepository;
import com.semulea.agrisemu.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserAdminRepository adminRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Pedro Sambongo", "pedro@gmail.com", "$2a$10$S5fAGhBxeRP91DKj0ohZhu78jVjCmFCEi2HRv0xPwTk6cC4SIwWNq");
		User u2 = new User(null, "Miguel Canga", "canga@gmail.com", "$2a$10$PFYpZUPIuphIdX7h8SBE0eycKSb/xfPCeNGMsKH47hQN2XmrmnrLu");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		UserAdmin a1 = new UserAdmin(null, "Leonardo Miguel", "miguel@gmail", "913456", "$2a$10$.rr9YMCBrt8KzkRdM09HZ.78Oucvc14wpMCU6wjgNMxY0OtEW8Ooq");
		UserAdmin a2 = new UserAdmin(null, "Anibal Miguel", "ani@gmail", "9134567", "$2a$10$eDIU6RRhhAIiGJufVk9zfeO51zaNVlmFmemRJkuJ2oB67YY.vU8m.");
		adminRepository.saveAll(Arrays.asList(a1,a2));
		
	}

}
