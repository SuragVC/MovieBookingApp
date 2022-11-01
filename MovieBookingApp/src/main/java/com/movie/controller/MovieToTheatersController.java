package com.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.exceptions.ConnectorServiceException;
import com.movie.services.MovieToTheaterService;

@RestController
@RequestMapping("conn")
public class MovieToTheatersController {
	@Autowired
	private MovieToTheaterService movieToTheatersSer;
	@PostMapping("/{movieId}/{theaterId}")
	public ResponseEntity<String>connectBetweenMovieAndTheater(@PathVariable Integer movieId,@PathVariable Integer theaterId) throws ConnectorServiceException{
		String answer = movieToTheatersSer.connectBetweenMovieAndTheater(movieId, theaterId);
		return new ResponseEntity<String>(answer,HttpStatus.CREATED);
	}
}
