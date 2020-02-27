package es.udc.paproject.backend.model.services;

import java.util.Optional;

import es.udc.paproject.backend.model.entities.Movie;

public interface BookingService {

	Optional<Movie> findMovie(Long movieId);
}
