package es.udc.paproject.backend.model.services;

import java.sql.Date;
import java.util.Set;

import es.udc.paproject.backend.model.entities.MovieSession;

public interface MovieService {


	Set<MovieSession> getListing(String cityName, String cinemaName, Date date);
	
}
