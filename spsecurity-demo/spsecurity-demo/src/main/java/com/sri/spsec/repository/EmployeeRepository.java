package com.sri.spsec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sri.spsec.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> 
{

}
