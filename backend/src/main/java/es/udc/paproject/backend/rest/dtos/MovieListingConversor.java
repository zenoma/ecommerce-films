package es.udc.paproject.backend.rest.dtos;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Movie;
import static es.udc.paproject.backend.rest.dtos.MovieSessionListingConversor.toMovieSessionListingDto;;

public class MovieListingConversor {
	private MovieListingConversor() {
	}

	public final static Set<MovieListingDto> toMovieListingDtos(Set<Movie> moviesListing) {
		return moviesListing.stream().map(movieListing -> toMovieListingDto(movieListing)).collect(Collectors.toSet());
	}

	public final static MovieListingDto toMovieListingDto(Movie movieListing) {
		//List<MovieSessionListingDto> sessions = movieListing.getMovieSessions().stream()
		//		.map(i -> toMovieSessionListingDto(i)).collect(Collectors.toList());

		//return new MovieListingDto(movieListing.getId(), movieListing.getTitle(), movieListing.getSynopsis(),
		//		movieListing.getDuration(), sessions);
		return null;
	}
}
