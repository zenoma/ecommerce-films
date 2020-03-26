package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Book;
import es.udc.paproject.backend.model.exceptions.BookAlreadyTakenException;
import es.udc.paproject.backend.model.exceptions.CodeAndCreditCardNotMatchException;
import es.udc.paproject.backend.model.exceptions.CreditCardNumberException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidSeatsException;
import es.udc.paproject.backend.model.exceptions.MovieSessionAlreadyStartedException;
import es.udc.paproject.backend.model.exceptions.NotEnoughtSeatsException;

public interface BookingService {
    Long bookTicket(int seats, Long creditCard, Long sessionId, Long userId)
	    throws InstanceNotFoundException, NotEnoughtSeatsException, CreditCardNumberException, InvalidSeatsException, MovieSessionAlreadyStartedException;

    void deliverTicket(Long creditCard, Long code) throws InstanceNotFoundException, CodeAndCreditCardNotMatchException, BookAlreadyTakenException, MovieSessionAlreadyStartedException;

    Block<Book> getBookRecord(Long userId) throws InstanceNotFoundException;

}
