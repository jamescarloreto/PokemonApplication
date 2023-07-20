package com.ej.pokemonapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ej.pokemonapplication.model.PokemonType;

public interface PokemonTypeRepository extends JpaRepository<PokemonType, Integer> {
	
	PokemonType findByType (String type);
}
