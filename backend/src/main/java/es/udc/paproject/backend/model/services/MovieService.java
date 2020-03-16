package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;
import java.util.Set;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PreviousDateException;

public interface MovieService {


	Set<Movie> getListing(Long cinemaId, LocalDateTime date) throws InstanceNotFoundException, PreviousDateException;
	
}
