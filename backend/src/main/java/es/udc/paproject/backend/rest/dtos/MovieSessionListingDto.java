package es.udc.paproject.backend.rest.dtos;

import java.time.LocalTime;

public class MovieSessionListingDto {
	private Long id;
	private LocalTime hour;

	public MovieSessionListingDto() {
	}

	public MovieSessionListingDto(Long id, LocalTime hour) {
		this.id = id;
		this.hour = hour;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
	}
}
