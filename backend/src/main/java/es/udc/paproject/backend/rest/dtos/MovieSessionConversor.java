package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.MovieSession;
import static es.udc.paproject.backend.rest.dtos.MovieConversor.toMovieDto;
import static es.udc.paproject.backend.rest.dtos.RoomConversor.toRoomDto;

public class MovieSessionConversor {

	private MovieSessionConversor() {
	}

	public final static MovieSessionDto toMovieSessionDto(MovieSession movieSession) {
		MovieDto movie = toMovieDto(movieSession.getMovie());
		RoomDto room = toRoomDto(movieSession.getRoom());

		return new MovieSessionDto(movieSession.getId(), movie, room, movieSession.getPrice(), movieSession.getSeats(),
				movieSession.getDate());
	}

}
