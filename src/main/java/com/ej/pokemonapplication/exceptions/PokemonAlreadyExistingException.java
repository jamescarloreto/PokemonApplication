package com.ej.pokemonapplication.exceptions;

public class PokemonAlreadyExistingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PokemonAlreadyExistingException(String message) {
		super(message);
	}
}
