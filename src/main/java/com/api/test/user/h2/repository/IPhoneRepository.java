package com.api.test.user.h2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.test.user.h2.entity.Phone;

public interface IPhoneRepository extends JpaRepository<Phone, UUID>{

}
