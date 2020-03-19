package es.udc.paproject.backend.test.model.entities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

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
import es.udc.paproject.backend.model.services.BookingService;
import es.udc.paproject.backend.model.services.MovieService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MovieSessionTest {

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

	private Movie addMovie() {
		return movieDao.save(new Movie("Movie Test", "Synopsis", 180));
	}

	private City addCity() {
		return cityDao.save(new City("City Test"));
	}

	private Cinema addCinema(City city) {
		return cinemaDao.save(new Cinema("Cinema Test", city));
	}

	private Room addRoom(Cinema cine, int seats) {
		return roomDao.save(new Room("Sala Test", seats, cine));
	}

	private MovieSession addSession(Movie movie, Room room) {
		return sessionDao.save(new MovieSession(movie, room, 5.0, LocalDateTime.now()));
	}

	@Test
	public void testMovieSession() {
		City city = addCity();
		Cinema cine = addCinema(city);
		Room room = addRoom(cine, 80);
		Movie movie = addMovie();
		MovieSession session = addSession(movie, room);

		MovieSession sessionFound = sessionDao.findById(session.getId()).get();
		assertTrue(session.equals(sessionFound));
	}
}
