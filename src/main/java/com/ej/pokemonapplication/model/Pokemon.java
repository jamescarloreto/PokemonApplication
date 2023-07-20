package com.ej.pokemonapplication.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pokemon {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private int pokemonId;
	
	@Column(unique=true)
	private String pokemonName;
	
	@ManyToMany (fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "pokemon_pokemontypes", 
			joinColumns = @JoinColumn(
				name = "pokemonId",
				referencedColumnName = "pokemonId"),
			inverseJoinColumns = @JoinColumn (
				name = "typeId",
				referencedColumnName = "id"))
	private List<PokemonType> pokemonTypes;
}
