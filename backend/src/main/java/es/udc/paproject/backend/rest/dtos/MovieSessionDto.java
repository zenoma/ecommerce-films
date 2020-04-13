package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovieSessionDto {
	private String movieTitle;
	private int movieDuration;
	private String roomName;
	private BigDecimal price;
	private int seats;
	private LocalDateTime date;

	public MovieSessionDto() {
	}
	
	public MovieSessionDto(String movieTitle, int movieDuration, String roomName, BigDecimal price, int seats, LocalDateTime date) {
		this.movieTitle = movieTitle;
		this.movieDuration = movieDuration;
		this.roomName = roomName;
		this.price = price;
		this.seats = seats;
		this.date = date;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public int getMovieDuration() {
		return movieDuration;
	}

	public void setMovieDuration(int movieDuration) {
		this.movieDuration = movieDuration;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
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
