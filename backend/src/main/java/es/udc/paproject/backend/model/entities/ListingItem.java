package es.udc.paproject.backend.model.entities;

import java.util.List;

public class ListingItem {
	private Movie movie;
	private List<MovieSession> movieSessions;
	
	public ListingItem(Movie movie, List<MovieSession> movieSessions) {
		this.movie = movie;
		this.movieSessions = movieSessions;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public List<MovieSession> getMovieSessions() {
		return movieSessions;
	}
	
	public void setMovieSessions(List<MovieSession> movieSessions) {
		this.movieSessions = movieSessions;
	}
}
