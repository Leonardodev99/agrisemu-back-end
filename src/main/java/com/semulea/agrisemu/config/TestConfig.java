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
import com.semulea.agrisemu.entties.employers.CompanySector;
import com.semulea.agrisemu.entties.employers.Contract;
import com.semulea.agrisemu.entties.employers.Department;
import com.semulea.agrisemu.entties.employers.Employer;
import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.entties.employers.enums.CompanyType;
import com.semulea.agrisemu.entties.employers.enums.EconomicActivity;
import com.semulea.agrisemu.entties.employers.enums.MarketSector;
import com.semulea.agrisemu.entties.employers.enums.Sex;
import com.semulea.agrisemu.entties.employers.enums.StatusCivic;
import com.semulea.agrisemu.entties.employers.enums.TypeContract;
import com.semulea.agrisemu.entties.employers.enums.WorkerLevel;
import com.semulea.agrisemu.repositories.CompanySectorRepository;
import com.semulea.agrisemu.repositories.ContractRepository;
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
	
	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	private CompanySectorRepository companySectorRepository;

	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		LocalDate localDate1 = LocalDate.parse("14/04/2002", dateFormatter);
		Instant dateOfBirth1 = localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant();
		
		LocalDate localDate2 = LocalDate.parse("12/04/2002", dateFormatter);
		Instant dateOfBirth2 = localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant();
		
		
		LocalDate localDateTimeInitial1 = LocalDate.parse("14/04/2002 10:30", dateTimeFormatter);
		Instant initialDate1 = localDateTimeInitial1.atStartOfDay(ZoneId.systemDefault()).toInstant();
		LocalDate localDateTimeFinal1 = LocalDate.parse("14/04/2003 10:30", dateTimeFormatter);
		Instant finalDate1 = localDateTimeFinal1.atStartOfDay(ZoneId.systemDefault()).toInstant();
		
		LocalDate localDateTimeInitial2 = LocalDate.parse("14/04/2004 10:30", dateTimeFormatter);
		Instant initialDate2 = localDateTimeInitial2.atStartOfDay(ZoneId.systemDefault()).toInstant();
		LocalDate localDateTimeFinal2 = LocalDate.parse("14/04/2005 10:30", dateTimeFormatter);
		Instant finalDate2 = localDateTimeFinal2.atStartOfDay(ZoneId.systemDefault()).toInstant();
		
		
		LocalDate localDateTimeInitial3 = LocalDate.parse("14/04/2004 12:30", dateTimeFormatter);
		Instant registrationDate1 = localDateTimeInitial3.atStartOfDay(ZoneId.systemDefault()).toInstant();
		LocalDate localDateTimeFinal3 = LocalDate.parse("14/04/2005 14:30", dateTimeFormatter);
		Instant registrationDate2 = localDateTimeFinal3.atStartOfDay(ZoneId.systemDefault()).toInstant();
		
		
		UserWorker u1 = new UserWorker(null, "Pedro Sambongo", "pedro@gmail.com", "123456");
		UserWorker u2 = new UserWorker(null, "Miguel Canga", "canga@gmail.com", "123456");
		
		userWorkerRepository.saveAll(Arrays.asList(u1, u2));
		
		UserAdmin a1 = new UserAdmin(null, "Leonardo Miguel", "miguel@gmail.com", "913 456 222", "123456");
		UserAdmin a2 = new UserAdmin(null, "Anibal Miguel", "ani@gmail.com", "913 456 700", "123456");
		adminRepository.saveAll(Arrays.asList(a1,a2));
		
		User b1 = new User(null, "ana@gmail.com", "999 999 999", "123456");
		User b2 = new User(null, "dani@gmail.com", "999 978 222", "123456");
		userRepository.saveAll(Arrays.asList(b1,b2));
		
		CompanySector cs1 = new CompanySector(null, EconomicActivity.AGRICULTURE, CompanyType.SMALL, MarketSector.PRIVATE_SECTOR);
		CompanySector cs2 = new CompanySector(null, EconomicActivity.INFORMATION_TECHNOLOGY, CompanyType.MEDIUM, MarketSector.PRIVATE_SECTOR);
		
		Employer q1 = new Employer(null, "DUINOR", "3334567890", "Rangel rua da brigada", "999 934 332","duinor@gmail.com", 2,registrationDate1);
		Employer q2 = new Employer(null, "Semulea", "3324561234", "São Paulo avenida comandante valodia", "999 834 111","semulea@gmail.com", 1, registrationDate2);
		
		companySectorRepository.saveAll(Arrays.asList(cs1,cs2));
		employerRepository.saveAll(Arrays.asList(q1,q2));
		
		q1.getCompanySectors().add(cs2);
		q2.getCompanySectors().add(cs1);
		
		cs1.getEmployers().add(q2);
        cs2.getEmployers().add(q1);
		
		
        companySectorRepository.saveAll(Arrays.asList(cs1, cs2));
        employerRepository.saveAll(Arrays.asList(q1, q2));
		
		Department d1 = new Department(null, "RH", 4, q1, "Miguel", "901 222 234", "rh@gmail.com");
		Department d2 = new Department(null, "Finanças",10,q2, "Alex", "901 222 230", "financa@gmail.com");
		Department d3 = new Department(null, "Finanças",10,q1, "Anna", "901 222 239", "financa@gmail.com");
		
		Worker w1 = new Worker(null, "Alex", "005678914LA041", "222 345 111", "a@gmail.com", "Cazenga", dateOfBirth1, "Angolana", Sex.M, StatusCivic.SINGLE, "Técnico Médio", WorkerLevel.JUNIOR, TypeContract.UNDETERMINED_TIME, 20000.00, 1000.0, 50000.00, 1, 100000.0, 80000.0, 30L);
		Worker w2 = new Worker(null, "Anna", "005678914LA042", "222 346 202", "an@gmail.com", "Cazenga", dateOfBirth2, "Angolana", Sex.F, StatusCivic.MERRIED, "Técnico Médio", WorkerLevel.MID_LEVEL, TypeContract.DETERMINED_TIME, 20000.00, 1000.0, 50000.00, 1, 100000.0, 80000.0, 30l);
		
		Contract c1 = new Contract(null, initialDate1, finalDate1, 1000.00, 8L, 1000.0, 2000.00, 3.0, w2);
		Contract c2 = new Contract(null, initialDate2, finalDate2, 1000.00, 8L, 1000.0, 2000.00, 7.0, w1);
		
		
		
		
		
		
		
		
       
		
		departmentRepository.saveAll(Arrays.asList(d1,d2,d3));
		workerRepository.saveAll(Arrays.asList(w1,w2));
		employerRepository.saveAll(Arrays.asList(q1,q2));
		
		w1.getDepartments().add(d1);
		w2.getDepartments().add(d2);
		w2.getDepartments().add(d3);
		
		workerRepository.saveAll(Arrays.asList(w1,w2));
		contractRepository.saveAll(Arrays.asList(c1,c2));
		
	
	}

}
