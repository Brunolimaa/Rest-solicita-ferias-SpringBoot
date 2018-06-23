package com.gestao.ferias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.ferias.domain.Ferias;
import com.gestao.ferias.repository.FeriasRepository;

@Service
public class FeriasService {
	
	@Autowired
	private FeriasRepository repo;
	
	public Ferias find(Integer id) {
		Optional<Ferias> ferias = repo.findById(id);
		return ferias.orElse(null);
	}
	
	public Ferias insert(Ferias ferias) {
		ferias.setId(null);
		return repo.save(ferias);
	}
	
	public Ferias update(Ferias ferias) {
		return repo.save(ferias);
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
	public List<Ferias> findAll() {
		return repo.findAll();
	}
}
