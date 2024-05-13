package com.api.test.user.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.test.user.h2.entity.OAuthAccessToken;

public interface ITokenRepository extends JpaRepository<OAuthAccessToken, String>{
	
	OAuthAccessToken findByUserName(String email);

}
