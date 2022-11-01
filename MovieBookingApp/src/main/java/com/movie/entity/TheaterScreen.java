package com.movie.entity;

import java.util.List;

import javax.persistence.Column;
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
	@Column(unique = true)
	private Integer screenId;
	private Integer theaterId;
	private Integer availableRows;
	private Integer availableColums;
	private Double priceForSeat;
	private Integer movieId;
	private String screenName;
	@JsonIgnore
	private Integer totalSeats;
	@JsonIgnore
	private Integer bookedSeats;
	@JsonIgnore
	private Integer remainingSeats;
	@JsonIgnore
	@Column(columnDefinition="TEXT")
	private String reservedSeats;
	@JsonIgnore
	private String theaterName;
	@JsonIgnore
	private String movieName;
	@OneToOne
	@JsonIgnore
	private Theaters theater;
}
