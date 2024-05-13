package com.api.test.user.h2.service;

import java.util.List;

import com.api.test.user.h2.entity.User;

public interface UserService {
	
	public User obtenerPorEmail(String email);
	
	public User registrar(User user) throws Exception;
	
	public User actualizar(User user);
	
	public List<User> listar();

	public List<User> guardarTodos(List<User> users);
	
	public User actualizarToken(User us);
	
}
