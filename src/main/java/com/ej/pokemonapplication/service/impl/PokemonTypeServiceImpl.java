package com.ej.pokemonapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ej.pokemonapplication.model.PokemonType;
import com.ej.pokemonapplication.repository.PokemonTypeRepository;
import com.ej.pokemonapplication.service.PokemonTypeService;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

	@Autowired
	private PokemonTypeRepository pokemonTypeRepository;
	
	@Override
	public PokemonType createPokemonType(PokemonType pokemonType) {
		
		return pokemonTypeRepository.save(pokemonType);
	}

	@Override
	public String deletePokemonType(int id) {

		pokemonTypeRepository.deleteById(id);
		
		return "Removed Successfully!";
	}


}
