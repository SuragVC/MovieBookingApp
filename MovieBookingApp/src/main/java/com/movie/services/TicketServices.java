package com.movie.services;

import com.movie.entity.Ticket;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.ScreenException;
import com.movie.exceptions.TheatersException;
import com.movie.exceptions.TicketException;

public interface TicketServices {
	public Ticket bookAticket(Ticket ticket)throws TicketException,TheatersException,MoviesExceptions,ScreenException;
}
