package com.movie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.MovieToTheaters;
import com.movie.entity.Movies;
import com.movie.entity.PrimaryKeyGenerator;
import com.movie.entity.TheaterScreen;
import com.movie.entity.Theaters;
import com.movie.entity.Ticket;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.ScreenException;
import com.movie.exceptions.TheatersException;
import com.movie.exceptions.TicketException;
import com.movie.repository.MoviesDAO;
import com.movie.repository.TheaterDAO;
import com.movie.repository.TheaterScreenDAO;
import com.movie.repository.TicketDAO;

@Service
public class TicketServicesImpl implements TicketServices{
	@Autowired
	private TicketDAO ticketDao;
	@Autowired
	private MoviesDAO movieDao;
	@Autowired
	private TheaterDAO theaterDao;
	@Autowired
	private TheaterScreenDAO screenDao;
	@SuppressWarnings("unused")
	@Override
	public Ticket bookAticket(Ticket ticket) throws TicketException, TheatersException,MoviesExceptions,ScreenException{
		Optional<Theaters> theaterOpt= theaterDao.findById(ticket.getTheaterId());
		if(theaterOpt.isPresent()) {
				Theaters theater=theaterOpt.get();
				List<TheaterScreen>screenlist=theater.getTheaterScreen();
				if(screenlist.isEmpty()) {
					throw new TheatersException("!BOOKING CANCELLED! Reason : "+"Theater not running any movie now");
				}else {
					TheaterScreen screen=new TheaterScreen();
					for(TheaterScreen itr :screenlist) {
						if(itr.getMovieId()==ticket.getMovieId()) {
							screen=itr;
							break;
						}
					}
					if(screen != null) {
						Integer remainingSeats = screen.getRemainingSeats();
						if(remainingSeats<ticket.getTicketCount()) {
							throw new ScreenException("!BOOKING CANCELLED! Reason : "+"Screen only have "+screen.getRemainingSeats()+" seats left");
						}else {
							Double totalAmountPay=ticket.getPayment();
							Double totalAmountNeeded = screen.getPriceForSeat()*ticket.getTicketCount();
									
							if(totalAmountPay>=totalAmountNeeded) {
								if(ticket.getRowNo()*ticket.getColumnNo()>screen.getTotalSeats()) {
									throw new ScreenException("!BOOKING CANCELLED! Reason : "+"Theater only have "+screen.getTotalSeats());
								}else {							
									String reservedSeats=screen.getReservedSeats()+"";
									String bookedSeat="";
									int start=ticket.getColumnNo()*ticket.getRowNo();
									int j=0;
									for(int i=start;j<ticket.getTicketCount();i++) {
										String bookingSeat=""+i;
										if(reservedSeats.contains(bookingSeat)) {
											throw new ScreenException("!BOOKING CANCELLED! Reason : "+"Seat number "+i+" is already reserved");
										}else{
											bookingSeat=bookingSeat+i+" ";
											bookedSeat=bookedSeat+i+" ";
										}
										j++;
									}
									ticket.setTicketId(PrimaryKeyGenerator.getRandomNumberInStringFormat());
									ticket.setReturnAmount(totalAmountPay-totalAmountNeeded);
									ticket.setScreen(screen);
									ticket.setSeatAlloted(bookedSeat);
									screen.setReservedSeats(reservedSeats+bookedSeat);
									screen.setRemainingSeats(screen.getRemainingSeats()-ticket.getTicketCount());
									screen.setBookedSeats(screen.getBookedSeats()+ticket.getTicketCount());
									Optional<Movies> movie =movieDao.findById(ticket.getMovieId());
									movie.get().setTotalCollection(movie.get().getTotalCollection()+totalAmountNeeded);
									movieDao.save(movie.get());
									screenDao.save(screen);
									ticketDao.save(ticket);
									return ticket;
								}
							}else {
								throw new ScreenException("!BOOKING CANCELLED! Reason : "+"Total amount needed "+totalAmountNeeded+" you have paid only "+totalAmountPay);
							}
						}
						
					}else {
						throw new MoviesExceptions("!BOOKING CANCELLED! Reason : "+"Movie not running with the id : "+ticket.getMovieId()+" on the theater "+theater.getTheaterName());
					}
				}
		}else {
			throw new TheatersException("!BOOKING CANCELLED! Reason : "+"Theater not found ID : "+ticket.getTheaterId());
		}
	}

}
