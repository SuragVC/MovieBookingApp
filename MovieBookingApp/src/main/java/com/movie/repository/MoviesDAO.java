package com.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movie.entity.Movies;

public interface MoviesDAO extends JpaRepository<Movies, Integer>{
	public List<Movies> findByName(String name);
	public List<Movies> findByYear(String year);
}
