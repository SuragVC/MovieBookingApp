package com.movie.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class SeatsOfTheater {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer seatsOfTheaterId;
	private Integer totalSeats;
	private Integer bookedSeats;
	private Integer remainingSeats;
	private Double priceForSeat;
	private StringBuilder seatsOfTheater;
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seatsOfTheaterId")
	private TheaterScreen theaterScreen;
}
