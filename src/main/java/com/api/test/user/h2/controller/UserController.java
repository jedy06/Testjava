package com.api.test.user.h2.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.test.user.h2.entity.User;
import com.api.test.user.h2.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
    private TokenStore tokenStore;
    
    
    @Value("${security.jwt.client-id}")
    private String clientID;
    
	
	@PostMapping("/crear")
	public User registrar(@RequestBody User userR) throws Exception {
		User user = userService.registrar(userR);
		return user;
	}
	
	@GetMapping("/todos")
	public List<User> listar(){
		List<User> users = new ArrayList<>();
		users = userService.listar();
		users.forEach(us -> {
			us.setPassword(bcrypt.encode(us.getPassword()));
		});
		
		return userService.listar();
	}
	
	@GetMapping("/listar")
	public List<User> listarTodos(){
		return userService.listar();
	}
	
	@GetMapping(value = "/private")
	public String endPointPrivado() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getDetails().toString());
		return "Hola, tienes acceso a este recurso ya que " + "estás logeado con el user: " + auth.getName()
				+ " si no estubieras legeado, no podrías acceder aquí.";
	}
	
	
    public String getTokens(String email) {
    	
        Collection<OAuth2AccessToken>  listtok = tokenStore.findTokensByClientId(clientID);
        Collection<OAuth2AccessToken>  colttok = tokenStore.findTokensByClientIdAndUserName(clientID, email);
        listtok.forEach(lt ->{
        	System.out.println(lt.getValue());
        	System.out.println(lt.getTokenType());
        	
        });
        
        String tokenS = "";
        ArrayList<OAuth2AccessToken> listoken = new ArrayList<>(colttok);
        tokenS = listoken.get(0).toString();
        return tokenS;
            
    }


}
