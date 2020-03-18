package es.udc.paproject.backend.test.model.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Set;

import es.udc.paproject.backend.model.entities.Book;
import es.udc.paproject.backend.model.entities.BookDao;
import es.udc.paproject.backend.model.entities.MovieSession;
import es.udc.paproject.backend.model.entities.MovieSessionDao;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.entities.UserDao;
import es.udc.paproject.backend.model.exceptions.BookAlreadyTakenException;
import es.udc.paproject.backend.model.exceptions.CodeAndCreditCardNotMatchException;
import es.udc.paproject.backend.model.exceptions.CreditCardNumberException;
import es.udc.paproject.backend.model.exceptions.DuplicateInstanceException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.MovieAlreadyStartedException;
import es.udc.paproject.backend.model.exceptions.NotEnoughtSeatsException;
import es.udc.paproject.backend.model.services.BookingService;
import es.udc.paproject.backend.model.services.UserService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BookingServiceTest {

	@Autowired
	private MovieSessionDao sessionDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private BookingService bookingService;

	@Autowired
	private UserService userService;
	
	private User addUser() throws DuplicateInstanceException {
		User user = new User("TestUser", "testpass", "Test", "User", "testuser@gmail.com");
		userService.signUp(user);

		return userDao.findByUserName("TestUser").get();
	}
	
	@Test
	public void testBookTicketandBookRecord() throws DuplicateInstanceException, InstanceNotFoundException, NotEnoughtSeatsException, CreditCardNumberException{
		User user = addUser();
		MovieSession session = sessionDao.findById(1L).get();
		Long creditcard = 9352531593525315L;
		Long bookId=bookingService.bookTicket(3, creditcard, session.getId(), user.getId());
		
		Set<Book> books = bookingService.getBookRecord(user.getId());
		Book expectedBook = new Book(3, session, creditcard, user, LocalDateTime.now().withNano(0), false);
		expectedBook.setId(bookId);
		assertTrue(books.contains(expectedBook));
	}
	
	@Test
	public void testBookTicketSessionNotFound() throws DuplicateInstanceException{
		User user = addUser();
		Long creditcard = 9352531593525315L;
		assertThrows(InstanceNotFoundException.class, () -> bookingService.bookTicket(3, creditcard, 500L, user.getId()));
	}
	
	@Test
	public void testBookTicketUserNotFound(){
		MovieSession session = sessionDao.findById(1L).get();
		Long creditcard = 9352531593525315L;
		assertThrows(InstanceNotFoundException.class, () -> bookingService.bookTicket(3, creditcard, session.getId(), 500L));
	}
	
	@Test
	public void testBookTicketNotEnoughtSeats() throws DuplicateInstanceException{
		User user = addUser();
		MovieSession session = sessionDao.findById(1L).get();
		Long creditcard = 9352531593525315L;
		assertThrows(NotEnoughtSeatsException.class, () -> bookingService.bookTicket(500, creditcard, session.getId(), user.getId()));
	}
	
	@Test
	public void testBookTicketCreditCardNumber() throws DuplicateInstanceException{
		User user = addUser();
		MovieSession session = sessionDao.findById(1L).get();
		Long creditcard = 935253159L;
		assertThrows(CreditCardNumberException.class, () -> bookingService.bookTicket(3, creditcard, session.getId(), user.getId()));
	}
	
	@Test
	public void testBookRecordUserNotExist(){
		assertThrows(InstanceNotFoundException.class, () -> bookingService.getBookRecord(500L));
	}
	
	@Test
	public void testDeliverTicket() throws DuplicateInstanceException, InstanceNotFoundException, NotEnoughtSeatsException, CreditCardNumberException, CodeAndCreditCardNotMatchException, BookAlreadyTakenException, MovieAlreadyStartedException{
		User user = addUser();
		MovieSession session = sessionDao.findById(2L).get();
		Long creditcard = 9352531593525315L;
		Long bookId = bookingService.bookTicket(3, creditcard, session.getId(), user.getId());
		
		bookingService.deliverTicket(creditcard, bookId);
		
		Book newBook=bookDao.findById(bookId).get();
		
		assertTrue(newBook.isWithdraw());
	}
	
	@Test
	public void testDeliverTicketBookNotExist(){
		Long creditcard = 9352531593525315L;
		
		assertThrows(InstanceNotFoundException.class, () -> bookingService.deliverTicket(creditcard, 500L));
	}
	
	@Test
	public void testDeliverTicketCreditCardNoNotMatch() throws DuplicateInstanceException, InstanceNotFoundException, NotEnoughtSeatsException, CreditCardNumberException {
		User user = addUser();
		MovieSession session = sessionDao.findById(2L).get();
		Long creditcard = 9352531593525315L;
		Long bookId = bookingService.bookTicket(3, creditcard, session.getId(), user.getId());
		
		assertThrows(CodeAndCreditCardNotMatchException.class, () -> bookingService.deliverTicket(93525315L, bookId));
	}
	
	@Test
	public void testDeliverTicketMovieAlreadyStarted() throws DuplicateInstanceException, InstanceNotFoundException, NotEnoughtSeatsException, CreditCardNumberException{
		User user = addUser();
		MovieSession session = sessionDao.findById(1L).get();
		Long creditcard = 9352531593525315L;
		Long bookId = bookingService.bookTicket(3, creditcard, session.getId(), user.getId());
		
		assertThrows(MovieAlreadyStartedException.class, () -> bookingService.deliverTicket(creditcard, bookId));
	}
	
	@Test
	public void testDeliverTicketBookAlreadyTaken() throws DuplicateInstanceException, InstanceNotFoundException, NotEnoughtSeatsException, CreditCardNumberException, CodeAndCreditCardNotMatchException, BookAlreadyTakenException, MovieAlreadyStartedException {
		User user = addUser();
		MovieSession session = sessionDao.findById(2L).get();
		Long creditcard = 9352531593525315L;
		Long bookId = bookingService.bookTicket(3, creditcard, session.getId(), user.getId());
		
		bookingService.deliverTicket(creditcard, bookId);
		
		assertThrows(BookAlreadyTakenException.class, () -> bookingService.deliverTicket(creditcard, bookId));
	}
}
