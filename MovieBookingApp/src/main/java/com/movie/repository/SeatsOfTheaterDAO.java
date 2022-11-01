package com.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.SeatsOfTheater;

public interface SeatsOfTheaterDAO extends JpaRepository<SeatsOfTheater, Integer>{

}
