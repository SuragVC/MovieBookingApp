package com.movie.entity;
import java.util.ArrayList;
import java.util.HashSet;
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
public class Theaters {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer theaterId;
	@Column(unique=true)
	private String theaterName;
	private String address;
	private String city;
	private String pin;
	@OneToMany
	@JsonIgnore
	private List<MovieToTheaters>movieToTheater=new ArrayList<>();
	@OneToMany
	@JsonIgnore
	private List<Ticket>ticket=new ArrayList<>();
	@OneToMany
	@JsonIgnore
	private List<TheaterScreen>theaterScreen=new ArrayList<>();
	
}
