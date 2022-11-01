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
import com.movie.entity.SeatsOfTheater;
import com.movie.entity.TheaterScreen;
import com.movie.entity.Theaters;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;
import com.movie.repository.MoviesDAO;
import com.movie.repository.SeatsOfTheaterDAO;
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
	@Autowired
	private SeatsOfTheaterDAO seatsOfTheaterDao;
	@Override
	public Theaters addScreen(TheaterScreen screen) throws TheatersException,MoviesExceptions {
		Optional<Theaters> theater = theaterDao.findById(screen.getTheaterId());
		if(theater.isPresent()) {
			Optional<Movies> movieOpt = movieDao.findById(screen.getMovieId());
			if(movieOpt.isPresent()){
				List<TheaterScreen>screenList=theater.get().getTheaterScreen();
				if(screenList.size()>=3) {
					throw new TheatersException("Theater already have 3 Screens");
				}
				screen.setTheaterName(theater.get().getTheaterName());
				SeatsOfTheater seats = new SeatsOfTheater();
				seats.setTotalSeats(screen.getAvailableColums()*screen.getAvailableRows());
				seats.setPriceForSeat(screen.getPriceForSeat());
				seats.setRemainingSeats(screen.getAvailableColums()*screen.getAvailableRows());
				seats.setBookedSeats(0);
				StringBuilder seatsOfTheater=new StringBuilder();
				seatsOfTheater.append("");
				seats.setSeatsOfTheater(seatsOfTheater);
				screen.setMovieName(movieOpt.get().getName());
				screenList.add(screen);
				screenDao.save(screen);
				seatsOfTheaterDao.save(seats);
				
				return theater.get();
			}else {
				throw new MoviesExceptions("Movie not found with id "+screen.getMovieId());
			}
		}else {
			throw new TheatersException("Theater not found with id "+screen.getTheaterId());
		}
		
	}

}
