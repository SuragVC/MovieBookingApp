package com.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.Theaters;

public interface TheaterDAO extends JpaRepository<Theaters, Integer>{
	public Theaters findByTheaterName(String theaterName);
}
