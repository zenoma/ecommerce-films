package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.CinemaDao;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.CityDao;
import es.udc.paproject.backend.model.entities.ListingItem;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.entities.MovieSession;
import es.udc.paproject.backend.model.entities.MovieSessionDao;
import es.udc.paproject.backend.model.entities.Room;
import es.udc.paproject.backend.model.entities.RoomDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.MovieSessionAlreadyStartedException;
import es.udc.paproject.backend.model.exceptions.PlusWeekDateException;
import es.udc.paproject.backend.model.exceptions.PreviousDateException;
import es.udc.paproject.backend.model.services.MovieService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MovieServiceTest {
	private static final String[] MOVIE_TITLES= {"Avengers: Endgame", "Avatar", "Star Wars: El despertar de la fuerza", "Titanic", "Jurassic World", "Frozen", "Toy Story 4", "El Rey León", "Fast & Furious 7", "Los Increíbles 2"};
	private static final String[] CITY_NAMES= {"A Coruña", "Pontevedra", "Lugo"};
	private static final String[] CINEMA_NAMES= {"Cinesa", "Yelmo", "Galicine"};
	private static final int NUM_MAX_ROOMS_BY_CINEMA=5;
	private static final int HOUR_BETWEEN_SESSION=3;
	private static final int DAYS_WITH_MOVIES=8;
	
	private List<City> cities=new ArrayList<>();
	private List<Movie> movies=new ArrayList<>();
	private List<Cinema> cinemas=new ArrayList<>();
	private List<Room> rooms=new ArrayList<>();
	private List<MovieSession> movieSessions=new ArrayList<>();

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

	@Autowired
	private MovieService movieService;
	
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
			Room room=new Room("Sala "+i, i+5*i, cinema);
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
					MovieSession movieSession=new MovieSession(movie, room, 8.20, LocalDateTime.of(today.getYear(), today.getMonthValue(), i, j, minutes));
					movieSession=movieSessionDao.save(movieSession);
					movieSessions.add(movieSession);
				}
			}
		}
		return movieSessions;
	}
	
	@BeforeEach
	public void initData() {
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
	}
	
	@Test
	public void testGetListingNow() throws InstanceNotFoundException, PreviousDateException, PlusWeekDateException {
		Long cinemaId=cinemas.get(0).getId();
		LocalDate date=LocalDate.now();
		
		Set<ListingItem> listing=movieService.getListing(cinemaId, date);

		for(ListingItem item:listing) {
			for(MovieSession movieSession:item.getMovieSessions()) {
				LocalDate dateReceived=LocalDate.of(movieSession.getDate().getYear(), movieSession.getDate().getMonth(), movieSession.getDate().getDayOfMonth());
				assertEquals(date, dateReceived);
				assertEquals(cinemaId, movieSession.getRoom().getCinema().getId());
			}
		}
	}
	
	@Test
	public void testGetListingFutureDays() throws InstanceNotFoundException, PreviousDateException, PlusWeekDateException {
		Long cinemaId=cinemas.get(0).getId();
		LocalDate date=LocalDate.now();
		
		for(int i=1; i<=6; i++) {
			Set<ListingItem> listing=movieService.getListing(cinemaId, date.plusDays(i));
			for(ListingItem item:listing) {
				for(MovieSession movieSession:item.getMovieSessions()) {
					LocalDate dateReceived=LocalDate.of(movieSession.getDate().getYear(), movieSession.getDate().getMonth(), movieSession.getDate().getDayOfMonth());
					assertEquals(date.plusDays(i), dateReceived);
					assertEquals(cinemaId, movieSession.getRoom().getCinema().getId());
				}
			}
		}
	}
	
	@Test
	public void testGetListingCinemaNotFound() throws InstanceNotFoundException, PreviousDateException, PlusWeekDateException {
		Long cinemaId=-1L;
		LocalDate date=LocalDate.now();
		
		assertThrows(InstanceNotFoundException.class,
				() -> movieService.getListing(cinemaId, date));
	}
	
	@Test
	public void testGetListingPreviousDate() throws InstanceNotFoundException, PreviousDateException, PlusWeekDateException {
		Long cinemaId=cinemas.get(0).getId();
		LocalDate date=LocalDate.now().minusDays(1);
		
		assertThrows(PreviousDateException.class,
				() -> movieService.getListing(cinemaId, date));
	}
	
	@Test
	public void testGetListingPlusWeekDate() throws InstanceNotFoundException, PreviousDateException, PlusWeekDateException {
		Long cinemaId=cinemas.get(0).getId();
		LocalDate date=LocalDate.now().plusDays(7);
		
		assertThrows(PlusWeekDateException.class,
				() -> movieService.getListing(cinemaId, date));
	}
	
	@Test
	public void testGetAllCities() throws InstanceNotFoundException, PreviousDateException, PlusWeekDateException {
		Set<City> cities = movieService.getAllCities();
		for(City city:cities) {
			assertTrue(this.cities.contains(city));
		}
	}

	@Test
	public void testGetCinemasFromCity() throws InstanceNotFoundException {
		Long cityId=cities.get(0).getId();
		Set<Cinema> cinemas = movieService.getCinemasByCityId(cityId);
		for(Cinema cinema:cinemas) {
			assertTrue(this.cinemas.contains(cinema));
			assertEquals(cinema.getCity().getId(), cityId);
		}
	}

	@Test
	public void testGetCinemasFromNotFoundCity() {
		Long cityId=-1L;
		assertThrows(InstanceNotFoundException.class, () -> movieService.getCinemasByCityId(cityId));
	}
	
	@Test
	public void testGetMovieById() throws InstanceNotFoundException {
		Long movieId=movies.get(0).getId();
		Movie movie = movieService.getMovieById(movieId);

		assertEquals(movies.get(0), movie);
	}
	
	@Test
	public void testGetNotFoundMovie() {
		Long movieId=-1L;
		assertThrows(InstanceNotFoundException.class, () -> movieService.getMovieById(movieId));
	}
	
	@Test
	public void testGetMovieSessionById() throws InstanceNotFoundException, MovieSessionAlreadyStartedException, PreviousDateException, PlusWeekDateException {
		MovieSession movieSessionExpected=new MovieSession();
		
		for(MovieSession movieSession:movieSessions) {
			if(movieSession.getDate().isAfter(LocalDateTime.now())) {
				movieSessionExpected=movieSession;
				break;
			}
		}
		
		MovieSession movieSession = movieService.getMovieSessionById(movieSessionExpected.getId());

		assertEquals(movieSessionExpected, movieSession);
	}

	@Test
	public void testGetNotFoundMovieSession() {
		Long movieSessionId=-1L;
		assertThrows(InstanceNotFoundException.class, () -> movieService.getMovieSessionById(movieSessionId));
	}
	
	@Test
	public void testGetMovieSessionAlreadyStarted() throws InstanceNotFoundException, MovieSessionAlreadyStartedException {
		MovieSession movieSessionExpected=new MovieSession();
		
		for(MovieSession movieSession:movieSessions) {
			if(movieSession.getDate().isBefore(LocalDateTime.now())) {
				movieSessionExpected=movieSession;
				break;
			}
		}
		
		final Long movieSessionId=movieSessionExpected.getId();
		
		assertThrows(MovieSessionAlreadyStartedException.class, () -> 
			movieService.getMovieSessionById(movieSessionId)
		);
	}

}
