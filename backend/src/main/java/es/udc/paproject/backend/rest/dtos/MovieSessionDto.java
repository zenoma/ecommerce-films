package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;

public class MovieSessionDto {
	private Long id;
	private MovieDto movie;
	private RoomDto room;
	private double price;
	private int seats;
	private LocalDateTime date;

	public MovieSessionDto() {
	}

	public MovieSessionDto(Long id, MovieDto movie, RoomDto room, double price, int seats, LocalDateTime date) {
		this.id = id;
		this.movie = movie;
		this.room = room;
		this.price = price;
		this.seats = seats;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MovieDto getMovie() {
		return movie;
	}

	public void setMovie(MovieDto movie) {
		this.movie = movie;
	}

	public RoomDto getRoom() {
		return room;
	}

	public void setRoom(RoomDto room) {
		this.room = room;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
