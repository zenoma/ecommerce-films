package es.udc.paproject.backend.rest.dtos;

public class CityDto {
	
	private Long id;
	private String name;

	public CityDto(Long id, String name) {
		this.id=id;
		this.name=name != null ? name.trim() : null;;
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
