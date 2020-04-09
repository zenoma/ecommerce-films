package es.udc.paproject.backend.model.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.MovieSessionAlreadyStartedException;
import es.udc.paproject.backend.model.exceptions.PlusWeekDateException;
import es.udc.paproject.backend.model.exceptions.PreviousDateException;

@Service
@Transactional(readOnly = true)
public class MovieServiceImpl implements MovieService {

	@Autowired
	private CityDao cityDao;

	@Autowired
	private CinemaDao cinemaDao;

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private MovieSessionDao movieSessionDao;

	@Override
	public Set<ListingItem> getListing(Long cinemaId, LocalDate date)
			throws InstanceNotFoundException, PreviousDateException, PlusWeekDateException {
		LocalDateTime startDate=LocalDateTime.of(date, LocalTime.now()).withNano(0);
		LocalDateTime endDate=LocalDateTime.of(date, LocalTime.of(23,  59)).withNano(0);
		LocalDateTime limitWeek=LocalDateTime.now().plusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
		Set<ListingItem> listingItems=new HashSet<>();
		
		if(!cinemaDao.findById(cinemaId).isPresent()) {
			throw new InstanceNotFoundException("project.entities.Cinema", cinemaId);
		}

		if (startDate.isBefore(LocalDateTime.now().withNano(0)))
			throw new PreviousDateException();
		
		if (endDate.isAfter(limitWeek))
			throw new PlusWeekDateException();

		Set<MovieSession> movieSessionList=movieSessionDao.findByRoomCinemaIdAndDateBetweenOrderByMovieTitleDescDateAsc(cinemaId, startDate, endDate);
		
		Movie movie=new Movie();
		List<MovieSession> movieSessions=new ArrayList<>();

		for(MovieSession movieSession:movieSessionList) {
			movieSessions.add(movieSession);
			if(movie.getId()!=null && movie.getId()!=movieSession.getMovie().getId()){
				listingItems.add(new ListingItem(movie, movieSessions));
				movieSessions.clear();
			}
			movie=movieSession.getMovie();
		}
		
		return listingItems;
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
		return cinemaDao.findByCityIdOrderByNameDesc(city.getId());
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
