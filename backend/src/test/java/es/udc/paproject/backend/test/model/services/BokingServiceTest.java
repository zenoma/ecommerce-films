package es.udc.paproject.backend.test.model.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.services.BookingService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BokingServiceTest {

	// TODO Probar el Caso de Uso findMovie
	@Autowired
	private MovieDao movieDao;

	@Autowired
	private BookingService bookingService;

	private Movie addMovie() {
		return movieDao.save(new Movie("Batman Begins", "This is a synopsis", 180));
	}
	
	@Test
	public void testFindMovie() {
		Movie movie = addMovie();
		assertEquals(movie.getTitle(), bookingService.findMovie(movie.getId()).get().getTitle());
	}

}
