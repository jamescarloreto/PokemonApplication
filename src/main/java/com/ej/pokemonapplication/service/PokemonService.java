package com.ej.pokemonapplication.service;

import java.util.List;

import com.ej.pokemonapplication.dto.PokemonDto;
import com.ej.pokemonapplication.model.Pokemon;

public interface PokemonService {

	PokemonDto createPokemon(Pokemon pokemon);

	PokemonDto deletePokemon(int id);

	PokemonDto updatePokemon(Pokemon pokemon);

	Pokemon searchPokemon(int id);

	List<Pokemon> getAllPokemon();

}
