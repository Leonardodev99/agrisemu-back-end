package com.semulea.agrisemu.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.entties.employers.enums.Sex;
import com.semulea.agrisemu.entties.employers.enums.StatusCivic;
import com.semulea.agrisemu.entties.employers.enums.TypeContract;
import com.semulea.agrisemu.entties.employers.enums.WorkerLevel;
import com.semulea.agrisemu.repositories.DepartmentRepository;
import com.semulea.agrisemu.repositories.EmployerRepository;
import com.semulea.agrisemu.repositories.UserAdminRepository;
import com.semulea.agrisemu.repositories.UserRepository;
import com.semulea.agrisemu.repositories.UserWorkerRepository;
import com.semulea.agrisemu.repositories.WorkerRepository;

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
	
	@Autowired
	private WorkerRepository workerRepository;

	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate1 = LocalDate.parse("14/04/2002", formatter);
		Instant dateOfBirth1 = localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant();
		
		LocalDate localDate2 = LocalDate.parse("12/04/2002", formatter);
		Instant dateOfBirth2 = localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant();
		
		UserWorker u1 = new UserWorker(null, "Pedro Sambongo", "pedro@gmail.com", "123456");
		UserWorker u2 = new UserWorker(null, "Miguel Canga", "canga@gmail.com", "123456");
		
		userWorkerRepository.saveAll(Arrays.asList(u1, u2));
		
		UserAdmin a1 = new UserAdmin(null, "Leonardo Miguel", "miguel@gmail.com", "913 456 222", "123456");
		UserAdmin a2 = new UserAdmin(null, "Anibal Miguel", "ani@gmail.com", "913 456 700", "123456");
		adminRepository.saveAll(Arrays.asList(a1,a2));
		
		User b1 = new User(null, "ana@gmail.com", "999 999 999", "123456");
		User b2 = new User(null, "dani@gmail.com", "999 978 222", "123456");
		userRepository.saveAll(Arrays.asList(b1,b2));
		
		Employer q1 = new Employer(null, "DUINOR", "3334567890", "Rangel rua da brigada", "999 934 332","duinor@gmail.com", 2);
		Employer q2 = new Employer(null, "Semulea", "3324561234", "São Paulo avenida comandante valodia", "999 834 111","semulea@gmail.com", 1);
		
		Department d1 = new Department(null, "RH", 4, q1);
		Department d2 = new Department(null, "Finanças",10,q2);
		Department d3 = new Department(null, "Finanças",10,q1);
		
		Worker w1 = new Worker(null, "Alex", "005678914LA041", "222 345 111", "a@gmail.com", "Cazenga", dateOfBirth1, "Angolana", Sex.M, StatusCivic.SINGLE, "Técnico Médio", WorkerLevel.JUNIOR, TypeContract.UNDETERMINED_TIME, 20000.00, 1000.0, 50000.00);
		Worker w2 = new Worker(null, "Anna", "005678914LA042", "222 346 202", "an@gmail.com", "Cazenga", dateOfBirth2, "Angolana", Sex.F, StatusCivic.MERRIED, "Técnico Médio", WorkerLevel.MID_LEVEL, TypeContract.DETERMINED_TIME, 20000.00, 1000.0, 50000.00);
		
		employerRepository.saveAll(Arrays.asList(q1,q2));
		departmentRepository.saveAll(Arrays.asList(d1,d2,d3));
		workerRepository.saveAll(Arrays.asList(w1,w2));
		
		w1.getDepartments().add(d1);
		w2.getDepartments().add(d2);
		w2.getDepartments().add(d3);
		
		workerRepository.saveAll(Arrays.asList(w1,w2));
		
	
	}

}
