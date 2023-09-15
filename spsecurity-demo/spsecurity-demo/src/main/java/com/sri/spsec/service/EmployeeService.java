package com.sri.spsec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sri.spsec.entity.Employee;
import com.sri.spsec.repository.EmployeeRepository;



@Service
public class EmployeeService 
{
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Transactional(readOnly=true)
	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	
	public Employee getEmployeeById(int employeeId)
	 {
		Optional<Employee> oe = employeeRepository.findById(employeeId);
		if(oe.isPresent()) return oe.get();
		return null;
	 }
	
	@Transactional
	public boolean insertOrModifyEmployee(Employee employee)
	{
		Employee e = employeeRepository.save(employee);
		return e!=null;
	}
    
	@Transactional
	public boolean deleteEmployeeById(int employeeId)
	{
		long count = employeeRepository.count();
		employeeRepository.deleteById(employeeId);
		return count != employeeRepository.count();
	}
}
