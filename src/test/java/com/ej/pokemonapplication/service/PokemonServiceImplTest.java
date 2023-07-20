package com.ej.pokemonapplication.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertAll;

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
import org.springframework.http.HttpStatus;

import com.ej.pokemonapplication.dto.PokemonDto;
import com.ej.pokemonapplication.model.Pokemon;
import com.ej.pokemonapplication.model.PokemonType;
import com.ej.pokemonapplication.repository.PokemonRepository;
import com.ej.pokemonapplication.repository.PokemonTypeRepository;
import com.ej.pokemonapplication.service.impl.PokemonServiceImpl;

@ExtendWith (MockitoExtension.class)
public class PokemonServiceImplTest {
	
	@Mock
	private PokemonRepository pokemonRepository;
	
	@Mock
	private PokemonTypeRepository pokemonTypeRepository;
	
	@InjectMocks
	private PokemonServiceImpl pokemonService;
	
	Pokemon pokemonBuilder;
	PokemonDto pokemonDto = new PokemonDto();
	Pokemon pokemonDtoBuilder;
	List<PokemonType> pokemonTypes;
	PokemonType pokemonType;
	List<Pokemon> pokemons;
	PokemonServiceImpl pokemonServiceTemp;
	
	@BeforeEach
	public void beforeAllMethods() {
		pokemonTypes = new ArrayList<PokemonType>();
		pokemons = new ArrayList<Pokemon>();
		
		pokemonTypes.add(new PokemonType(0, "Electric"));
		pokemonTypes.add(new PokemonType(0, "Leaf"));
		
		pokemonBuilder = Pokemon.builder()
				.pokemonId(1)
				.pokemonName("Raichu")
				.pokemonTypes(pokemonTypes)
				.build(); 
		
		pokemonDtoBuilder = Pokemon.builder()
				.pokemonName("Raichu")
				.pokemonTypes(pokemonTypes)
				.build();
		pokemonDto.setPokemon(pokemonDtoBuilder);
		pokemonDto.setStatus(HttpStatus.CREATED);
		
		pokemons.add(pokemonBuilder);
		pokemons.add(Pokemon.builder()
				.pokemonId(2)
				.pokemonName("Pikachu")
				.pokemonTypes(pokemonTypes).build());
	}

	@Test
	public void PokemonService_CreatePokemon_ReturnPokemon() {
		// Act
		when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemonDtoBuilder);
		
		PokemonDto pokemonDto = pokemonService.createPokemon(pokemonDtoBuilder);
		
		// Assert
		Assertions.assertThat(pokemonDto).isNotNull();
	}
	
	@Test
	public void PokemonService_UpdatePokemon_ReturnUpdatedPokemon() {
		
		when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemonBuilder));
		when(pokemonRepository.save(pokemonBuilder)).thenReturn(pokemonBuilder);
		
		PokemonDto newPokemonDto = pokemonService.updatePokemon(pokemonBuilder);
		
		Assertions.assertThat(newPokemonDto.getPokemon()).isNotNull();
	}
	
	@Test
	public void PokemonService_DeletePokemon_ReturnVoid() {
		
		when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemonBuilder));
		doNothing().when(pokemonRepository).delete(pokemonBuilder);
		
		assertAll(() -> pokemonService.deletePokemon(1));
	}
	
	@Test
	public void PokemonService_AllPokemon_ReturnAllPokemon() {
		when(pokemonRepository.findAll()).thenReturn(pokemons);
		
		List<Pokemon> pokemons = pokemonService.getAllPokemon();
		
		Assertions.assertThat(pokemons).isNotNull();
	}
	
	@Test
	public void PokemonService_FindPokemon_ReturnFoundPokemon() {
		
		when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemonBuilder));
		
		Pokemon foundPokemon = pokemonService.searchPokemon(1);
		
		Assertions.assertThat(pokemonBuilder).isEqualTo(foundPokemon);
	}
	
}
