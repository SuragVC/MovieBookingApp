package com.movie.controller;

import java.io.FileNotFoundException;

import javax.persistence.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.movie.entity.PDFGenerator;
import com.movie.entity.Ticket;
import com.movie.exceptions.MoviesExceptions;
import com.movie.exceptions.ScreenException;
import com.movie.exceptions.TheatersException;
import com.movie.exceptions.TicketException;
import com.movie.services.TicketServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private TicketServices ticketServices;
	
	@PostMapping("/book")
	public ResponseEntity<Ticket>bookTicket(@RequestBody Ticket ticket) throws TicketException, TheatersException, MoviesExceptions, ScreenException, FileNotFoundException, DocumentException{
		Ticket bookedTicket = ticketServices.bookAticket(ticket);
		return new ResponseEntity<Ticket>(bookedTicket,HttpStatus.CREATED);
	}
	
	
}
