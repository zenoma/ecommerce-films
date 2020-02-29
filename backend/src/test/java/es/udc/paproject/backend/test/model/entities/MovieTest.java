package es.udc.paproject.backend.test.model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.services.BookingService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MovieTest {
		@Autowired
		private MovieDao movieDao;

		@Autowired
		private BookingService bookingService;

		private Movie addMovie() {
			return movieDao.save(new Movie("Batman Begins", "This is a synopsis", 180));
		}
		
		@Test
		public void testEqualsMovie() {
			Movie movie = addMovie();
			Movie movieFound=bookingService.findMovie(movie.getId()).get();
			Movie movieError=new Movie("Batman Begins", "This is a synopsis", 180);
			
			movieError.setId(9999999L);
			assertNotEquals(movieError, movieFound);
			movieError.setId(movie.getId());
			
			movieError.setTitle("Error");
			assertNotEquals(movieError, movieFound);
			movieError.setTitle(movie.getTitle());
			
			movieError.setSynopsis("Error");
			assertNotEquals(movieError, movieFound);
			movieError.setSynopsis(movie.getSynopsis());
			
			movieError.setDuration(10000);
			assertNotEquals(movieError, movieFound);
			movieError.setDuration(movie.getDuration());
			
			assertEquals(movieError, movieFound);
		}
}
