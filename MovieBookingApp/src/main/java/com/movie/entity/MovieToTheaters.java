package com.movie.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class MovieToTheaters {
	@Id
	@GeneratedValue
	private Integer conectorId;
	private Integer movieId;
	private Integer theaterId;
	private LocalDateTime time;
}
