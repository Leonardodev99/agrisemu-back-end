package com.semulea.agrisemu.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.semulea.agrisemu.entties.User;
import com.semulea.agrisemu.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Pedro Sambongo", "pedro@gmail.com", "123456");
		User u2 = new User(null, "Miguel Canga", "canga@gmail.com", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}

}
