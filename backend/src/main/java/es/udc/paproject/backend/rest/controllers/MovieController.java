package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.CityConversor.toCityDtos;
import static es.udc.paproject.backend.rest.dtos.CinemaConversor.toCinemaDtos;
import static es.udc.paproject.backend.rest.dtos.MovieConversor.toMovieDto;
import static es.udc.paproject.backend.rest.dtos.MovieSessionConversor.toMovieSessionDto;
import static es.udc.paproject.backend.rest.dtos.ListingItemConversor.toListingItemDtos;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.CinemaDto;
import es.udc.paproject.backend.rest.dtos.CityDto;
import es.udc.paproject.backend.rest.dtos.ListingItemDto;
import es.udc.paproject.backend.rest.dtos.MovieDto;
import es.udc.paproject.backend.rest.dtos.MovieSessionDto;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.MovieSessionAlreadyStartedException;
import es.udc.paproject.backend.model.exceptions.PlusWeekDateException;
import es.udc.paproject.backend.model.exceptions.PreviousDateException;
import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.entities.ListingItem;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieSession;
import es.udc.paproject.backend.model.services.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private final static String PREVIOUS_DATE_EXCEPTION_CODE = "project.exceptions.PreviousDateException";
	private final static String PLUS_WEEK_DATE_EXCEPTION_CODE = "project.exceptions.PlusWeekDateException";
	private final static String MOVIE_SESSION_ALREADY_STARTED_EXCEPTION_CODE = "project.exceptions.MovieSessionAlreadyStartedException";

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MovieService movieService;

	@ExceptionHandler(PreviousDateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handlePreviousDateException(PreviousDateException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(PREVIOUS_DATE_EXCEPTION_CODE, null, PREVIOUS_DATE_EXCEPTION_CODE,
				locale);

		return new ErrorsDto(errorMessage);

	}

	@ExceptionHandler(PlusWeekDateException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handlePlusWeekDateException(PlusWeekDateException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(PLUS_WEEK_DATE_EXCEPTION_CODE, null,
				PLUS_WEEK_DATE_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	@ExceptionHandler(MovieSessionAlreadyStartedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleMovieSessionAlreadyStartedException(MovieSessionAlreadyStartedException exception,
			Locale locale) {
		String errorMessage = messageSource.getMessage(MOVIE_SESSION_ALREADY_STARTED_EXCEPTION_CODE,
				new Object[] {exception.getMovieId() }, MOVIE_SESSION_ALREADY_STARTED_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	@GetMapping("/cities")
	public List<CityDto> getCities() {
		List<City> cities = movieService.getAllCities();
		return toCityDtos(cities);
	}

	@GetMapping("/cinemas")
	public List<CinemaDto> getCinemas(@RequestParam Long cityId) throws InstanceNotFoundException {
		List<Cinema> cinemas = movieService.getCinemasByCityId(cityId);
		return toCinemaDtos(cinemas);
	}

	@GetMapping("{movieId}")
	public MovieDto getMovie(@PathVariable Long movieId) throws InstanceNotFoundException {
		Movie movie = movieService.getMovieById(movieId);
		return toMovieDto(movie);
	}

	@GetMapping("/movieSession/{movieSessionId}")
	public MovieSessionDto getMovieSession(@PathVariable Long movieSessionId)
			throws InstanceNotFoundException, MovieSessionAlreadyStartedException {
		MovieSession movieSession = movieService.getMovieSessionById(movieSessionId);
		return toMovieSessionDto(movieSession);
	}

	@GetMapping("/listing")
	public List<ListingItemDto> getListing(@RequestParam Long cinemaId, @RequestParam(required = false) String date)
			throws InstanceNotFoundException, PreviousDateException, PlusWeekDateException {
		LocalDate localDate = null;
		if (date == null) {
			localDate = LocalDate.now();
		} else {
			localDate = LocalDate.parse(date);
		}
		List<ListingItem> listing = movieService.getListing(cinemaId, localDate);
		return toListingItemDtos(listing);
	}
}
