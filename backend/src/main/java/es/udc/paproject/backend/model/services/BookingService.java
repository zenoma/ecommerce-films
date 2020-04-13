package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Book;
import es.udc.paproject.backend.model.exceptions.BookAlreadyTakenException;
import es.udc.paproject.backend.model.exceptions.CodeAndCreditCardNotMatchException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.MovieSessionAlreadyStartedException;
import es.udc.paproject.backend.model.exceptions.NotEnoughtSeatsException;

public interface BookingService {
    Long bookTicket(int seats, String creditCard, Long sessionId, Long userId)
	    throws InstanceNotFoundException, NotEnoughtSeatsException, MovieSessionAlreadyStartedException;

    void deliverTicket(String creditCard, Long code) throws InstanceNotFoundException, CodeAndCreditCardNotMatchException, BookAlreadyTakenException, MovieSessionAlreadyStartedException;

    Block<Book> getBookRecord(Long userId, int page, int size) throws InstanceNotFoundException;

}
