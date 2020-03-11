package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
import es.udc.paproject.backend.model.services.MovieService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MovieServiceTest {

	@Autowired
	private MovieService movieService;

	@Test
	public void testgetListing() throws InstanceNotFoundException {
		List<Long> moviesId = new ArrayList<>();
		moviesId.add(3L);
		moviesId.add(4L);
		Set<Movie> movies = movieService.getListing(1L, LocalDateTime.of(2020, 03, 07, 18, 14));
		for (Movie movie : movies) {
			assertTrue(moviesId.contains(movie.getId()));
		}
	}
}
