package com.semulea.agrisemu.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.semulea.agrisemu.entties.User;
import com.semulea.agrisemu.entties.UserAdmin;
import com.semulea.agrisemu.entties.UserWorker;
import com.semulea.agrisemu.entties.employers.Department;
import com.semulea.agrisemu.entties.employers.Employer;
import com.semulea.agrisemu.repositories.DepartmentRepository;
import com.semulea.agrisemu.repositories.EmployerRepository;
import com.semulea.agrisemu.repositories.UserAdminRepository;
import com.semulea.agrisemu.repositories.UserRepository;
import com.semulea.agrisemu.repositories.UserWorkerRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserWorkerRepository userWorkerRepository;
	
	@Autowired
	private UserAdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public void run(String... args) throws Exception {
		
		UserWorker u1 = new UserWorker(null, "Pedro Sambongo", "pedro@gmail.com", "123456");
		UserWorker u2 = new UserWorker(null, "Miguel Canga", "canga@gmail.com", "123456");
		
		userWorkerRepository.saveAll(Arrays.asList(u1, u2));
		
		UserAdmin a1 = new UserAdmin(null, "Leonardo Miguel", "miguel@gmail.com", "913 456 222", "123456");
		UserAdmin a2 = new UserAdmin(null, "Anibal Miguel", "ani@gmail.com", "913 456 700", "123456");
		adminRepository.saveAll(Arrays.asList(a1,a2));
		
		User b1 = new User(null, "ana@gmail.com", "999 999 999", "123456");
		User b2 = new User(null, "dani@gmail.com", "999 978 222", "123456");
		userRepository.saveAll(Arrays.asList(b1,b2));
		
		Employer q1 = new Employer(null, "DUINOR", 333456L, "Rangel rua da brigada", "999934", 2);
		Employer q2 = new Employer(null, "Semulea", 332456L, "São Paulo avenida comandante valodia", "999834", 1);
		
		Department d1 = new Department(null, "RH", 4, q1);
		Department d2 = new Department(null, "Finanças", 10, q1);
		Department d3 = new Department(null, "Finanças", 10, q2);
		
		employerRepository.saveAll(Arrays.asList(q1,q2));
		departmentRepository.saveAll(Arrays.asList(d1,d2,d3));
		
	
	}

}
