package es.udc.paproject.backend.rest.dtos;

import java.util.List;

public class MovieListingDto {
	private Long id;
	private String title;
	private String synopsis;
	private int duration;
	private List<MovieSessionListingDto> sessions;

	public MovieListingDto() {
	}

	public MovieListingDto(Long id, String title, String synopsis, int duration,
			List<MovieSessionListingDto> sessions) {
		super();
		this.id = id;
		this.title = title;
		this.synopsis = synopsis;
		this.duration = duration;
		this.sessions = sessions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<MovieSessionListingDto> getSessions() {
		return sessions;
	}

	public void setSessions(List<MovieSessionListingDto> sessions) {
		this.sessions = sessions;
	}

}
