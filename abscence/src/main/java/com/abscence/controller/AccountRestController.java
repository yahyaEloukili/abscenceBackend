package com.abscence.controller;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abscence.dao.CoursRepository;
import com.abscence.dao.RoleRepository;
import com.abscence.dao.SceanceRepository;
import com.abscence.dao.UtilisateurRepository;
import com.abscence.entities.Cours;
import com.abscence.entities.Role;
import com.abscence.entities.Sceance;
import com.abscence.entities.Utilisateur;
import com.abscence.service.AccountService;

@RestController
@CrossOrigin(value = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE} )
public class AccountRestController {

@Autowired
UtilisateurRepository utilisateurRepository;
@Autowired
SceanceRepository sceanceRepository;
@Autowired
RoleRepository roleRepository;
@Autowired
CoursRepository coursRepository;
    @Autowired
    AccountService accountService;
    @PostMapping(value="/register",consumes={"application/json"})
    public Utilisateur register(@Valid @RequestBody Utilisateur userForm) throws Exception {
    	if(userForm.getEmail()!=null) {
    		Utilisateur user=  utilisateurRepository.findByEmail(userForm.getEmail());
    		if(user!=null) {
    			throw new RuntimeException("user already exist");
    		}
    	}
    	
    		

    Collection<Role> roles = new ArrayList<Role>(); 
    if(userForm.getRoles_id()!=null && userForm.getRoles_id().size()!=0) {
    	
   for (Long id  :  userForm.getRoles_id()) {
	
	   Role role =	roleRepository.findById(id).get();
	   roles.add(role);
	
   }
   userForm.setRoles(roles);
}
    Collection<Cours> courses = new ArrayList<Cours>(); 
    if(userForm.getCourses_id()!=null && userForm.getCourses_id().size()!=0) {
    	
    	   for (Long id  :  userForm.getCourses_id()) {
    		
    		   Cours cours =	coursRepository.findById(id).get();
    		   courses.add(cours);
    		
    	   }
    	  
    	   userForm.setCours(courses);
    	  
    	}
  
  accountService.saveUser(userForm);
   

   
      
   return  userForm;
    }
 
    
    @PutMapping(value="/updateUser/{idd}")
    public Utilisateur update(@RequestBody Utilisateur userForm, @PathVariable Long idd) throws Exception {
    Utilisateur userFromDb = utilisateurRepository.getOne(idd);

    		Collection<Role> roles = new ArrayList<Role>(); 
    if(userForm.getRoles_id()!=null && userForm.getRoles_id().size()!=0) {
    	
   for (Long id  :  userForm.getRoles_id()) {
	
	   Role role =	roleRepository.findById(id).get();
	   roles.add(role);
	
   }
   userForm.setRoles(roles);
}
    Collection<Cours> courses = new ArrayList<Cours>(); 
    if(userForm.getCourses_id()!=null && userForm.getCourses_id().size()!=0) {
    	
    	   for (Long id  :  userForm.getCourses_id()) {
    		
    		   Cours cours =	coursRepository.findById(id).get();
    		   courses.add(cours);
    		
    	   }
    	  
    	   userForm.setCours(courses);
    	  
    	}
  return   accountService.updateUser(userForm,userFromDb);
    }


@GetMapping("/v2/sceances")
public Sceance getSceances4(@RequestParam(value= "date", required = false)  
String date,@RequestParam(value= "debut", required = false)  
String debut,@RequestParam(value= "fin", required = false)  
String fin) throws ParseException{
	   SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
	   Date date1 = f.parse(date);
	   java.sql.Date someDate = new java.sql.Date(date1.getTime()+3600000);
	LocalTime debut1 = LocalTime.parse(debut);
	LocalTime fin2 = LocalTime.parse(fin);
  Sceance s = sceanceRepository.findSceance(someDate,debut1, fin2);
  return s;

	

	

}







}
