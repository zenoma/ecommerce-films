package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class MovieAlreadyStartedException extends Exception{
	private Long movieId;
	
	public MovieAlreadyStartedException(Long movieId) {
		this.movieId=movieId;
	}
	
	public final Long getMovieId() {
		return movieId;
	}
}
