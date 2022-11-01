package com.movie.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.GeneratedValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.Movies;
import com.movie.entity.PrimaryKeyGenerator;
import com.movie.entity.TheaterScreen;
import com.movie.entity.Theaters;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;
import com.movie.repository.MoviesDAO;
import com.movie.repository.TheaterDAO;
import com.movie.repository.TheaterScreenDAO;

@Service
public class TheaterScreenServicesImpl implements TheaterScreenServices {
	@Autowired
	private TheaterDAO theaterDao;
	@Autowired
	private TheaterScreenDAO screenDao;
	@Autowired
	private MoviesDAO movieDao;
	
	@Override
	public TheaterScreen addScreen(TheaterScreen screen) throws TheatersException,MoviesExceptions {
		Optional<Theaters> theater = theaterDao.findById(screen.getTheaterId());
		if(theater.isPresent()) {
			Optional<Movies> movieOpt = movieDao.findById(screen.getMovieId());
			if(movieOpt.isPresent()){
				List<TheaterScreen>screenList=theater.get().getTheaterScreen();
				if(screenList.size()>=3) {
					throw new TheatersException("Theater already have 3 Screens");
				}
				if(screen.getAvailableColums()*screen.getAvailableRows()>250) {
					throw new TheatersException("Screen Must be maximum capacity of 250 seats");
				}
				screen.setScreenId(PrimaryKeyGenerator.getRandomNumber());
				screen.setTheaterName(theater.get().getTheaterName());
				screen.setBookedSeats(0);
				screen.setTotalSeats(screen.getAvailableColums()*screen.getAvailableRows());
				screen.setRemainingSeats(screen.getAvailableColums()*screen.getAvailableRows());
				screen.setMovieName(movieOpt.get().getName());
				screen.setTheater(theater.get());
				screenList.add(screen);
				screenDao.save(screen);				
				return screen;
			}else {
				throw new MoviesExceptions("Movie not found with id "+screen.getMovieId());
			}
		}else {
			throw new TheatersException("Theater not found with id "+screen.getTheaterId());
		}
		
	}

	@Override
	public TheaterScreen removeScreenFromTheater(Integer screenId) throws TheatersException {
		Optional<TheaterScreen> screenOpt = screenDao.findById(screenId);
		if(screenOpt.isPresent()){
			Optional<Theaters> theater = theaterDao.findById(screenOpt.get().getScreenId());
			List<TheaterScreen>screenList = theater.get().getTheaterScreen();
			TheaterScreen removable=new TheaterScreen();
			for(TheaterScreen itr:screenList) {
				if(itr.getScreenId()==screenId) {
					removable=itr;
					break;
				}
			}
			screenList.remove(removable);
			screenDao.delete(removable);
			return removable;
		}
		else {
			throw new TheatersException("No Screen found with id "+screenId);
		}
		
	}

	@Override
	public String changeMovieRunningOnScreen(Integer screenId, Integer movieId)
			throws TheatersException, MoviesExceptions {
		Optional<TheaterScreen> screenOpt = screenDao.findById(screenId);
		if(screenOpt.isPresent()){
			Optional<Movies> movieOpt = movieDao.findById(movieId);
			if(movieOpt.isPresent()) {
				Optional<Theaters> theater = theaterDao.findById(screenOpt.get().getScreenId());
				List<TheaterScreen>screenList = theater.get().getTheaterScreen();
				TheaterScreen updatable=new TheaterScreen();
				for(TheaterScreen itr:screenList) {
					if(itr.getScreenId()==screenId) {
						updatable=itr;
						break;
					}
				}
				String movieUpdating = updatable.getMovieName();
				updatable.setMovieName(movieOpt.get().getName());
				screenList.add(updatable);
				screenDao.save(updatable);
				String ans ="Movie "+movieUpdating+" changed to "+updatable.getMovieName()+" from "+updatable.getTheaterName()+" theater screen "+updatable.getScreenName();
				return ans;
			}else {
				throw new MoviesExceptions("Movie not found with id "+movieId);
			}
			
		}
		else {
			throw new TheatersException("No Screen found with id "+screenId);
		}
	
	}

}
