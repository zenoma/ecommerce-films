package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.CinemaDao;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PreviousDateException;
import es.udc.paproject.backend.model.services.MovieService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MovieServiceTest {
	
	@Autowired
	private MovieDao movieDao;
	
	@Autowired
	private CinemaDao cinemaDao;

	@Autowired
	private MovieService movieService;

	@Test
	public void testGetListing() throws InstanceNotFoundException, PreviousDateException {
		Set <Movie> moviesExpected = new HashSet<>();
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
	public void testGetListing1() throws InstanceNotFoundException, PreviousDateException {
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
	public void testGetListingInstanceNotFound() throws InstanceNotFoundException, PreviousDateException {
		assertThrows(InstanceNotFoundException.class,
				() -> movieService.getListing(-1L, LocalDateTime.of(2021, 03, 02, 0, 0)));
	}
	
	@Test
	public void testGetListingPreviousDate() throws InstanceNotFoundException, PreviousDateException {
		assertThrows(PreviousDateException.class,
				() -> movieService.getListing(-1L, LocalDateTime.of(2020, 03, 02, 0, 0)));
	}

}
