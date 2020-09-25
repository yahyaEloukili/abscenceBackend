package com.abscence.dao;



import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import javax.transaction.Transactional;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;


import com.abscence.entities.Sceance;
import com.abscence.entities.Utilisateur;


@CrossOrigin(value = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE} )
@RepositoryRestResource
public  interface SceanceRepository extends JpaRepository<Sceance, Long>{
	
    Sceance findByDate(Date date1);
    Sceance findByDebut(LocalTime debut);
	@Query("select s from Sceance s "
	          + "where ( s.date=:date) "
	          + "and (s.debut=:debut) "
	        
	          + "and (s.fin=:fin)")
	    Sceance findSceance(@Param("date") Date date,
	                               @Param("debut") LocalTime debut,
	                               @Param("fin") LocalTime fin
	                              );
	@Query("select s from Sceance s "
	          + "where (s.debut=:debut) "
	          + "and (s.fin=:fin) ")
	        
	          
	    Sceance findSceance2(
	                               @Param("debut") LocalTime debut,
	                               @Param("fin") LocalTime fin
	                              );
}
