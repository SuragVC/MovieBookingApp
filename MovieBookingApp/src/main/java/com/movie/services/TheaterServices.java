package com.movie.services;

import com.movie.entity.Theaters;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;

public interface TheaterServices {
	public Theaters addTheater(Theaters theater)throws TheatersException, MoviesExceptions;
}
