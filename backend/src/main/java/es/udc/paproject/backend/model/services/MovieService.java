package es.udc.paproject.backend.model.services;

import java.time.LocalDateTime;
import java.util.Set;

import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieSession;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.MovieSessionAlreadyStartedException;
import es.udc.paproject.backend.model.exceptions.PlusWeekDateException;
import es.udc.paproject.backend.model.exceptions.PreviousDateException;

public interface MovieService {

	Set<Movie> getListing(Long cinemaId, LocalDateTime date)
			throws InstanceNotFoundException, PreviousDateException, PlusWeekDateException;

	Set<City> getAllCities();

	Set<Cinema> getCinemasByCityId(Long cityId) throws InstanceNotFoundException;

	Movie getMovieById(Long movieId) throws InstanceNotFoundException;

	MovieSession getMovieSessionById(Long movieSessionId)
			throws InstanceNotFoundException, MovieSessionAlreadyStartedException;

}
