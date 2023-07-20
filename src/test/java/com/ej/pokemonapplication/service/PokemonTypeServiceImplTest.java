package com.ej.pokemonapplication.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ej.pokemonapplication.model.PokemonType;
import com.ej.pokemonapplication.repository.PokemonTypeRepository;
import com.ej.pokemonapplication.service.impl.PokemonTypeServiceImpl;

@ExtendWith(MockitoExtension.class)
class PokemonTypeServiceImplTest {

	@Mock
	private PokemonTypeRepository pokemonTypeRepository;
	
	@InjectMocks
	private PokemonTypeServiceImpl pokemonTypeService;
	
	List<PokemonType> pokemonTypes;
	PokemonType pokemonType;
	
	@BeforeEach
	public void beforeAllMethods() {
		pokemonTypes = new ArrayList<PokemonType>();
		pokemonType = new PokemonType(0, "Electric");
		pokemonTypes.add(pokemonType);
		pokemonTypes.add(new PokemonType(0, "Leaf"));
	}
	
	@Test
	void testCreatePokemonType() {
		when(pokemonTypeRepository.save(Mockito.any(PokemonType.class))).thenReturn(pokemonType);
		
		PokemonType returnedType = pokemonTypeService.createPokemonType(pokemonType);
		
		Assertions.assertThat(returnedType).isEqualTo(pokemonType);
	}

	@Test
	void testDeletePokemonType() {
		assertAll(() -> pokemonTypeService.deletePokemonType(1));
	}

}
