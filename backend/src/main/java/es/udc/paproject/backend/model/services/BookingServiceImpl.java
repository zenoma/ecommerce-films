package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Book;
import es.udc.paproject.backend.model.entities.BookDao;
import es.udc.paproject.backend.model.entities.MovieSession;
import es.udc.paproject.backend.model.entities.MovieSessionDao;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.exceptions.BookAlreadyTakenException;
import es.udc.paproject.backend.model.exceptions.CodeAndCreditCardNotMatchException;
import es.udc.paproject.backend.model.exceptions.CreditCardNumberException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidSeatsException;
import es.udc.paproject.backend.model.exceptions.MovieSessionAlreadyStartedException;
import es.udc.paproject.backend.model.exceptions.NotEnoughtSeatsException;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private PermissionChecker permissionChecker;

    @Autowired
    private MovieSessionDao sessionDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public Long bookTicket(int seats, Long creditCard, Long sessionId, Long userId)
	    throws InstanceNotFoundException, NotEnoughtSeatsException, CreditCardNumberException, InvalidSeatsException, MovieSessionAlreadyStartedException {
		User user = permissionChecker.checkUser(userId);
		
		MovieSession session = checkSession(sessionId);
		if(session.getDate().isBefore(LocalDateTime.now())) {
    		throw new MovieSessionAlreadyStartedException(sessionId);
    	}
		int remainingTickets = session.getRoom().getCapacity() - session.getSeats();
		
		if(seats<=0 || seats>10) {
			throw new InvalidSeatsException(seats);
		}
			
		if (remainingTickets < seats) {
		    throw new NotEnoughtSeatsException(sessionId, session.getSeats());
		}
		
		creditCard=checkCreditCardNumber(creditCard);
	
		session.setSeats(session.getSeats() + seats);
	
		sessionDao.save(session);
	
		Book book = new Book(seats, session, creditCard, user, LocalDateTime.now().withNano(0), false);
	
		bookDao.save(book);
	
		return book.getId();
    }

    @Override
    public void deliverTicket(Long creditCard, Long bookId)
	    throws InstanceNotFoundException, CodeAndCreditCardNotMatchException, BookAlreadyTakenException, MovieSessionAlreadyStartedException {
    	Book book = checkBookByCode(bookId);
    	if(book.getMovieSession().getDate().isBefore(LocalDateTime.now())) {
    		throw new MovieSessionAlreadyStartedException(book.getMovieSession().getId());
    	}
		if (book.getCredit_card() != creditCard) {
			throw new CodeAndCreditCardNotMatchException(bookId, creditCard);
		}
		if (book.isWithdraw()) {
			throw new BookAlreadyTakenException(bookId);
		}
		book.setWithdraw(true);
		bookDao.save(book);
    }

    @Override
    public Block<Book> getBookRecord(Long userId, int page, int size) throws InstanceNotFoundException {
    	permissionChecker.checkUser(userId);
    	Slice<Book> books=bookDao.findByUserIdOrderByDateDesc(userId, PageRequest.of(page, size));
    	
    	return new Block<Book>(books.getContent(), books.hasNext());
    }

    private MovieSession checkSession(Long sessionId) throws InstanceNotFoundException {
    	Optional<MovieSession> session = sessionDao.findById(sessionId);
    	if (!session.isPresent()) {
    		throw new InstanceNotFoundException("project.entities.MovieSession", sessionId);
    	}
    	return session.get();
    }

    private Book checkBookByCode(Long bookId) throws InstanceNotFoundException {
    	Optional<Book> book = bookDao.findById(bookId);
    	if (!book.isPresent()) {
    		throw new InstanceNotFoundException("project.entities.Book", bookId);
    	}
    	return book.get();
    }

    private Long checkCreditCardNumber(Long creditCard) throws CreditCardNumberException {
    	if(creditCard.toString().length()!=16) {
    		throw new CreditCardNumberException(creditCard);
    	}
    	
    	return creditCard;
    }
}
