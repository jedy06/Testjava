package com.api.test.user.h2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.test.user.h2.entity.User;
import com.api.test.user.h2.repository.IUserRepository;
import com.api.test.user.h2.service.UserService;

@Service
public class MyUserDetailServiceImp implements UserDetailsService{
	
	
	
	@Autowired
//	private IUserRepository userRepo;
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("ENTRA EN LOADBYUSER*************************************************");
//			User user = userRepo.findByEmail(username);
			User user = userService.obtenerPorEmail(username);
			if (user == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
			}
			List<GrantedAuthority> roles = new ArrayList<>();
			user.getRoles().forEach(rol-> {
				roles.add(new SimpleGrantedAuthority(rol.getRolName()));
			});
			
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
			return userDetails;
		
	}

}
