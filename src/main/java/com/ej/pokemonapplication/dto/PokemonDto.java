package com.ej.pokemonapplication.dto;

import org.springframework.http.HttpStatus;

import com.ej.pokemonapplication.model.Pokemon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDto {
	
	Pokemon pokemon = new Pokemon();
	HttpStatus status;
}
