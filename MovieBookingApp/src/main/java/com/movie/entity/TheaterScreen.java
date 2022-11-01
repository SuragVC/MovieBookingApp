package com.movie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class TheaterScreen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer screenId;
	private Integer theaterId;
	private Integer availableRows;
	private Integer availableColums;
	private Double priceForSeat;
	private Integer movieId;
	private String screenName;
	@JsonIgnore
	private String theaterName;
	@JsonIgnore
	private String movieName;
	@OneToOne
	@JsonIgnore
	private SeatsOfTheater seats;
}
