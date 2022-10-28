package com.movie.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private String theaterName;
	private String movieName;
	private String address;
	private String city;
	private String pin;
	private Integer AvailableRows;
	private Integer AvailableColums;
	private Integer CircleCategoryAmount;
	private Integer CrownCategoryAmount;
	private Integer DiamondCategoryAmount;
}
