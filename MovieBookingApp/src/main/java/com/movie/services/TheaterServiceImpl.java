package com.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.Movies;
import com.movie.entity.Theaters;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;
import com.movie.repository.MovieDAO;
import com.movie.repository.TheaterDAO;
@Service
public class TheaterServiceImpl implements TheaterServices{
	@Autowired
	private TheaterDAO theaterDao;
	
	@Autowired
	private MovieDAO mDao;
	@Override
	public Theaters addTheater(Theaters theater) throws TheatersException, MoviesExceptions {
		Movies movie = mDao.findByName(theater.getMovieName());
		if(movie == null) {
			throw new MoviesExceptions("Movie not added to the database");
		}else {
			List<Theaters>TheaterList=movie.getTheaters();
			TheaterList.add(theater);
			return theaterDao.save(theater);
		}
		
	}

}
