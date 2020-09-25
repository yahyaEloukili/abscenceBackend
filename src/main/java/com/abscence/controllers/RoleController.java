package com.abscence.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abscence.dao.CoursRepository;
import com.abscence.dao.RoleRepository;
import com.abscence.dao.UtilisateurRepository;
import com.abscence.entities.Cours;
import com.abscence.entities.Role;
import com.abscence.entities.Sceance;
import com.abscence.entities.SceancesPage;
import com.abscence.entities.Utilisateur;
import com.abscence.entities.utilisateursPage;

@RestController
public class RoleController {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	CoursRepository coursRepository;
	
	@GetMapping("v2/roles/{id}/utilisateurs")
	public utilisateursPage getUsersOfRole(@PathVariable Long id,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name = "size",defaultValue = "5") int size
			,@RequestParam(name = "nom",required = false) String nom){
		Role role = roleRepository.findById(id).get();
		List<Utilisateur> utilisateurs =  role.getUtilisateurs();
		List<Utilisateur> filteredUtilisateurs = new ArrayList<Utilisateur>();
		PagedListHolder page2 ;
		if(nom!=null && !nom.equals("")) {
		for(Utilisateur u : utilisateurs) {
			
			if(u.getNom().contains(nom)) {
				
			filteredUtilisateurs.add(u);
		}
		}
		
		 page2 = new PagedListHolder(filteredUtilisateurs);
		}else {
			 page2 = new PagedListHolder(utilisateurs);
		}
		page2.setPageSize(size);
		page2.setPage(page);
		page2.getPageCount(); // number of pages 
		page2.getPageList();
		
		utilisateursPage utilisateursPage = new utilisateursPage(page2.getPageList(), page2.getPageCount());
		return  utilisateursPage;
	}
	

@Autowired
UtilisateurRepository utilisateurRepository;
	
	@GetMapping("v2/utilisateurs/{id}/sceances")
	public SceancesPage getSceancesOfUser(@PathVariable Long id,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name = "size",defaultValue = "5") int size){
		Utilisateur user = utilisateurRepository.findById(id).get();
		List<Sceance> sceances =  user.getSceances();
		PagedListHolder page2 ;
		
			 page2 = new PagedListHolder(sceances);
		
		page2.setPageSize(size);
		page2.setPage(page);
		page2.getPageCount(); // number of pages 
		page2.getPageList();
		
		SceancesPage sceancesPage = new SceancesPage(page2.getPageList(), page2.getPageCount());
		return  sceancesPage;
	}
	
	@GetMapping("v2/courses/{id}/utilisateurs")
	public utilisateursPage getUsersOfCourse(@PathVariable Long id,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name = "size",defaultValue = "5") int size
			,@RequestParam(name = "nom",required = false) String nom){
		Role role = roleRepository.findByNom("Etudiant");
		Cours cours = coursRepository.findById(id).get();
		List<Utilisateur> utilisateurs =  cours.getUtilisateurs();
		List<Utilisateur> users = new ArrayList<Utilisateur>();
		for(Utilisateur u : utilisateurs) {
			if(u.getRoles().contains(role)) {
				users.add(u);
			}
		}
		List<Utilisateur> filteredUtilisateurs = new ArrayList<Utilisateur>();
		PagedListHolder page2 ;
		if(nom!=null && !nom.equals("")) {
		for(Utilisateur u : users) {
			
			if(u.getNom().contains(nom)) {
				
			filteredUtilisateurs.add(u);
		}
		}
		
		 page2 = new PagedListHolder(filteredUtilisateurs);
		}else {
			 page2 = new PagedListHolder(users);
		}
		page2.setPageSize(size);
		page2.setPage(page);
		page2.getPageCount(); // number of pages 
		page2.getPageList();
		
		utilisateursPage utilisateursPage = new utilisateursPage(page2.getPageList(), page2.getPageCount());
		return  utilisateursPage;
	}
	
	
	
	
	
	
	
	
}
