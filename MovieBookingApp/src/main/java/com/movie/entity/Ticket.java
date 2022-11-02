package com.movie.entity;

import javax.persistence.Column;
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
	@Column(unique=true)
	private String ticketId;
	private Integer theaterId;
	private Integer movieId;
	private Integer ticketCount;
	private Integer rowNo;
	private Integer columnNo;
	private Double payment;
	private String seatAlloted;
	private Double returnAmount;
	@OneToOne
	private TheaterScreen screen;
}
