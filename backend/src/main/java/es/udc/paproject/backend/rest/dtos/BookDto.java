package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;

public class BookDto {

	private LocalDateTime dateBook;
	private String movieTitle;
	private int tickets;
	private double prize;
	private LocalDateTime dateMovieSession;
	
	public BookDto() {}

	public BookDto(LocalDateTime dateBook, String movieTitle, int tickets, double prize,
			LocalDateTime dateMovieSession) {
		super();
		this.dateBook = dateBook;
		this.movieTitle = movieTitle;
		this.tickets = tickets;
		this.prize = prize;
		this.dateMovieSession = dateMovieSession;
	}

	public LocalDateTime getDateBook() {
		return dateBook;
	}

	public void setDateBook(LocalDateTime dateBook) {
		this.dateBook = dateBook;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	public double getPrize() {
		return prize;
	}

	public void setPrize(double prize) {
		this.prize = prize;
	}

	public LocalDateTime getDateMovieSession() {
		return dateMovieSession;
	}

	public void setDateMovieSession(LocalDateTime dateMovieSession) {
		this.dateMovieSession = dateMovieSession;
	}

}
