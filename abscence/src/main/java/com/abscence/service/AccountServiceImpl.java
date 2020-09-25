package com.abscence.service;

import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abscence.dao.CoursRepository;
import com.abscence.dao.RoleRepository;
import com.abscence.dao.UtilisateurRepository;
import com.abscence.entities.Cours;
import com.abscence.entities.Role;
import com.abscence.entities.Utilisateur;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	SmtpMailSender smtpMailSender;
	 @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;
	    @Autowired
	    private UtilisateurRepository utilisateurRepository;
	    @Autowired
	    private RoleRepository roleRepository;
	    @Override
	    public Utilisateur saveUser(Utilisateur user) throws Exception {
	    	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
	    	String pwd = RandomStringUtils.random(8 , characters );
	    	smtpMailSender.send(user.getEmail(), "password for abscence application", "Here is your password"+pwd);
	     System.out.println("email send ************************************************");
	    	String hashPw = bCryptPasswordEncoder.encode(pwd);
	       
	        user.setPassword(hashPw);
	        return utilisateurRepository.save(user);
	    }
	    
	    public Utilisateur updateUser(Utilisateur userJson,Utilisateur userfromdb) throws Exception {
	    	
	    
	    
	       if(userJson.getAdress()!=null) {
	    	   userfromdb.setAdress(userJson.getAdress());
	       }
	       if(userJson.getEmail()!=null) {
	    	   userfromdb.setEmail(userJson.getEmail());
	       }
	       if(userJson.getCne()!=null) {
	    	   userfromdb.setCne(userJson.getCne());
	       }
	       if(userJson.getNom()!=null) {
	    	   userfromdb.setNom(userJson.getNom());
	       }
	       if(userJson.getPrenom()!=null) {
	    	   userfromdb.setPrenom(userJson.getPrenom());
	       }
	       if(userJson.getCours()!=null) {
	    	   userfromdb.setCours(userJson.getCours());
	       }
	       if(userJson.getRoles()!=null) {
	    	   userfromdb.setRoles(userJson.getRoles());
	       }
	       
	        return utilisateurRepository.save(userfromdb);
	    }
	    

	    @Override
	    public Role saveRole(Role role) {
	        return roleRepository.save(role);
	    }

	    @Override
	    public void addRoleToUser(String email, String roleName) {
	        Role role = roleRepository.findByNom(roleName);
	        Utilisateur user = utilisateurRepository.findByEmail(email);
	        user.getRoles().add(role);
	    }

	    @Override
	    public Utilisateur findUserByEmail(String email) {
	        return utilisateurRepository.findByEmail(email);
	    }
@Autowired
CoursRepository coursRepository;
		@Override
		public void addCourseToUser(String coursName, String userName) {
			Cours c = coursRepository.findByNom(coursName);
			Utilisateur u = utilisateurRepository.findByNom(userName);
			u.getCours().add(c);
			
		}
}
