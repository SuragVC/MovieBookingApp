package com.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.Ticket;

public interface TicketDAO extends JpaRepository<Ticket, Integer>{

}
