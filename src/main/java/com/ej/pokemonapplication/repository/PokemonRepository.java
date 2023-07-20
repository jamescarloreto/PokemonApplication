package com.ej.pokemonapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ej.pokemonapplication.model.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{
	
	Pokemon findByPokemonName(String pokemonName);
}
