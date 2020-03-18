package es.udc.paproject.backend.test.model.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import es.udc.paproject.backend.model.entities.Book;
import es.udc.paproject.backend.model.entities.BookDao;
import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.CinemaDao;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.CityDao;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.entities.MovieSession;
import es.udc.paproject.backend.model.entities.MovieSessionDao;
import es.udc.paproject.backend.model.entities.Room;
import es.udc.paproject.backend.model.entities.RoomDao;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.entities.UserDao;
import es.udc.paproject.backend.model.exceptions.BookAlreadyTakenException;
import es.udc.paproject.backend.model.exceptions.CodeAndCreditCardNotMatchException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.NotEnoughSeatsException;
import es.udc.paproject.backend.model.exceptions.UnauthorizedRoleException;
import es.udc.paproject.backend.model.services.BookingService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BookingServiceTest {

	@Autowired
	private MovieDao movieDao;
	@Autowired
	private MovieSessionDao sessionDao;
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private CinemaDao cinemaDao;
	@Autowired
	private CityDao cityDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private BookingService bookingService;

	private Movie addMovie() {
		return movieDao.save(new Movie("Batman Begins", "This is a synopsis", 180));
	}
	
	private City addCity() {
		return cityDao.save(new City("A Coruña"));
	}
	
	private Cinema addCinema(City city) {
		return cinemaDao.save(new Cinema("Cinesa", city));
	}
	
	private Room addRoom(Cinema cine, int seats) {
		return roomDao.save(new Room("Sala 1", seats, cine));
	}
	
	private MovieSession addSession(Movie movie, Room room) {
		return sessionDao.save(new MovieSession(movie, room, 5.0, LocalDateTime.now()));
	}
	
	private User addUser() {
		return userDao.save(new User("TestUser", "testpass", "Test", "User", "testuser@gmail.com"));
	}
	
	@Test
	public void testFindMovie() {
		Movie movie = addMovie();
		Movie movieFound=bookingService.findMovie(movie.getId()).get();
		assertEquals(movie, movieFound);
	}
	
	@Test //(expected = InstanceNotFoundException.class)
	public void testNotFoundMovie() {
		try {
			bookingService.findMovie(99999L).get();
			assertTrue(false);
		}catch(Exception e) { //TODO AÑADIR EXCEPCIÓN CORRESPONDIENTE DE SQL
			assertTrue(true);
		}
	}
	
	@Test
	public void testBookTicketandBookRecord() throws InstanceNotFoundException, UnauthorizedRoleException, NotEnoughSeatsException {
		User user = addUser();
		City city = addCity();
		Cinema cine = addCinema(city);
		Room room = addRoom(cine, 80);
		Movie movie = addMovie();
		MovieSession session = addSession(movie, room);
		Long creditcard = (long) 93525315;
		bookingService.bookTicket(3, creditcard, session.getId(), user.getId());
		
		Set<Book> books = bookingService.getBookRecord(user.getId());
		Set<Book> expectedBooks = new HashSet<>();
		Book expectedBook = new Book(3, session, creditcard, user, LocalDateTime.now(), false);
		expectedBooks.add(expectedBook);
		assertEquals(expectedBooks, books);
	}
	
	//Este test espera notEnoughSeatsException
	@Test
	public void testBookTicketandBookRecord2() throws InstanceNotFoundException, UnauthorizedRoleException, NotEnoughSeatsException {
		User user = addUser();
		City city = addCity();
		Cinema cine = addCinema(city);
		Room room = addRoom(cine,2);
		Movie movie = addMovie();
		MovieSession session = addSession(movie, room);
		Long creditcard = (long) 93525315;
		bookingService.bookTicket(3, creditcard, session.getId(), user.getId());
		
		Set<Book> books = bookingService.getBookRecord(user.getId());
		Set<Book> expectedBooks = new HashSet<>();
		Book expectedBook = new Book(3, session, creditcard, user, LocalDateTime.now(), false);
		expectedBooks.add(expectedBook);
		assertEquals(expectedBooks, books);
	}
	
	@Test
	public void testDeliverTicket() throws InstanceNotFoundException, UnauthorizedRoleException, NotEnoughSeatsException, CodeAndCreditCardNotMatchException, BookAlreadyTakenException {
		User user = addUser();
		City city = addCity();
		Cinema cine = addCinema(city);
		Room room = addRoom(cine,80);
		Movie movie = addMovie();
		MovieSession session = addSession(movie, room);
		Long creditcard = (long) 93525315;
		Long code = bookingService.bookTicket(3, creditcard, session.getId(), user.getId());
		
		bookingService.deliverTicket(creditcard, code);
		
		Set<Book> books = bookingService.getBookRecord(user.getId());
		
		books.forEach((e) -> { 
			assertTrue(e.isWithdraw());
		});		
	}
	
	//Este test espera AlreadywithdrawException
	@Test
	public void testDeliverTicket2() throws InstanceNotFoundException, UnauthorizedRoleException, NotEnoughSeatsException, CodeAndCreditCardNotMatchException, BookAlreadyTakenException {
		User user = addUser();
		City city = addCity();
		Cinema cine = addCinema(city);
		Room room = addRoom(cine,80);
		Movie movie = addMovie();
		MovieSession session = addSession(movie, room);
		Long creditcard = (long) 93525315;
		Long code = bookingService.bookTicket(3, creditcard, session.getId(), user.getId());
		
		bookingService.deliverTicket(creditcard, code);
		bookingService.deliverTicket(creditcard, code);	
	}
}
