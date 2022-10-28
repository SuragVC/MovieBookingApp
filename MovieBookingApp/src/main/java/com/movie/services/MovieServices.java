package com.movie.services;


import java.util.List;

import com.movie.entity.Movies;
import com.movie.exceptions.MoviesExceptions;

public interface MovieServices {
	public Movies createMovie(Movies movie)throws MoviesExceptions;
	public List<Movies> findAllMovies()throws MoviesExceptions;
	public Movies findByName(String name)throws MoviesExceptions;
	public Movies findByYear(String year)throws MoviesExceptions;
}
