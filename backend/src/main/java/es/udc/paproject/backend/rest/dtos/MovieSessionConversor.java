package es.udc.paproject.backend.rest.dtos;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.MovieSession;

public class MovieSessionConversor {

	private MovieSessionConversor() {
	}

	public final static MovieSessionDto toMovieSessionDto(MovieSession movieSession) {
		return new MovieSessionDto(movieSession.getMovie().getTitle(), movieSession.getMovie().getDuration(), movieSession.getRoom().getCinema().getName(), movieSession.getRoom().getName(),
				movieSession.getPrice(), movieSession.getSeats(), movieSession.getDate());
	}

	public final static List<MovieSessionListingDto> toMovieSessionListingDtos(List<MovieSession> movieSessions) {
		return movieSessions.stream().map(movieSession -> toMovieSessionListingDto(movieSession)).collect(Collectors.toList());
	}
	
	public final static MovieSessionListingDto toMovieSessionListingDto(MovieSession movieSession) {
		return new MovieSessionListingDto(movieSession.getId(), LocalTime.of(movieSession.getDate().getHour(), movieSession.getDate().getMinute()));
	}
}
