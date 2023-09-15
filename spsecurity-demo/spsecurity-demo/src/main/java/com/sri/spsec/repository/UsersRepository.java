package com.sri.spsec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sri.spsec.entity.Users;

public interface UsersRepository extends JpaRepository<Users,String>
{
     Optional<Users> findByUsernameAndPassword(String username,String password);
}
