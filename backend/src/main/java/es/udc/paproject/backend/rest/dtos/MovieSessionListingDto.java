package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;

public class MovieSessionListingDto {
	private Long id;
	private RoomDto room;
	private double price;
	private int seats;
	private LocalDateTime date;

	public MovieSessionListingDto() {
	}

	public MovieSessionListingDto(Long id, RoomDto room, double price, int seats, LocalDateTime date) {
		this.id = id;
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
