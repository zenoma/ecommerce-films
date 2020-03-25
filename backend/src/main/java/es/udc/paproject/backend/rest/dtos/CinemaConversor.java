package es.udc.paproject.backend.rest.dtos;

import java.util.Set;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Cinema;

public class CinemaConversor {

	private CinemaConversor() {}	
	
	public final static Set<CinemaDto> toCinemaDtos(Set<Cinema> cinemas) {
		return cinemas.stream().map(cinema -> toCinemaDto(cinema)).collect(Collectors.toSet());
	}
	
	public final static CinemaDto toCinemaDto(Cinema cinema) {
		return new CinemaDto(cinema.getId(), cinema.getName());
	}
	
	//Sólo necesario si modificásemos o creasemos algún cine
	/*public final static Cinema toCinema(CinemaDto cinemaDto) {
		return new Cinema(cinemaDto.getName());
	}*/
	
}
