package com.movie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ticketId;
	private Integer theaterId;
	private Integer movieId;
	private Integer ticketCount;
	private Integer rowNo;
	private Integer columnNo;
	private Double payment;
	@OneToOne
	@JsonIgnore
	private Theaters theater;
	@OneToOne
	@JsonIgnore
	private Movies movie;
}
