package com.movie.services;

import com.movie.entity.TheaterScreen;
import com.movie.entity.Theaters;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;

public interface TheaterScreenServices {
	public Theaters addScreen(TheaterScreen screen)throws TheatersException,MoviesExceptions;
}
