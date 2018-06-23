package com.gestao.ferias.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazonaws.services.applicationautoscaling.model.ObjectNotFoundException;
import com.gestao.ferias.domain.Usuario;
import com.gestao.ferias.repository.UsuarioRepository;

@Service
public class AuthService {

	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Usuario usuario = usuarioRepo.findByEmail(email);
		if (usuario == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		usuario.setSenha(pe.encode(newPass));
		
		usuarioRepo.save(usuario);
		//emailService.sendNewPasswordEmail(usuario, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
