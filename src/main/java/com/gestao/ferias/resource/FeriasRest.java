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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gestao.ferias.domain.Ferias;
import com.gestao.ferias.domain.Usuario;
import com.gestao.ferias.service.FeriasService;
import com.gestao.ferias.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/feriasrest")
public class FeriasRest {
	
	//@Autowired
	//private UsuarioService userService;
	
	@Autowired
	private FeriasService feriasService;
	
	//@Autowired
	//private PerfilService perfilService;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		Ferias lista = feriasService.find(id);
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Ferias>> findAll() {
		List<Ferias> lista = feriasService.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	//@PreAuthorize("hasAnyRole('CLIENTE')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Ferias obj){
		obj = feriasService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Ferias obj){
		obj.setId(id);
		feriasService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		feriasService.delete(id);
		return ResponseEntity.noContent().build();
	}
	

	
//	@RequestMapping(value="/teste", method=RequestMethod.GET)
//	public ResponseEntity<List<Perfil>> buscaPerfil(){
//		List<Perfil> lista = perfilService.findAll();
//		return ResponseEntity.ok().body(lista);
//	}
}
