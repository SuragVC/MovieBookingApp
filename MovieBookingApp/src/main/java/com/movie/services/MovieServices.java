package com.movie.services;


import java.util.List;

import com.movie.entity.Movies;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;

public interface MovieServices {
	public Movies createMovie(Movies movie)throws MoviesExceptions;
	public List<Movies> findAllMovies()throws MoviesExceptions;
	public List<Movies> findByName(String movieName)throws MoviesExceptions;
	public List<Movies> findByYear(String year)throws MoviesExceptions;
	public List<String> findTheaterByMovieName(String movieName)throws MoviesExceptions;
}
