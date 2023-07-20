package com.ej.pokemonapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PokemonType {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private int id;
	
	@Column(unique = true)
	private String type;
}
