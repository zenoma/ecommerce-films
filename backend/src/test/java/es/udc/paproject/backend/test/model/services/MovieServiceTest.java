package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.CinemaDao;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.CityDao;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.entities.MovieSession;
import es.udc.paproject.backend.model.entities.MovieSessionDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.MovieSessionAlreadyStartedException;
import es.udc.paproject.backend.model.exceptions.PreviousDateException;
import es.udc.paproject.backend.model.services.MovieService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MovieServiceTest {

	@Autowired
	private MovieDao movieDao;
	
	@Autowired
	private CityDao cityDao;
	
	@Autowired
	private MovieSessionDao movieSessionDao;
	
	@Autowired
	private CinemaDao cinemaDao;

	@Autowired
	private MovieService movieService;

	private boolean isTrueGetListingPlusDay(int day, Movie movie)
			throws InstanceNotFoundException, PreviousDateException {
		LocalDateTime plusOneDay = LocalDateTime.now().plusDays(day);
		Cinema cinema = cinemaDao.findByName("Ravachol").get();
		System.out.println("ME PRUEBO");
		Set<Movie> movies = movieService.getListing(cinema.getId(), plusOneDay);

		return movies.contains(movie);
	}

	@Test
	public void testGetListing() throws InstanceNotFoundException, PreviousDateException {
		Set<Movie> moviesExpected = new HashSet<>();
		Movie movie1 = movieDao.findByTitle("Batman Begins").get();
		Movie movie2 = movieDao.findByTitle("The Dark Knight").get();
		Movie movie3 = movieDao.findByTitle("The Dark Knight Rises").get();
		moviesExpected.add(movie1);
		moviesExpected.add(movie2);
		moviesExpected.add(movie3);
		Cinema cinema = cinemaDao.findByName("Marineda").get();
		Set<Movie> movies = movieService.getListing(cinema.getId(), LocalDateTime.of(2021, 03, 01, 0, 0));

		assertTrue(movies.containsAll(moviesExpected));
	}

	@Test
	public void testGetListingNow() throws InstanceNotFoundException, PreviousDateException {
		Set<Movie> moviesExpected = new HashSet<>();
		Movie movie1 = movieDao.findByTitle("Batman Begins").get();
		Movie movie2 = movieDao.findByTitle("The Dark Knight").get();
		moviesExpected.add(movie1);
		moviesExpected.add(movie2);
		Cinema cinema = cinemaDao.findByName("Marineda").get();
		Set<Movie> movies = movieService.getListing(cinema.getId(), LocalDateTime.now().withNano(0));

		assertTrue(movies.containsAll(moviesExpected));
	}

	@Test
	public void testGetListingFutureDays() throws InstanceNotFoundException, PreviousDateException {
		Movie movie = null;

		for (int i = 0; i < 6; i++) {
			if ((i + 1) % 3 == 1) {
				movie = movieDao.findByTitle("Batman Begins").get();
			} else if ((i + 1) % 3 == 2) {
				movie = movieDao.findByTitle("The Dark Knight").get();
			} else if ((i + 1) % 3 == 0) {
				movie = movieDao.findByTitle("The Dark Knight Rises").get();
			}
			assertTrue(isTrueGetListingPlusDay(i + 1, movie));
		}
	}

	@Test
	public void testGetListingOne() throws InstanceNotFoundException, PreviousDateException {
		Movie movie = movieDao.findByTitle("The Dark Knight Rises").get();
		Cinema cinema = cinemaDao.findByName("Marineda").get();
		Set<Movie> movies = movieService.getListing(cinema.getId(), LocalDateTime.of(2021, 03, 02, 0, 0));

		assertTrue(movies.contains(movie));
	}

	@Test
	public void testGetListingEmpty() throws InstanceNotFoundException, PreviousDateException {
		Cinema cinema = cinemaDao.findByName("Marineda").get();
		Set<Movie> movies = movieService.getListing(cinema.getId(), LocalDateTime.of(2021, 04, 02, 0, 0));
		assertTrue(movies.isEmpty());
	}

	@Test
	public void testGetListingInstanceNotFound() {
		assertThrows(InstanceNotFoundException.class,
				() -> movieService.getListing(-1L, LocalDateTime.of(2021, 03, 02, 0, 0)));
	}

	@Test
	public void testGetListingPreviousDate() {
		assertThrows(PreviousDateException.class,
				() -> movieService.getListing(-1L, LocalDateTime.of(2020, 03, 02, 0, 0)));
	}

	@Test
	public void testGetAllCities() {
		Set<City> cities = movieService.getAllCities();
		Set<City> expectedCities = new HashSet<>();
		City city = cityDao.findById(1L).get();
		expectedCities.add(city);
		city = cityDao.findById(2L).get();
		expectedCities.add(city);
		city = cityDao.findById(3L).get();
		expectedCities.add(city);
		assertTrue(cities.containsAll(expectedCities));
	}

	@Test
	public void testGetCinemasFromCity() throws InstanceNotFoundException {
		Set<Cinema> cinemas = movieService.getCinemasByCityId(1L);
		Cinema cinema1 = cinemaDao.findByName("Marineda").get();
		Cinema cinema2 = cinemaDao.findByName("Espacio Coruña").get();
		Set<Cinema> expectedCinemas = new HashSet<>();
		expectedCinemas.add(cinema1);
		expectedCinemas.add(cinema2);
		assertTrue(cinemas.containsAll(expectedCinemas));
	}
	
	@Test 
	public void testGetCinemasFromNotFoundCity(){
		assertThrows(InstanceNotFoundException.class, () -> movieService.getCinemasByCityId(-1L));
	}
	
	
	@Test
	public void testGetMovieById() throws InstanceNotFoundException {
		Movie movie = movieDao.findById(2L).get();
		assertEquals(movie, movieService.getMovieById(2L));
	}
	
	@Test 
	public void testGetNotFoudMovie(){
		assertThrows(InstanceNotFoundException.class, () -> movieService.getMovieById(-1L));
	}
	
	@Test
	public void testGetMovieSessionById() throws InstanceNotFoundException, MovieSessionAlreadyStartedException {
		MovieSession ms = movieSessionDao.findById(3L).get();
		assertEquals(ms, movieService.getMovieSessionById(3L));
	}
	
	@Test 
	public void testGetNotFoudMovieSession(){
		assertThrows(InstanceNotFoundException.class, () -> movieService.getMovieSessionById(-1L));
	}
	
	@Test 
	public void testGetAlreadyStartedMovieSession(){
		assertThrows(MovieSessionAlreadyStartedException.class, () -> movieService.getMovieSessionById(1L));
	}
		 

}
