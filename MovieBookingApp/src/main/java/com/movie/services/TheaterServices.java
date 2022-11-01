package com.movie.services;

import java.util.List;

import com.movie.entity.TheaterScreen;
import com.movie.entity.Theaters;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;

public interface TheaterServices {
	public Theaters addTheater(Theaters theater)throws TheatersException, MoviesExceptions;
	public Theaters getTheater(Integer theaterId)throws TheatersException;
	public List<String> findMovieRunningOnTheater(String theaterName)throws TheatersException;
	public List<Theaters> getAllTheaters()throws TheatersException;
	public Theaters removeTheater(Integer theaterId)throws TheatersException;
	public Theaters updateTheater(Theaters theater)throws TheatersException;
	public List<TheaterScreen> viewAllScreen(Integer theaterId)throws TheatersException;
}
