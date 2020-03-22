package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import es.udc.paproject.backend.model.entities.Room;
import es.udc.paproject.backend.model.entities.RoomDao;

@Service
@Transactional(readOnly = true)
public class MovieServiceImpl implements MovieService {

	@Autowired
	private CityDao cityDao;

	@Autowired
	private CinemaDao cinemaDao;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private MovieSessionDao movieSessionDao;

	@Override
	public Set<Movie> getListing(Long cinemaId, LocalDateTime date)
			throws InstanceNotFoundException, PreviousDateException {

		if (date.isBefore(LocalDateTime.now().withNano(0))) {
			throw new PreviousDateException();
		}

		Optional<Cinema> cinema = cinemaDao.findById(cinemaId);
		if (!cinema.isPresent()) {
			throw new InstanceNotFoundException("project.entities.cinema", cinemaId);
		}

		Set<Room> rooms = roomDao.findAllByCinemaId(cinema.get().getId());

		Set<MovieSession> movieSessions = new HashSet<>();

		for (Room room : rooms) {
			movieSessions.addAll(movieSessionDao.findAllByRoomIdAndDate(room.getId(), date));
		}

		Set<Movie> movies = new HashSet<>();

		Set<MovieSession> actualMovieSessions = new HashSet<>();
		Movie actualMovie = new Movie();

		for (MovieSession movieSession : movieSessions) {

			actualMovie = movieSession.getMovie();
			if (actualMovie.getId() != null) {
				movies.add(actualMovie);
			}
			actualMovie.setMovieSessions(new HashSet<MovieSession>());

			actualMovieSessions = actualMovie.getMovieSessions();
			actualMovieSessions.add(movieSession);
			actualMovie.setMovieSessions(actualMovieSessions);
		}
		if (!movies.isEmpty()) {
			movies.add(actualMovie);
		}
		return movies;

	}

	@Override
	public Set<City> getAllCities() {
		Set<City> cities = new HashSet<City>();
		cityDao.findAll().forEach(cities::add);
		return cities;
	}

	@Override
	public Set<Cinema> getCinemasByCityId(Long cityId) throws InstanceNotFoundException {
		City city = checkCity(cityId);
		return cinemaDao.findByCityId(city.getId());
	}

	@Override
	public Movie getMovieById(Long movieId) throws InstanceNotFoundException {
		return checkMovie(movieId);
	}

	@Override
	public MovieSession getMovieSessionById(Long movieSessionId)
			throws InstanceNotFoundException, MovieSessionAlreadyStartedException {
		return checkSession(movieSessionId);
	}

	private Movie checkMovie(Long movieId) throws InstanceNotFoundException {
		Optional<Movie> movie = movieDao.findById(movieId);
		if (!movie.isPresent()) {
			throw new InstanceNotFoundException("project.entities.Movie", movieId);
		}
		return movie.get();
	}

	private City checkCity(Long cityId) throws InstanceNotFoundException {
		Optional<City> city = cityDao.findById(cityId);
		if (!city.isPresent()) {
			throw new InstanceNotFoundException("project.entities.City", cityId);
		}
		return city.get();
	}

	private MovieSession checkSession(Long sessionId)
			throws InstanceNotFoundException, MovieSessionAlreadyStartedException {
		Optional<MovieSession> session = movieSessionDao.findById(sessionId);
		if (!session.isPresent()) {
			throw new InstanceNotFoundException("project.entities.MovieSession", sessionId);
		}

		if (session.get().getDate().isBefore(LocalDateTime.now())) {
			throw new MovieSessionAlreadyStartedException(sessionId);
		}
		return session.get();
	}

}
