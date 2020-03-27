package es.udc.paproject.backend.rest.dtos;

import java.util.Set;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Movie;

public class MovieConversor {
	private MovieConversor() {
	}

	public final static Set<MovieDto> toMovieDtos(Set<Movie> movies) {
		return movies.stream().map(movie -> toMovieDto(movie)).collect(Collectors.toSet());
	}

	public final static MovieDto toMovieDto(Movie movie) {
		return new MovieDto(movie.getId(), movie.getTitle(), movie.getSynopsis(), movie.getDuration());
	}

}