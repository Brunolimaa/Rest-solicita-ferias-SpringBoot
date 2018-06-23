package com.gestao.ferias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestao.ferias.domain.Ferias;

@Repository
public interface FeriasRepository extends JpaRepository<Ferias, Integer> {
	
}

