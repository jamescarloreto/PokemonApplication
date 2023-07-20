package com.ej.pokemonapplication.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//@TestPropertySource(locations = "classpath:application-test.properties")
public class PokemonTypeRepositoryTest {
	
	
	private PokemonTypeRepository PokemonTypeRepository;

	@Autowired
	public PokemonTypeRepositoryTest(PokemonTypeRepository pokemonTypeRepository) {
		PokemonTypeRepository = pokemonTypeRepository;
	}

}
