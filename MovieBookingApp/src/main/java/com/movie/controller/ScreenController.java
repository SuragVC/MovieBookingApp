package com.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.entity.TheaterScreen;
import com.movie.entity.Theaters;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;
import com.movie.services.TheaterScreenServices;
import com.movie.services.TheaterServices;

@RestController
@RequestMapping("/screen")
public class ScreenController {
	@Autowired
	private TheaterScreenServices theaterScreen;
	
	@PostMapping("/new")
	public ResponseEntity<Theaters>addTheater(@RequestBody TheaterScreen screen) throws TheatersException, MoviesExceptions{
		Theaters screenSaved=theaterScreen.addScreen(screen);
		return new ResponseEntity<Theaters>(screenSaved,HttpStatus.CREATED);
	}
}
