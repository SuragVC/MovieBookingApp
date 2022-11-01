package com.movie.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.MovieToTheaters;
import com.movie.entity.Movies;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;
import com.movie.repository.MoviesDAO;
import com.movie.repository.TheaterDAO;

@Service
public class MovieServicesImpl implements MovieServices{
	@Autowired
	private MoviesDAO mDao;
	@Autowired
	private TheaterDAO tDao;
	@Override
	public Movies createMovie(Movies movie) throws MoviesExceptions {
		List<Movies> moviebyName=mDao.findByName(movie.getName());
		for(Movies itr:moviebyName) {
			if(itr.getName().matches(movie.getName())) {
				if(itr.getYear().matches(movie.getYear())) {
					throw new MoviesExceptions("Movie already exists on the database");
				}
			}
		}
		return mDao.save(movie);
	}
	@Override
	public List<Movies> findAllMovies() throws MoviesExceptions {
		List<Movies>list=mDao.findAll();
		if(list.isEmpty()) {
			throw new MoviesExceptions("There is no movie in the database");
		}else {
			return list;
		}
	}
	@Override
	public List<Movies> findByName(String movieName) throws MoviesExceptions {
		List<Movies> list=mDao.findByName(movieName);
		if(list.isEmpty()) {
			throw new MoviesExceptions("There is no movie in the database name as "+movieName);
		}else {
			return list;
		}
	}
	@Override
	public List<Movies> findByYear(String year) throws MoviesExceptions {
		List<Movies> movie=mDao.findByYear(year);
		if(movie.isEmpty()) {
			throw new MoviesExceptions("There is no movie in the database released in the year "+year);
		}else {
			return movie;
		}
	}
	@Override
	public List<String> findTheaterByMovieName(String movieName) throws MoviesExceptions {
		List<Movies>movieList = mDao.findByName(movieName);
		List<String> ans=new ArrayList();
		if(movieList.isEmpty()) {
			throw new MoviesExceptions("There is no movie in the database with the name "+movieName);
		}else {
			for(Movies itr:movieList) {
				if(itr.getMovieToTheater().isEmpty()) {
					String movieString = "ID "+itr.getMovieId()+" "+movieName+" Release year "+itr.getYear()+" is not running on any theater";
					ans.add(movieString);
				}else {
					for(MovieToTheaters itr2:itr.getMovieToTheater()) {
						String movieString2 ="ID "+itr2.getMovieId()+" "+movieName+" is running on "+tDao.findById(itr2.getTheaterId()).get().getTheaterName()+" Release Year "+itr.getYear();
						ans.add(movieString2);
					}
				}
			}
			return ans;
		}
		
	}
	
}
