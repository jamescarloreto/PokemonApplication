package com.ej.pokemonapplication.repository;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.ej.pokemonapplication.model.Pokemon;
import com.ej.pokemonapplication.model.PokemonType;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//@TestPropertySource(locations = "classpath:application-test.properties")
public class PokemonRepositoryTest {

	private PokemonRepository pokemonRepository;

	@Autowired
	public PokemonRepositoryTest(PokemonRepository pokemonRepository) {

		this.pokemonRepository = pokemonRepository;
	}
	
	@Test
	public void PokemonRepository_CreatePokemon_ReturnCreatedPokemon() {
		
		// Arrange
		List<PokemonType> pokemonTypes = new ArrayList<PokemonType>();
		pokemonTypes.add(new PokemonType(0, "Electric"));
		pokemonTypes.add(new PokemonType(0, "Leaf"));
		Pokemon pokemonBuilder = Pokemon.builder()
				.pokemonName("Raichu")
				.pokemonTypes(pokemonTypes)
				.build(); 
		
		//Act
		Pokemon savedPokemon = pokemonRepository.save(pokemonBuilder);
		
		//
		Assertions.assertThat(savedPokemon).isNotNull();
	}
	
}
