package com.movie.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.Cast;
import com.movie.entity.MovieToTheaters;
import com.movie.entity.Movies;
import com.movie.entity.TheaterScreen;
import com.movie.entity.Theaters;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;
import com.movie.repository.MoviesDAO;
import com.movie.repository.TheaterDAO;
import com.movie.repository.TheaterScreenDAO;
@Service
public class TheaterServiceImpl implements TheaterServices{
	@Autowired
	private TheaterDAO tDao;
	@Autowired
	private MoviesDAO mDao;
	
	@Override
	public Theaters addTheater(Theaters theater) throws TheatersException, MoviesExceptions {
		return tDao.save(theater);
	}
	@Override
	public List<String> findMovieRunningOnTheater(String theaterName) throws TheatersException {
		Theaters theater = tDao.findByTheaterName(theaterName);
		if(theater==null) {
			throw new TheatersException("There is no Theater in the name of "+theaterName);
		}
		List<MovieToTheaters>list = theater.getMovieToTheater();
		List<String> ans=new ArrayList<>();
		List<TheaterScreen>screenList = theater.getTheaterScreen();
		if(list.isEmpty()){
			throw new TheatersException("Theater is not running any movies now");
		}else {
			for(MovieToTheaters itr:list) {
				Optional<Movies> movie=mDao.findById(itr.getMovieId());
				ans.add("ID : "+movie.get().getMovieId()+"| Movie Name : "+movie.get().getName());
			}
			for(TheaterScreen itr:screenList) {
				ans.add( itr.getMovieName()+" Running now on "+itr.getScreenName()+" Screen");
			}
		}
		return ans;
	}
	@Override
	public List<Theaters> getAllTheaters() throws TheatersException {
		List<Theaters> list = tDao.findAll();
		if(list.isEmpty()) {
			throw new TheatersException("No Theaters added");
		}else {
			return list;
		}
	}
	@Override
	public Theaters removeTheater(Integer theaterId) throws TheatersException {
		Optional<Theaters> theater = tDao.findById(theaterId);
		if(theater.isPresent()) {
			tDao.delete(theater.get());
			return theater.get();
		}else {
			throw new TheatersException("Theater is not present with id as "+theaterId);
		}
		
	}
	@Override
	public Theaters updateTheater(Theaters theater) throws TheatersException {
		Optional<Theaters> theaterOpt = tDao.findById(theater.getTheaterId());
		if(theaterOpt.isPresent()) {
			Theaters theaterSaved = theaterOpt.get();
			theaterSaved.setTheaterName(theater.getTheaterName());
			theaterSaved.setAddress(theater.getAddress());
			theaterSaved.setCity(theater.getCity());
			theaterSaved.setPin(theater.getPin());
			return tDao.save(theaterSaved);
		}else {
			throw new TheatersException("Theater is not present with id as "+theater.getTheaterId());
		}
	}
	@Override
	public Theaters getTheater(Integer theaterId) throws TheatersException {
		Optional<Theaters> theater = tDao.findById(theaterId);
		if(theater.isPresent()) {
			return theater.get();
		}else {
			throw new TheatersException("Theater is not present with id as "+theaterId);
		}
	}
	@Override
	public List<TheaterScreen> viewAllScreen(Integer theaterId) throws TheatersException {
		Optional<Theaters> theater = tDao.findById(theaterId);
		if(theater.isPresent()) {
			return theater.get().getTheaterScreen();
		}else {
			throw new TheatersException("Theater is not present with id as "+theaterId);
		}
		
	}
	
}
