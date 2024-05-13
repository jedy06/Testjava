package com.api.test.user.h2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.test.user.h2.entity.Phone;
import com.api.test.user.h2.repository.IPhoneRepository;
import com.api.test.user.h2.service.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService{
	
	@Autowired
	IPhoneRepository phoneRepo;

	@Override
	public Phone registar(Phone phone) {
		Phone phoneEntity =  phoneRepo.save(phone);
		return phoneEntity;
	}

}
