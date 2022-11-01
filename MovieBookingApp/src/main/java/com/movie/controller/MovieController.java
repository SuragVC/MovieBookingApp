package com.movie.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.entity.Movies;
import com.movie.exceptions.MoviesExceptions;
import com.movie.services.MovieServices;

@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	private MovieServices mServices;
	@PostMapping("/movie")
	public ResponseEntity<Movies>saveMovie(@RequestBody @Valid Movies movie) throws MoviesExceptions{
		Movies saved = mServices.createMovie(movie);
		return new ResponseEntity<Movies>(saved,HttpStatus.CREATED);
	}
	
	@GetMapping("/movies")
	public ResponseEntity <List<Movies>>getAllMovies() throws MoviesExceptions{
		List<Movies> saved=mServices.findAllMovies();
		return new ResponseEntity<List<Movies>>(saved,HttpStatus.CREATED);
	}
	@GetMapping("/movies/{name}")
	public ResponseEntity <List<Movies>>getMovieByName(@RequestParam String name) throws MoviesExceptions{
		List<Movies> list=mServices.findByName(name);
		return new ResponseEntity<List<Movies>>(list,HttpStatus.OK);
	}
	@PostMapping("/movies/{year}")
	public ResponseEntity <List<Movies>>getMovieByYear(@PathVariable String year) throws MoviesExceptions{
		List<Movies> list=mServices.findByYear(year);
		return new ResponseEntity<List<Movies>>(list,HttpStatus.OK);
	}
	@GetMapping("/theater/{movieName}")
	public ResponseEntity <List<String>>getTheaterByMovieName(@PathVariable String movieName) throws MoviesExceptions{
		List<String> list=mServices.findTheaterByMovieName(movieName);
		return new ResponseEntity<List<String>>(list,HttpStatus.OK);
	}
}
