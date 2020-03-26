package es.udc.paproject.backend.rest.dtos;

import static es.udc.paproject.backend.rest.dtos.RoomConversor.toRoomDto;

import es.udc.paproject.backend.model.entities.MovieSession;

public class MovieSessionListingConversor {
	private MovieSessionListingConversor() {
	}

	public final static MovieSessionListingDto toMovieSessionListingDto(MovieSession movieSession) {
		RoomDto room = toRoomDto(movieSession.getRoom());

		return new MovieSessionListingDto(movieSession.getId(), room, movieSession.getPrice(), movieSession.getSeats(),
				movieSession.getDate());
	}
	
}
