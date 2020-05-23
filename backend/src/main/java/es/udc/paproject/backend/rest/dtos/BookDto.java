package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;

public class BookDto {

	private Long id;
	private LocalDateTime dateBook;
	private String movieTitle;
	private int tickets;
	private double price;
	private LocalDateTime dateMovieSession;

	public BookDto() {
	}

	public BookDto(Long id, LocalDateTime dateBook, String movieTitle, int tickets, double price,
			LocalDateTime dateMovieSession) {
		super();
		this.id = id;
		this.dateBook = dateBook;
		this.movieTitle = movieTitle;
		this.tickets = tickets;
		this.price = price;
		this.dateMovieSession = dateMovieSession;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public double getprice() {
		return price;
	}

	public void setprice(double price) {
		this.price = price;
	}

	public LocalDateTime getDateMovieSession() {
		return dateMovieSession;
	}

	public void setDateMovieSession(LocalDateTime dateMovieSession) {
		this.dateMovieSession = dateMovieSession;
	}

}
