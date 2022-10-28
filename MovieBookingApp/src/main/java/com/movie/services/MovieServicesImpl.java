package com.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.Movies;
import com.movie.exceptions.MoviesExceptions;
import com.movie.repository.MovieDAO;

@Service
public class MovieServicesImpl implements MovieServices{
	@Autowired
	private MovieDAO movieServices;
	@Override
	public Movies createMovie(Movies movie) throws MoviesExceptions {
		Movies moviebyName=movieServices.findByName(movie.getName());
		Movies moviebyYear=movieServices.findByYear( movie.getYear());
		if(moviebyName==null || moviebyYear==null) {
			Movies saved = movieServices.save(movie);
			return saved;
		}else {
			throw new MoviesExceptions("Movie Already Exists on the database");
		}
	}
	@Override
	public List<Movies> findAllMovies() throws MoviesExceptions {
		List<Movies>list=movieServices.findAll();
		if(list.isEmpty()) {
			throw new MoviesExceptions("There is no movie in the database");
		}else {
			return list;
		}
	}
	@Override
	public Movies findByName(String name) throws MoviesExceptions {
		Movies list=movieServices.findByName(name);
		if(list==null) {
			throw new MoviesExceptions("There is no movie in the database");
		}else {
			return list;
		}
	}
	@Override
	public Movies findByYear(String year) throws MoviesExceptions {
		Movies movie=movieServices.findByYear(year);
		if(movie == null) {
			throw new MoviesExceptions("There is no movie in the database");
		}else {
			return movie;
		}
	}
	
}
