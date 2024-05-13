package com.api.test.user.h2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.test.user.h2.entity.User;

public interface IUserRepository extends JpaRepository<User, UUID>{
	
	User findByEmail(String email);

}
