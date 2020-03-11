package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Book;
import es.udc.paproject.backend.model.entities.BookDao;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.entities.MovieSession;
import es.udc.paproject.backend.model.entities.MovieSessionDao;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.exceptions.BookAlreadyTakenException;
import es.udc.paproject.backend.model.exceptions.CodeAndCreditCardNotMatchException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NotEnoughSeatsException;
import es.udc.paproject.backend.model.exceptions.UnauthorizedRoleException;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private PermissionChecker permissionChecker;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private MovieSessionDao sessionDao;

    @Autowired
    private BookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> findMovie(Long movieId) {
	return movieDao.findById(movieId);
    }

    @Override
    public Long bookTicket(int seats, Long creditCard, Long sessionId, Long userId)
	    throws InstanceNotFoundException, UnauthorizedRoleException, NotEnoughSeatsException {
	User user = permissionChecker.checkUser(userId);
	
	MovieSession session = checkSession(sessionId);
	if (session.getSeats() < seats) {
	    throw new NotEnoughSeatsException(sessionId, session.getSeats());
	}
	// Es necesario comprobar la tarjeta de crédito?

	session.setSeats(session.getSeats() - seats);

	sessionDao.save(session);

	Book book = new Book(seats, session, creditCard, user, LocalDateTime.now(), false);

	bookDao.save(book);

	return book.getId();
    }

    @Override
    public void deliverTicket(Long creditCard, Long bookId)
	    throws InstanceNotFoundException, CodeAndCreditCardNotMatchException, BookAlreadyTakenException {
    	Book book = checkBookByCode(bookId);
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
    public Set<Book> getBookRecord(Long userId) throws UnauthorizedRoleException, InstanceNotFoundException {
    	User user = permissionChecker.checkUser(userId);
    	return bookDao.findAllByUserId(userId);
    }

    public MovieSession checkSession(Long sessionId) throws InstanceNotFoundException {
    	Optional<MovieSession> session = sessionDao.findById(sessionId);
    	if (!session.isPresent()) {
    		throw new InstanceNotFoundException("project.entities.MovieSession", sessionId);
    	}
    	return session.get();
    }

    public Book checkBookByCode(Long bookId) throws InstanceNotFoundException {
    	Optional<Book> book = bookDao.findById(bookId);
    	if (!book.isPresent()) {
    		throw new InstanceNotFoundException("project.entities.Book", bookId);
    	}
    	return book.get();
    }

}
