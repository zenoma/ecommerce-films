package es.udc.paproject.backend.rest.dtos;

public class MovieDto {
	private Long id;
	private String title;
	private String synopsis;
	private int duration;

	public MovieDto() {
	}

	public MovieDto(Long id, String title, String synopsis, int duration) {
		this.id = id;
		this.title = title;
		this.synopsis = synopsis;
		this.duration = duration;
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

}
