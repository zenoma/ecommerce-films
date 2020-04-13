package es.udc.paproject.backend.test.model.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import es.udc.paproject.backend.model.exceptions.DuplicateInstanceException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.MovieSessionAlreadyStartedException;
import es.udc.paproject.backend.model.exceptions.NotEnoughtSeatsException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.BookingService;
import es.udc.paproject.backend.model.services.UserService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BookingServiceTest {
	private static final String[] MOVIE_TITLES= {"Avengers: Endgame"};
	private static final String[] CITY_NAMES= {"A Coruña"};
	private static final String[] CINEMA_NAMES= {"Cinesa"};
	private static final int NUM_MAX_ROOMS_BY_CINEMA=1;
	private static final int HOUR_BETWEEN_SESSION=10;
	private static final int DAYS_WITH_MOVIES=3;
	
	private List<City> cities=new ArrayList<>();
	private List<Movie> movies=new ArrayList<>();
	private List<Cinema> cinemas=new ArrayList<>();
	private List<Room> rooms=new ArrayList<>();
	private List<MovieSession> movieSessions=new ArrayList<>();
	private User user;

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

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private CityDao cityDao;

	@Autowired
	private MovieSessionDao movieSessionDao;

	@Autowired
	private CinemaDao cinemaDao;
	
	@Autowired
	private RoomDao roomDao;
	
	private List<City> createCities() {
		List<City> cities=new ArrayList<>();
		
		for(String name:CITY_NAMES) {
			City city=new City(name);
			city=cityDao.save(city);
			cities.add(city);
		}

		return cities;
	}
	
	private List<Movie> createMovies(){
		List<Movie> movies = new ArrayList<>();
		for(String title:MOVIE_TITLES) {
			Movie movie=new Movie(title, "Prueba", 100);
			movie=movieDao.save(movie);
			movies.add(movie);
		}
		return movies;
	}
	
	private List<Cinema> createCinemas(City city) {
		List<Cinema> cinemas = new ArrayList<>();
		for(String name:CINEMA_NAMES) {
			Cinema cinema=new Cinema(name, city);
			cinema=cinemaDao.save(cinema);
			cinemas.add(cinema);
		}
		return cinemas;
	}
	
	private List<Room> createRooms(Cinema cinema) {
		List<Room> rooms=new ArrayList<>();
		for(int i=0; i<NUM_MAX_ROOMS_BY_CINEMA; i++) {
			Room room=new Room("Sala "+i, 5, cinema);
			room=roomDao.save(room);
			rooms.add(room);
		}
		return rooms;
	}
	
	private List<MovieSession> createMovieSessions(Room room, List<Movie> movies) {
		List<MovieSession> movieSessions=new ArrayList<>();
		LocalDate today=LocalDate.now();
		for(Movie movie:movies) {
			for(int i=today.getDayOfMonth()-1;i<=today.getDayOfMonth()+(DAYS_WITH_MOVIES-1);i++) {
				if(i==28 && today.getMonthValue()==2) break;
				if(i<0) i+=30;
				if(i>30) i-=30;
				for(int j=0;j<24; j+=HOUR_BETWEEN_SESSION) {
					int minutes=30;
					if(j%2!=0) minutes=55;
					MovieSession movieSession=new MovieSession(movie, room, BigDecimal.valueOf(8.20), LocalDateTime.of(today.getYear(), today.getMonthValue(), i, j, minutes));
					movieSession=movieSessionDao.save(movieSession);
					movieSessions.add(movieSession);
				}
			}
		}
		return movieSessions;
	}

	private User createUser() throws DuplicateInstanceException {
		User user = new User("TestUser", "testpass", "Test", "User", "testuser@gmail.com");
		userService.signUp(user);

		return userDao.findByUserName("TestUser").get();
	}
	
	@BeforeEach
	public void initData() throws DuplicateInstanceException {
		cities=createCities();
		movies=createMovies();
		cinemas=new ArrayList<>();
		rooms=new ArrayList<>();
		movieSessions=new ArrayList<>();
		for(City city:cities) {
			for(Cinema cinema:createCinemas(city)) {
				for(Room room:createRooms(cinema)) {
					movieSessions.addAll(createMovieSessions(room, movies));
					rooms.add(room);
				}
				cinemas.add(cinema);
			}
		}
		user=createUser();
	}
	
	@Test
	public void testBookTicketandBookRecord() throws InstanceNotFoundException, NotEnoughtSeatsException, MovieSessionAlreadyStartedException {
		
		MovieSession movieSession=new MovieSession();
		
		for(MovieSession mv:movieSessions) {
			if(mv.getDate().isAfter(LocalDateTime.now())) {
				movieSession=mv;
				break;
			}
		}
		
		String creditcard = "9352531593525315";
		Long bookId = bookingService.bookTicket(3, creditcard, movieSession.getId(), user.getId());

		Block<Book> books = bookingService.getBookRecord(user.getId(), 0, 10);
		Book expectedBook = new Book(3, movieSession, creditcard, user, LocalDateTime.now().withNano(0), false);
		expectedBook.setId(bookId);
		assertEquals(books.getItems().get(0).getId(), expectedBook.getId());
	}
	
	@Test
	public void testBookTicketSessionNotFound() {
		String creditcard = "9352531593525315";
		assertThrows(InstanceNotFoundException.class,
				() -> bookingService.bookTicket(3, creditcard, -1L, user.getId()));
	}
	
	@Test
	public void testBookTicketMovieSessionAlreadyStarted() {
		MovieSession movieSession=new MovieSession();
		String creditcard = "9352531593525315";
		
		for(MovieSession mv:movieSessions) {
			if(mv.getDate().isBefore(LocalDateTime.now())) {
				movieSession=mv;
				break;
			}
		}
		
		final Long movieSessionId=movieSession.getId();
		
		assertThrows(MovieSessionAlreadyStartedException.class,
				() -> bookingService.bookTicket(3, creditcard, movieSessionId, user.getId()));
	}
	
	@Test
	public void testBookTicketUserNotFound() {
		MovieSession movieSession=new MovieSession();
		String creditcard = "9352531593525315";
		
		for(MovieSession mv:movieSessions) {
			if(mv.getDate().isAfter(LocalDateTime.now())) {
				movieSession=mv;
				break;
			}
		}

		final Long movieSessionId=movieSession.getId();
		
		assertThrows(InstanceNotFoundException.class,
				() -> bookingService.bookTicket(3, creditcard, movieSessionId, -1L));
	}
	
	@Test
	public void testBookTicketNotEnoughtSeats() throws DuplicateInstanceException {
		MovieSession movieSession=new MovieSession();
		String creditcard = "9352531593525315";
		
		for(MovieSession mv:movieSessions) {
			if(mv.getDate().isAfter(LocalDateTime.now())) {
				movieSession=mv;
				break;
			}
		}

		final Long movieSessionId=movieSession.getId();
		assertThrows(NotEnoughtSeatsException.class,
				() -> bookingService.bookTicket(10, creditcard, movieSessionId, user.getId()));
	}
	
	@Test
	public void testBookRecordUserNotExist() {
		assertThrows(InstanceNotFoundException.class, () -> bookingService.getBookRecord(-1L, 1, 10));
	}
	
	@Test
	public void testDeliverTicket() throws DuplicateInstanceException, InstanceNotFoundException, NotEnoughtSeatsException, CodeAndCreditCardNotMatchException,
			BookAlreadyTakenException, MovieSessionAlreadyStartedException {
		
		MovieSession movieSession=new MovieSession();
		String creditcard = "9352531593525315";
		
		for(MovieSession mv:movieSessions) {
			if(mv.getDate().isAfter(LocalDateTime.now()) && mv.getRoom().getCapacity()>3) {
				movieSession=mv;
				break;
			}
		}
		
		Long bookId = bookingService.bookTicket(3, creditcard, movieSession.getId(), user.getId());

		bookingService.deliverTicket(creditcard, bookId);

		Book newBook = bookDao.findById(bookId).get();

		assertTrue(newBook.isWithdraw());
	}
	
	@Test
	public void testDeliverTicketBookNotExist() {
		String creditcard = "9352531593525315";

		assertThrows(InstanceNotFoundException.class, () -> bookingService.deliverTicket(creditcard, -1L));
	}
	
	@Test
	public void testDeliverTicketCreditCardNoNotMatch() throws DuplicateInstanceException, InstanceNotFoundException, NotEnoughtSeatsException, MovieSessionAlreadyStartedException {
		MovieSession movieSession=new MovieSession();
		String creditcard = "9352531593525315";
		
		for(MovieSession mv:movieSessions) {
			if(mv.getDate().isAfter(LocalDateTime.now())) {
				movieSession=mv;
				break;
			}
		}
		
		Long bookId = bookingService.bookTicket(3, creditcard, movieSession.getId(), user.getId());

		assertThrows(CodeAndCreditCardNotMatchException.class, () -> bookingService.deliverTicket("93525315", bookId));
	}
	
	@Test
	public void testDeliverTicketMovieAlreadyStarted() throws DuplicateInstanceException, InstanceNotFoundException, NotEnoughtSeatsException, MovieSessionAlreadyStartedException {
		MovieSession movieSession=new MovieSession();
		String creditcard = "9352531593525315";
		
		for(MovieSession mv:movieSessions) {
			if(mv.getDate().isAfter(LocalDateTime.now())) {
				movieSession=mv;
				break;
			}
		}
		
		Long bookId = bookingService.bookTicket(3, creditcard, movieSession.getId(), user.getId());	
		movieSession.setDate(movieSession.getDate().minusDays(1));
		sessionDao.save(movieSession);	

		assertThrows(MovieSessionAlreadyStartedException.class, () -> bookingService.deliverTicket(creditcard, bookId));
	}

	@Test
	public void testDeliverTicketBookAlreadyTaken() throws DuplicateInstanceException, InstanceNotFoundException, NotEnoughtSeatsException,
			MovieSessionAlreadyStartedException, CodeAndCreditCardNotMatchException, BookAlreadyTakenException {

		MovieSession movieSession=new MovieSession();
		String creditcard = "9352531593525315";
		
		for(MovieSession mv:movieSessions) {
			if(mv.getDate().isAfter(LocalDateTime.now())) {
				movieSession=mv;
				break;
			}
		}
		
		Long bookId = bookingService.bookTicket(3, creditcard, movieSession.getId(), user.getId());

		bookingService.deliverTicket(creditcard, bookId);

		assertThrows(BookAlreadyTakenException.class, () -> bookingService.deliverTicket(creditcard, bookId));
	}
}
