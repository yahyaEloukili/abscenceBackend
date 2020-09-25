package com.abscence.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abscence.entities.Cours;
import com.abscence.entities.Utilisateur;

@CrossOrigin(value = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE} )
@RepositoryRestResource
public  interface CoursRepository extends JpaRepository<Cours, Long>{

	@RestResource(path ="/moduleByKeyWord")
	public Page<Cours> findByNomContains(@Param("nom") String mc,Pageable pageable);

	public Cours findByNom(String coursName);
}
