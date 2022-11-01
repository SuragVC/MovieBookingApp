package com.movie.services;

import com.movie.exceptions.ConnectorServiceException;

public interface MovieToTheaterService {
	public String connectBetweenMovieAndTheater(Integer movieId,Integer theaterId)throws ConnectorServiceException;
}
