package com.gestao.ferias.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gestao.ferias.domain.Usuario;
import com.gestao.ferias.service.FeriasService;
import com.gestao.ferias.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/ferias")
public class GestaoFerias {
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private FeriasService feriasService;
	
	//@Autowired
	//private PerfilService perfilService;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		Usuario lista = userService.find(id);
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Usuario> findAll() {
		List<Usuario> lista = userService.findAll();
		return lista;
	}
	
	@PreAuthorize("hasAnyRole('CLIENTE')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Usuario obj){
		obj = userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Usuario obj){
		obj.setId(id);
		userService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<List<Usuario>>  loginForm(@RequestBody Usuario obj) {
		//return "/loginForm";
		obj = userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();	
	}
	
//	@RequestMapping(value="/{email}", method=RequestMethod.GET)
//	public ResponseEntity<Usuario> find(@PathVariable String email) {
//		Usuario obj = userService.findByEmail(email);
//		return ResponseEntity.ok().body(obj);
//	}
//	
//	@RequestMapping(value="/teste", method=RequestMethod.GET)
//	public ResponseEntity<List<Perfil>> buscaPerfil(){
//		List<Perfil> lista = perfilService.findAll();
//		return ResponseEntity.ok().body(lista);
//	}
}
