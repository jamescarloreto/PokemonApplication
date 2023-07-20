package com.ej.pokemonapplication.service;

import com.ej.pokemonapplication.model.PokemonType;

public interface PokemonTypeService {

	PokemonType createPokemonType(PokemonType pokemonType);

	String deletePokemonType(int id);

}
