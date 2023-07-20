package com.ej.pokemonapplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ej.pokemonapplication.dto.PokemonDto;
import com.ej.pokemonapplication.exceptions.PokemonAlreadyExistingException;
import com.ej.pokemonapplication.exceptions.PokemonNotFoundException;
import com.ej.pokemonapplication.model.Pokemon;
import com.ej.pokemonapplication.model.PokemonType;
import com.ej.pokemonapplication.repository.PokemonRepository;
import com.ej.pokemonapplication.repository.PokemonTypeRepository;
import com.ej.pokemonapplication.service.PokemonService;


@Service
public class PokemonServiceImpl implements PokemonService {
	private static final Logger logger = LoggerFactory.getLogger(PokemonServiceImpl.class);
	
	private PokemonRepository pokemonRepository;
	private PokemonTypeRepository pokemonTypeRepository;
	
	@Autowired
	public PokemonServiceImpl (PokemonRepository pokemonRepository,  PokemonTypeRepository pokemonTypeRepository) {
		this.pokemonRepository = pokemonRepository;
		this.pokemonTypeRepository = pokemonTypeRepository;
	}
	
	@Override
	public PokemonDto createPokemon(Pokemon pokemon) {
		
		PokemonDto pokemonDto = new PokemonDto();
		
		try {
			List<PokemonType> typeList = ifExistingType(pokemon.getPokemonTypes());
			
			Pokemon foundPokemon = pokemonRepository.findByPokemonName(pokemon.getPokemonName());
			
			if (foundPokemon != null) 
				throw new PokemonAlreadyExistingException("Pokemon :: " + foundPokemon.getPokemonName() + " already existing");

			pokemon.setPokemonTypes(typeList);
			Pokemon savedPokemon = pokemonRepository.save(pokemon);
			
			pokemonDto.setPokemon(savedPokemon);
			pokemonDto.setStatus(HttpStatus.CREATED);
		} catch (Exception e) {
			pokemonDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			logger.error(e.getMessage());
		}
		
		return pokemonDto;
	}
	
	public List<PokemonType> ifExistingType(List<PokemonType> types) {
		
		List<PokemonType> typeList = new ArrayList<PokemonType>();
		try {
			
			types.stream().forEach(type -> {
				PokemonType pokemonType = pokemonTypeRepository.findByType(type.getType());
				
				if (pokemonType == null ) {
					typeList.add(new PokemonType(type.getId(), type.getType()));
				} else {
					typeList.add(pokemonType);
				}
			});
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
		return typeList;
	}
	
	@Override
	public PokemonDto updatePokemon(Pokemon pokemon) {
		PokemonDto pokemonDto = new PokemonDto();
		
		try {
			Pokemon retrivedPokemon = pokemonRepository.findById(pokemon.getPokemonId()).orElseThrow(() -> new PokemonNotFoundException("Pokemon ID:: " + pokemon.getPokemonId() + " not found to update!"));
			
			retrivedPokemon.setPokemonName(pokemon.getPokemonName());
			retrivedPokemon.setPokemonTypes(ifExistingType(pokemon.getPokemonTypes()));
			
			retrivedPokemon = pokemonRepository.save(retrivedPokemon);
			
			pokemonDto.setPokemon(retrivedPokemon);
			pokemonDto.setStatus(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			pokemonDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			logger.error(e.getMessage());
		}
		
		return pokemonDto;
	}
	
	@Override
	public PokemonDto deletePokemon(int id) {
		PokemonDto pokemonDto = new PokemonDto();
		
		try {
			Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon ID:: " + id + " not found to delete!"));
			
			pokemonRepository.delete(pokemon);
			
			pokemonDto.setStatus(HttpStatus.OK);
		} catch(Exception e) {
			pokemonDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			logger.error(e.getMessage());
		}
		
		return pokemonDto;
	}

	@Override
	public Pokemon searchPokemon(int id) {
		
		return pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon ID:: " + id + " not found!"));
	}

	@Override
	public List<Pokemon> getAllPokemon() {
		
		return pokemonRepository.findAll();
	}
}
