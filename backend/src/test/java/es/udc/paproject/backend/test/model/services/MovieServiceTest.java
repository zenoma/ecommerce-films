package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PreviousDateException;
import es.udc.paproject.backend.model.services.MovieService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MovieServiceTest {

	@Autowired
	private MovieService movieService;

	@Test
	public void testGetListing() throws InstanceNotFoundException, PreviousDateException {
		List<Long> moviesId = new ArrayList<>();
		moviesId.add(1L);
		moviesId.add(2L);
		moviesId.add(3L);
		Set<Movie> movies = movieService.getListing(1L, LocalDateTime.of(2021, 03, 01, 0, 0));
		for (Movie movie : movies) {
			assertTrue(moviesId.contains(movie.getId()));
		}
	}

	@Test
	public void testGetListing1() throws InstanceNotFoundException, PreviousDateException {
		List<Long> moviesId = new ArrayList<>();
		moviesId.add(3L);
		Set<Movie> movies = movieService.getListing(1L, LocalDateTime.of(2021, 03, 02, 0, 0));
		for (Movie movie : movies) {
			assertTrue(moviesId.contains(movie.getId()));
		}
	}

	@Test
	public void testGetListingEmpty() throws InstanceNotFoundException, PreviousDateException {
		Set<Movie> movies = movieService.getListing(1L, LocalDateTime.of(2021, 04, 02, 0, 0));
		assertTrue(movies.isEmpty());
	}

	@Test
	public void testGetListingInstanceNotFound() throws InstanceNotFoundException, PreviousDateException {
		assertThrows(InstanceNotFoundException.class,
				() -> movieService.getListing(0L, LocalDateTime.of(2021, 03, 02, 0, 0)));
	}
	
	@Test
	public void testGetListingPreviousDate() throws InstanceNotFoundException, PreviousDateException {
		assertThrows(PreviousDateException.class,
				() -> movieService.getListing(0L, LocalDateTime.of(2020, 03, 02, 0, 0)));
	}

}
