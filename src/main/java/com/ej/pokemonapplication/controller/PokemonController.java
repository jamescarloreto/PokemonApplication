package com.ej.pokemonapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ej.pokemonapplication.dto.PokemonDto;
import com.ej.pokemonapplication.model.Pokemon;
import com.ej.pokemonapplication.service.PokemonService;

@RestController
@RequestMapping ( "/pokemon" )
public class PokemonController {

	@Autowired
	private PokemonService pokemonService;
	
	@PostMapping ( "/create" )
	public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) {
		PokemonDto pokemonDto = pokemonService.createPokemon(pokemon);
		return new ResponseEntity<Pokemon>(pokemonDto.getPokemon(), pokemonDto.getStatus());
	}
	
	@DeleteMapping( "/delete/{id}" )
	public ResponseEntity<String> deletePokemon(@PathVariable int id) {
		pokemonService.deletePokemon(id);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping ( "/update" )
	public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon) {
		PokemonDto pokemonDto = pokemonService.updatePokemon(pokemon);
		return new ResponseEntity<Pokemon>(pokemonDto.getPokemon(), pokemonDto.getStatus());
	}
	
	@GetMapping ( "/find/{id}" )
	public ResponseEntity<Pokemon> searchPokemon(@PathVariable int id) {
		
		return new ResponseEntity<Pokemon>(pokemonService.searchPokemon(id), HttpStatus.OK);
	}
	
	@GetMapping ( "/getall" )
	public ResponseEntity<List<Pokemon>> getAll() {
		return new ResponseEntity<List<Pokemon>>(pokemonService.getAllPokemon(), HttpStatus.OK);
		
		
	}
}
