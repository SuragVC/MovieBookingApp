package com.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.entity.Theaters;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;
import com.movie.services.TheaterServices;

@RestController
@RequestMapping("/theaters")
public class TheaterController {
	
	@Autowired
	private TheaterServices theaterServices;
	@PostMapping("/theater")
	public ResponseEntity<Theaters>addTheater(@RequestBody Theaters theater) throws TheatersException, MoviesExceptions{
		Theaters theaterSaved=theaterServices.addTheater(theater);
		return new ResponseEntity<Theaters>(theaterSaved,HttpStatus.CREATED);
	}
}
