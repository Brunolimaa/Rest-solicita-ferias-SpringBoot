package com.gestao.ferias;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gestao.ferias.domain.Ferias;
import com.gestao.ferias.domain.Usuario;
import com.gestao.ferias.enums.Perfil;
import com.gestao.ferias.service.FeriasService;
import com.gestao.ferias.service.UsuarioService;

@SpringBootApplication
public class GestaoFeriasSolicitarApplication implements CommandLineRunner {

	@Autowired
	private FeriasService feriasService;
	
	@Autowired
	private UsuarioService userService;
	
	//@Autowired
	//private PerfilService perfilService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestaoFeriasSolicitarApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		String senha = new BCryptPasswordEncoder().encode("123");

		Usuario user = new Usuario();
		user.setNome("Lima");
		user.setEmail("conecthousee@gmail.com");
		user.setSenha(senha);
		user.addPerfil(Perfil.ADMIN);
		userService.insert(user);
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 Date dataInicio = new Date(format.parse("13/01/2015").getTime());
		 Date dataFim = new Date(format.parse("22/12/2018").getTime());
		 
		Ferias ferias = new Ferias(null, dataInicio, dataFim, true, 1);
		Ferias ferias1 = new Ferias(null, dataInicio, dataFim, false, 1);
		
		List<Ferias> listaFerias = new ArrayList<>();
		
		listaFerias.add(ferias);
		listaFerias.add(ferias1);

		//Set<Integer> perfis = new LinkedHashSet<Integer>();
		//perfis.add(1);
		
		//user.setPerfis(perfis);

		feriasService.insert(ferias);
		feriasService.insert(ferias1);
		
		//-----------------------------------------------------
		
		String senha1 = new BCryptPasswordEncoder().encode("123");

		Usuario user1 = new Usuario();
		user1.setNome("Bruno");
		user1.setEmail("br_lima@live.com");
		user1.setSenha(senha);
		user1.addPerfil(Perfil.CLIENTE);
		userService.insert(user1);
		 SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		 Date dataInicio1 = new Date(format.parse("13/01/2015").getTime());
		 Date dataFim1 = new Date(format.parse("22/12/2018").getTime());
		 
		Ferias ferias2 = new Ferias(null, dataInicio, dataFim, true, 1);
		
		List<Ferias> listaFerias1 = new ArrayList<>();
		
		listaFerias.add(ferias2);

		//Set<Integer> perfis = new LinkedHashSet<Integer>();
		//perfis.add(1);
		
		//user.setPerfis(perfis);

		feriasService.insert(ferias2);

	}
}
