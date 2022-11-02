package com.movie.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

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
	@Column(name="MovieName", length=40)
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Cast>castInvolved=new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<MovieToTheaters>movieToTheater=new ArrayList<>();
	private Categorys category;
	private String Duration;
	private Movie2Dor3D Dimension;
	@Column(name="Year")
	@Size(min = 4,max=4,message = "Year must be 4 character")
	private String year;
	private Double totalCollection;
}
