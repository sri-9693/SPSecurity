package com.sri.spsec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.sri.spsec.entity.Employee;
import com.sri.spsec.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController
{
	@Autowired
	EmployeeService employeeService;
    
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping(produces="application/json")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<List<Employee>> getAllEmployees()
	{
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(),HttpStatus.OK);
	}
	
	@GetMapping(value="/id/{employeeId}",produces="application/json")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId)
	{
		Employee e = employeeService.getEmployeeById(employeeId);
		if(e!=null) return new ResponseEntity<Employee>(e,HttpStatus.OK);
		return new ResponseEntity<Employee>(e,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes="application/json")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public HttpStatus addEmployee(@RequestBody Employee employee)
	{
		if(employeeService.insertOrModifyEmployee(employee))
		   return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@PutMapping(consumes="application/json")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public HttpStatus modifyEmployee(@RequestBody Employee employee)
	{
		if(employeeService.insertOrModifyEmployee(employee))
		   return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@DeleteMapping(value="/{employeeId}",consumes="application/json")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public HttpStatus deleteEmployeeById(@PathVariable int employeeId)
	{
		if(employeeService.deleteEmployeeById(employeeId)) 
			return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
}


