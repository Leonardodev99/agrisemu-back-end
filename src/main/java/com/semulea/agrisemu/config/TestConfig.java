package com.semulea.agrisemu.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.semulea.agrisemu.entties.UserWorker;
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
		
		UserWorker u1 = new UserWorker(null, "Pedro Sambongo", "pedro@gmail.com", "123456");
		UserWorker u2 = new UserWorker(null, "Miguel Canga", "canga@gmail.com", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		UserAdmin a1 = new UserAdmin(null, "Leonardo Miguel", "miguel@gmail.com", "913456", "123456");
		UserAdmin a2 = new UserAdmin(null, "Anibal Miguel", "ani@gmail.com", "9134567", "123456");
		adminRepository.saveAll(Arrays.asList(a1,a2));
		
	}

}
