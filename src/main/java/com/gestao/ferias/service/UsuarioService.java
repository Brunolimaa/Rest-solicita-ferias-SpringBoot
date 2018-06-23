package com.gestao.ferias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.ferias.domain.Usuario;
import com.gestao.ferias.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repoUser;
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repoUser.findById(id);
		return obj.orElse(null);
	}
	
	public Usuario insert(Usuario user) {
		user.setId(null);
		return repoUser.save(user);
	}
	
	public Usuario update(Usuario user) {
		return repoUser.save(user);
	}
	
	public void delete(Integer id) {
		repoUser.deleteById(id);
	}
	
	public List<Usuario> findAll() {
		return repoUser.findAll();
	}
	
	public Usuario findByEmail(String email) {
		return repoUser.findByEmail(email);
	}
}
