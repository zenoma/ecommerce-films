package es.udc.paproject.backend.model.services;

import java.util.Optional;
import java.util.Set;

import es.udc.paproject.backend.model.entities.Book;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.exceptions.CodeAndCreditCardNotMatchException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NotEnoughSeatsException;
import es.udc.paproject.backend.model.exceptions.UnauthorizedRoleException;

public interface BookingService {

    Optional<Movie> findMovie(Long movieId);

    Long bookTicket(int seats, Long creditCard, Long sessionId, Long userId)
	    throws InstanceNotFoundException, UnauthorizedRoleException, NotEnoughSeatsException;

    void deliverTicket(Long creditCard, Long code) throws InstanceNotFoundException, CodeAndCreditCardNotMatchException;

    Set<Book> getBookRecord(Long userId) throws UnauthorizedRoleException, InstanceNotFoundException;

}
