package com.ej.pokemonapplication.exceptions;

public class PokemonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6978468897426166199L;
	
	public PokemonNotFoundException(String message) {
		super(message);
	}
}
