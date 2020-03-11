package es.udc.paproject.backend.test.model.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.entities.MovieSession;
import es.udc.paproject.backend.model.entities.MovieSessionDao;
import es.udc.paproject.backend.model.entities.Room;
import es.udc.paproject.backend.model.services.BookingService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BookingServiceTest {

	// TODO Probar el Caso de Uso findMovie
	@Autowired
	private MovieDao movieDao;
	@Autowired
	private MovieSessionDao sessionDao;
	

	@Autowired
	private BookingService bookingService;

	private Movie addMovie() {
		return movieDao.save(new Movie("Batman Begins", "This is a synopsis", 180));
	}
	
	//private MovieSession addSession(Movie movie) {
		//return sessionDao.save(new MovieSession(movie, room, 5.0, LocalDateTime.now()));
	//}
	
	@Test
	public void testFindMovie() {
		Movie movie = addMovie();
		Movie movieFound=bookingService.findMovie(movie.getId()).get();
		assertEquals(movie, movieFound);
	}
	
	@Test
	public void testNotFoundMovie() {
		try {
			bookingService.findMovie(99999L).get();
			assertTrue(false);
		}catch(Exception e) { //TODO AÑADIR EXCEPCIÓN CORRESPONDIENTE DE SQL
			assertTrue(true);
		}
	}
	
	@Test
	public void testBookTicket() {
		
	}
}
