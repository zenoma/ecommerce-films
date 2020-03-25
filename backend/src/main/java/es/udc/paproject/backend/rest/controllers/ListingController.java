package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.CityConversor.toCityDtos;
import static es.udc.paproject.backend.rest.dtos.CinemaConversor.toCinemaDtos;

import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.CinemaDto;
import es.udc.paproject.backend.rest.dtos.CityDto;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PreviousDateException;
import es.udc.paproject.backend.model.entities.Cinema;
import es.udc.paproject.backend.model.entities.City;
import es.udc.paproject.backend.model.services.MovieService;

@RestController
@RequestMapping("/listing")
public class ListingController {
	
	private final static String PREVIOUS_DATE_EXCEPTION_CODE = "project.exceptions.PreviousDateException";
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MovieService movieService;
	
	@ExceptionHandler(PreviousDateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleMaxQuantityExceededException(PreviousDateException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(PREVIOUS_DATE_EXCEPTION_CODE, null, PREVIOUS_DATE_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
		
	}
	
	@GetMapping("/city")
	public Set<CityDto> getCities() {
		Set<City> cities= movieService.getAllCities();
		return toCityDtos(cities);
	}
	
	@GetMapping("/city/{cityId}")
	public Set<CinemaDto> getCinemas(@PathVariable Long cityId) throws InstanceNotFoundException {
		Set<Cinema> cinemas= movieService.getCinemasByCityId(cityId);
		return toCinemaDtos(cinemas);
	}
}
