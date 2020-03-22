package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class MovieSessionAlreadyStartedException extends Exception{
	private Long movieId;
	
	public MovieSessionAlreadyStartedException(Long movieId) {
		this.movieId=movieId;
	}
	
	public final Long getMovieId() {
		return movieId;
	}
}
