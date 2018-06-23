package com.gestao.ferias.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.gestao.ferias.domain.Usuario;
import com.gestao.ferias.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
