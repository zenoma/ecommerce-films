package es.udc.paproject.backend.model.services;

import java.util.Optional;
import java.util.Set;

import es.udc.paproject.backend.model.entities.Book;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieSession;
import es.udc.paproject.backend.model.entities.User;

public interface BookingService {

	Optional<Movie> findMovie(Long movieId);
	
	Long bookTicket(int seats, Long creditCard, MovieSession sessionId, User userId);
	
	void deliverTicket(Long creditCard, Long bookId);
	
	Set<Book> getBookRecord(Long userId);
	
	
	
}
