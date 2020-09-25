package com.abscence;

import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.abscence.dao.CoursRepository;
import com.abscence.dao.RoleRepository;
import com.abscence.dao.UtilisateurRepository;
import com.abscence.entities.Cours;
import com.abscence.entities.Role;
import com.abscence.entities.Sceance;
import com.abscence.entities.Utilisateur;
import com.abscence.service.AccountService;


import net.bytebuddy.utility.RandomString;


@SpringBootApplication()
public class AbscenceApplication implements CommandLineRunner {
	@Autowired
	 AccountService accountService;
	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CoursRepository coursRepository;
	
	
	
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
}
	public static void main(String[] args) {
		SpringApplication.run(AbscenceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
		repositoryRestConfiguration.exposeIdsFor(Utilisateur.class);
		repositoryRestConfiguration.exposeIdsFor(Role.class);
		repositoryRestConfiguration.exposeIdsFor(Cours.class);
		repositoryRestConfiguration.exposeIdsFor(Sceance.class);
		
		//add roles
		accountService.saveRole(new Role(1L,"Prof",null));
		accountService.saveRole(new Role(2L,"Etudiant",null));
		accountService.saveRole(new Role(3L,"Admin",null));
		Cours c = new Cours(1L,"gpmc",null,null);
			coursRepository.save(c);
		//add admin
		Utilisateur admin = new Utilisateur(null,"admin","admin","admin@hotmail.com",null,null,null,true,null,null,null);
		admin.setPassword(bCryptPasswordEncoder.encode("1234"));
		utilisateurRepository.save(admin);
		accountService.addRoleToUser(admin.getEmail(), "Admin");
		//add prof
		Utilisateur prof = new Utilisateur(null,"prof","prof","prof@hotmail.com",null,null,null,true,null,null,null);
		prof.setAdress("adress");
		prof.setPassword(bCryptPasswordEncoder.encode("1234"));
		utilisateurRepository.save(prof);
		accountService.addCourseToUser("gpmc", "prof");
		
		
		accountService.addRoleToUser(prof.getEmail(), "Prof");
		//add 10 enseignants
		//add 10 enseignants
		Random rnd = new Random();
		for(int i=0 ;i<10;i++) {
			Utilisateur p = new Utilisateur();
			p.setNom(RandomString.make(18));
			p.setPrenom(RandomString.make(18));
			
			p.setEmail(RandomString.make(7)+"@hotmail.com");
			p.setTelephone("0665656532");
			p.setAdress(RandomString.make(27));
			p.setPassword(bCryptPasswordEncoder.encode("1234"));
			
		utilisateurRepository.save(p);
		accountService.addRoleToUser(p.getEmail(), "Prof");
		
		
		
		
		}
		//add 10 etudiants
		for(int i=0 ;i<10;i++) {
			Utilisateur p = new Utilisateur();
			p.setNom(RandomString.make(18));
			p.setPrenom(RandomString.make(18));
			
			p.setEmail(RandomString.make(7)+"@hotmail.com");
			p.setTelephone("0665656532");
			p.setGender("male");
			p.setAdress(RandomString.make(27));
			p.setPassword(bCryptPasswordEncoder.encode("1234"));
			
		utilisateurRepository.save(p);
		accountService.addRoleToUser(p.getEmail(), "Etudiant");
		//add 10 etudiants
		
		
		
		}
		
		
		
		
	}


/*spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=yahya
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect*/

}
