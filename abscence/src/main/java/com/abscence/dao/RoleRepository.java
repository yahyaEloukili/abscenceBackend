package com.abscence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;


import com.abscence.entities.Role;
import com.abscence.entities.Sceance;
import com.abscence.entities.Utilisateur;

@CrossOrigin(value = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE} )
@RepositoryRestResource
public  interface RoleRepository extends JpaRepository<Role, Long>{

	Role  findByNom(String roleName);
}
