package com.movie.entity;

import java.time.Year;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Movies {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer movieId;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Cast> CastInvolved;
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Theaters> theaters;
	private Categorys category;
	private String Duration;
	private Movie2Dor3D Dimension;
	private String year;
	 
}
