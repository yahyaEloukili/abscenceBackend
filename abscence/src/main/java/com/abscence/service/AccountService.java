package com.abscence.service;

import com.abscence.entities.Cours;
import com.abscence.entities.Role;
import com.abscence.entities.Utilisateur;

public interface AccountService {

	Utilisateur saveUser(Utilisateur user) throws Exception;
	Role saveRole(Role role);
	void addRoleToUser(String email, String roleName);
	Utilisateur findUserByEmail(String email);

	Utilisateur updateUser(Utilisateur userJson,Utilisateur userfromdb) throws Exception;

	void addCourseToUser(String coursName, String userName);
}
