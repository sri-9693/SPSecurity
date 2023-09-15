package com.sri.spsec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sri.spsec.entity.Users;
import com.sri.spsec.repository.UsersRepository;


@Service
public class UsersService 
{
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly=true)
	public Users getByUsername(String username)
	{
	    Optional<Users> ou = usersRepository.findById(username);
	    if(ou.isPresent()) return ou.get();
	    return null;
	}
	
	@Transactional(readOnly=true)
	public Users getByUsernameAndPassword(String username,String password)
	{
		Optional<Users> ou = usersRepository.findByUsernameAndPassword(username, password);
		if(ou.isPresent()) return ou.get();
	    return null;
	}
	
	@Transactional()
	public boolean addUsers(Users user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Users u = usersRepository.save(user);
		return u!=null;
	}
	

}
