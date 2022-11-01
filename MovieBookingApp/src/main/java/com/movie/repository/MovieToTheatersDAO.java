package com.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.MovieToTheaters;

public interface MovieToTheatersDAO extends JpaRepository<MovieToTheaters, Integer>{

}
