package com.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.Movies;

public interface MovieDAO extends JpaRepository<Movies, Integer>{
	public Movies findByName(String name);
	public Movies findByYear(String year);
}
