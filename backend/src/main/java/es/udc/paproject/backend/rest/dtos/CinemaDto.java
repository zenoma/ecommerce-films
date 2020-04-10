package es.udc.paproject.backend.rest.dtos;

public class CinemaDto {
	
	private Long id;
	private String name;

	public CinemaDto(Long id, String name) {
		this.id=id;
		this.name=name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
