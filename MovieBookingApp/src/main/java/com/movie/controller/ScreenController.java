package com.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<TheaterScreen>addTheaterScreen(@RequestBody TheaterScreen screen) throws TheatersException, MoviesExceptions{
		TheaterScreen screenSaved=theaterScreen.addScreen(screen);
		return new ResponseEntity<TheaterScreen>(screenSaved,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{screenId}")
	public ResponseEntity<TheaterScreen>deleteScreenById(@RequestParam Integer screenId) throws TheatersException, MoviesExceptions{
		TheaterScreen theaterScreenRemoved = theaterScreen.removeScreenFromTheater(screenId);
		return new ResponseEntity<TheaterScreen>(theaterScreenRemoved,HttpStatus.OK);
	}
	@PatchMapping("/{screenId}/{movieId}")
	public ResponseEntity<String>updateTheater(@PathVariable Integer screenId,@PathVariable Integer movieId) throws TheatersException, MoviesExceptions{
		String ans=theaterScreen.changeMovieRunningOnScreen(screenId, movieId);
		return new ResponseEntity<String>(ans,HttpStatus.CREATED);
	}
}
