package com.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.TheaterScreen;

public interface TheaterScreenDAO extends JpaRepository<TheaterScreen, Integer>{

}
