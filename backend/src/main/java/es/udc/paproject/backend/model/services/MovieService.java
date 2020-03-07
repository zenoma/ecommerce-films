package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;
import java.util.Set;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;

public interface MovieService {


	Set<Movie> getListing(Long cinemaId, LocalDateTime date) throws InstanceNotFoundException;
	
}
