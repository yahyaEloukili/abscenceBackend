package com.abscence.dao;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abscence.entities.Utilisateur;

@CrossOrigin(value = "*",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH} )
@RepositoryRestResource
public  interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{


	@RestResource(path ="/utilisateursByEmail")
    Utilisateur findByEmail(@Param("email") String email);

    
	@RestResource(path ="/utilisateursBykeyword")
	public Page<Utilisateur> findByNomContains(@Param("nom") String mc,Pageable pageable);


	Utilisateur findByNom(String userName);



}
