package com.movie.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.movie.entity.MovieToTheaters;
import com.movie.entity.Movies;
import com.movie.entity.Theaters;
import com.movie.exceptions.ConnectorServiceException;
import com.movie.repository.MoviesDAO;
import com.movie.repository.MovieToTheatersDAO;
import com.movie.repository.TheaterDAO;

@Service
public class MovieToTheaterServiceImpl implements MovieToTheaterService{
	@Autowired
	private MoviesDAO mDao;
	@Autowired
	private TheaterDAO tDao;
	@Autowired
	private MovieToTheatersDAO mtDao;
	@Override
	public String connectBetweenMovieAndTheater(Integer movieId, Integer theaterId) throws ConnectorServiceException {
		Optional<Movies> movie = mDao.findById(movieId);
		Optional<Theaters> theater = tDao.findById(theaterId);
		String ans="Internal Error";
		
		if(movie.isPresent()){
			if(theater.isPresent()) {
				List<MovieToTheaters> theatersList = movie.get().getMovieToTheater();
				List<MovieToTheaters> movielist = theater.get().getMovieToTheater();
				for(MovieToTheaters itr : theatersList) {
					if(itr.getMovieId()==movieId) {
						if(itr.getTheaterId()==theaterId) {
							 throw new ConnectorServiceException("Movie is already running on the same theater"); 
						}
					}
				}
				MovieToTheaters movieToTheaters = new MovieToTheaters();
				movieToTheaters.setMovieId(movieId);
				movieToTheaters.setTheaterId(theaterId);
				movieToTheaters.setTime(LocalDateTime.now());
				theatersList.add(movieToTheaters);
				movielist.add(movieToTheaters);
				mtDao.save(movieToTheaters);
				ans="";
				ans=ans+movie.get().getName()+" is running now on "+theater.get().getTheaterName();
				return ans;
			}else {
				throw new ConnectorServiceException("Theater not found with id "+theaterId);
			}
		}else {
			throw new ConnectorServiceException("Movie not found with id "+movieId);
		}
	}
	
}
