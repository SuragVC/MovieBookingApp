package com.movie.services;

import com.movie.entity.TheaterScreen;
import com.movie.entity.Theaters;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;

public interface TheaterScreenServices {
	public TheaterScreen addScreen(TheaterScreen screen)throws TheatersException,MoviesExceptions;
	public TheaterScreen removeScreenFromTheater(Integer ScreenId)throws TheatersException;
	public String changeMovieRunningOnScreen(Integer ScreenId,Integer movieId)throws TheatersException,MoviesExceptions;
}
