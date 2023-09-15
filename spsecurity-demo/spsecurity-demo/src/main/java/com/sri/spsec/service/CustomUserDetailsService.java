package com.sri.spsec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sri.spsec.entity.Users;
import com.sri.spsec.model.CustomUserDetails;
import com.sri.spsec.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    UsersRepository usersRepository;
    
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Optional<Users> ou = usersRepository.findById(username);
		return ou.map(CustomUserDetails::new).orElseThrow(()
				   -> new UsernameNotFoundException("User Doenot Exist"));
	                          	
	}
	

}
