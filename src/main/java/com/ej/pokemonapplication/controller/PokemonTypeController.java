package com.ej.pokemonapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.ej.pokemonapplication.model.PokemonType;
import com.ej.pokemonapplication.service.PokemonTypeService;

@RestController
@RequestMapping("/pokemontype")
public class PokemonTypeController {
	
	@Autowired
	private PokemonTypeService pokemonTypeService;
	
	@PostMapping("/create")
	public ResponseEntity<PokemonType> createPokemonType(@RequestBody PokemonType pokemonType) {
		return new ResponseEntity<PokemonType>(pokemonTypeService.createPokemonType(pokemonType), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePokemonType(@PathVariable int id) {
		return new ResponseEntity<String>(pokemonTypeService.deletePokemonType(id), HttpStatus.OK);
	}
}
