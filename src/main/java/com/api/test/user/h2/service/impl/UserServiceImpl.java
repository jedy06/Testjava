package com.api.test.user.h2.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.test.user.h2.entity.Phone;
import com.api.test.user.h2.entity.Rol;
import com.api.test.user.h2.entity.User;
import com.api.test.user.h2.repository.IPhoneRepository;
import com.api.test.user.h2.repository.IUserRepository;
import com.api.test.user.h2.service.TokenService;
import com.api.test.user.h2.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	IUserRepository userRepo;
	
	@Autowired
	IPhoneRepository phoneRepo;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public User registrar(User user) throws Exception {
		
		if (!validarString(user.getEmail(), "correo")) {
			throw new Exception("el correo no cumple con el formato correcto");
		} else if(!validarString(user.getPassword(), "password")){
			throw new Exception("la constrasena no cumple el formato correcto");
		}
		user.setPassword(bcrypt.encode(user.getPassword()));
		user.setCreated(new Date(System.currentTimeMillis()));
		user.setModified(new Date(System.currentTimeMillis()));
		user.setLast_login(new Date(System.currentTimeMillis()));
		user.setEnabled(true);
		Rol rol = new Rol();
		rol.setDescription("user");
		rol.setRolName("user");
		List<Rol> lr = new ArrayList<>();
		lr.add(rol);
		user.setRoles(lr);
		
		List<Phone> phones = user.getPhones();
		user.setPhones(null);
		try {
			userRepo.save(user);			
		} catch (DataIntegrityViolationException e) {
			throw new Exception("El correo ya registrado");
		}
		phones.forEach(phone -> {
		    phone.setUser_id(user.getIdUser());
		});
//		for (Phone phone : phones) {
//			phone.setUser_id(user.getIdUser());;
//		}
		phoneRepo.saveAll(phones);
		user.setPhones(phones);
		return user;
		
	}
	
	public User actualizar(User user) {
		user.setModified(new Date(System.currentTimeMillis()));
		return userRepo.save(user);
	}
	
	@Override
	public List<User> listar(){
		return userRepo.findAll();
		
	}
	
    public boolean validarString(String cadena, String option) {
    	String regex="";
    	switch (option) {
		case "correo":
				regex = "^[a-z]{6}@[a-z]*.cl$";
			break;
		case "password":
				regex = "^[A-Z][a-z]*\\d{2}$";
			break;
		}
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }
    
    @Override
    public List<User> guardarTodos(List<User> users){
    	return userRepo.saveAll(users);
    }
    
    public User obtenerPorEmail(String email){
    	User user = userRepo.findByEmail(email);
    	if (user == null) {
			throw new RuntimeException("usuario no encontado service");
		}
//      	return userRepo.findByEmail(email);
    		return user;
    }

    
    public User actualizarToken(User us) {
    	String token = tokenService.getTokens(us.getEmail());
    	us.setToken(token);
    	us.setLast_login(new Date(System.currentTimeMillis()));
    	return userRepo.save(us);
    }
 
}
