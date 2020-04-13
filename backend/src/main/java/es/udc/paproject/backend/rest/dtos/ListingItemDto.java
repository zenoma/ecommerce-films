package es.udc.paproject.backend.rest.dtos;

import java.util.List;

public class ListingItemDto {
	private Long movieId;
	private String movieTitle;
	private List<MovieSessionListingDto> movieSessions;
	
	public ListingItemDto() {}
	
	public ListingItemDto(Long movieId, String movieTitle, List<MovieSessionListingDto> movieSessions) {
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.setMovieSessions(movieSessions);
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public List<MovieSessionListingDto> getMovieSessions() {
		return movieSessions;
	}

	public void setMovieSessions(List<MovieSessionListingDto> movieSessions) {
		this.movieSessions = movieSessions;
	}

	
}
