package com.movie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.MovieToTheaters;
import com.movie.entity.Movies;
import com.movie.entity.Theaters;
import com.movie.entity.Ticket;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.TheatersException;
import com.movie.exceptions.TicketException;
import com.movie.repository.MoviesDAO;
import com.movie.repository.TheaterDAO;
import com.movie.repository.TicketDAO;

@Service
public class TicketServicesImpl implements TicketServices{
	@Autowired
	private TicketDAO ticketDao;
	@Autowired
	private MoviesDAO movieDao;
	@Autowired
	private TheaterDAO theaterDao;
	
	@Override
	public Ticket bookAticket(Ticket ticket) throws TicketException, TheatersException,MoviesExceptions {
		Optional<Theaters> theaterOpt= theaterDao.findById(ticket.getTheaterId());
		if(theaterOpt.isPresent()) {
				Theaters theater=theaterOpt.get();
				List<MovieToTheaters>list=theater.getMovieToTheater();
				if(list.isEmpty()) {
					throw new TheatersException("Theater not running any movie now");
				}else {
					boolean movieRunning=false;
					for(MovieToTheaters itr :list) {
						if(itr.getMovieId()==ticket.getMovieId()) {
							movieRunning=true;
							break;
						}
					}
					if(movieRunning) {
						return null;
						
						
						
						
					}else {
						throw new MoviesExceptions("Movie not running with the id : "+ticket.getMovieId()+" on the theater "+theater.getTheaterName());
					}
				}
		}else {
			throw new TheatersException("Theater not found ID : "+ticket.getTheaterId());
		}
	}

}
