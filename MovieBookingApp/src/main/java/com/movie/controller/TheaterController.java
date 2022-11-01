package com.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping("/theater/{theaterName}")
	public ResponseEntity<List<String>>addTheater(@PathVariable String theaterName) throws TheatersException, MoviesExceptions{
		List<String> movies=theaterServices.findMovieRunningOnTheater(theaterName);
		return new ResponseEntity<List<String>>(movies,HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Theaters>>getAllTheater() throws TheatersException, MoviesExceptions{
		List<Theaters> list = theaterServices.getAllTheaters();
		return new ResponseEntity<List<Theaters>>(list,HttpStatus.OK);
	}
	@DeleteMapping("/{theaterId}")
	public ResponseEntity<Theaters>deleteTheaterById(@RequestParam Integer theaterId) throws TheatersException, MoviesExceptions{
		Theaters theater = theaterServices.removeTheater(theaterId);
		return new ResponseEntity<Theaters>(theater,HttpStatus.OK);
	}
	@PatchMapping("/theater/update")
	public ResponseEntity<Theaters>updateTheater(@RequestBody Theaters theater) throws TheatersException, MoviesExceptions{
		Theaters theaterSaved=theaterServices.updateTheater(theater);
		return new ResponseEntity<Theaters>(theaterSaved,HttpStatus.CREATED);
	}
	@GetMapping("/{theaterId}")
	public ResponseEntity<Theaters>getTheaterById(Integer theaterId) throws TheatersException, MoviesExceptions{
		Theaters theater = theaterServices.getTheater(theaterId);
		return new ResponseEntity<Theaters>(theater,HttpStatus.OK);
	}
	@GetMapping("/screen/{theaterId}")
	public ResponseEntity<List<TheaterScreen>>viewAllScreenByTheaterById(Integer theaterId) throws TheatersException{
		List<TheaterScreen> list = theaterServices.viewAllScreen(theaterId);
		return new ResponseEntity<List<TheaterScreen>>(list,HttpStatus.OK);
	}
}
