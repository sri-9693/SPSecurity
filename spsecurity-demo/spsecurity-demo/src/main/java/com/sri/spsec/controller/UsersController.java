package com.sri.spsec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sri.spsec.entity.Users;
import com.sri.spsec.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController 
{
	@Autowired
	UsersService usersService;
	
	@PostMapping(consumes="application/json")
	public HttpStatus addUser(@RequestBody Users user)
	{
		if(usersService.addUsers(user)) return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	

}
