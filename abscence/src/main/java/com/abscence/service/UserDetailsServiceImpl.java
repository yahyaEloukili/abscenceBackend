package com.abscence.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abscence.entities.Utilisateur;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = accountService.findUserByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException(email);
        }
        Collection<GrantedAuthority> authotities = new ArrayList<>();
        user.getRoles().forEach(r->{authotities.add(new SimpleGrantedAuthority(r.getNom()));});
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authotities);
    }
}
