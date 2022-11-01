package com.movie.entity;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Cast {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer castId;
	private Integer movieId;
	private String name;
	private String role;
	@ManyToMany
	private List<Cast>castSet=new ArrayList();
}
