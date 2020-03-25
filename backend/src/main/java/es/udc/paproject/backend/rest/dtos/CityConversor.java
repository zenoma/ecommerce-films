package es.udc.paproject.backend.rest.dtos;

import java.util.Set;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.City;

public class CityConversor {
	
	private CityConversor() {}	
	
	public final static Set<CityDto> toCityDtos(Set<City> cities) {
		return cities.stream().map(city -> toCityDto(city)).collect(Collectors.toSet());
	}
	
	public final static CityDto toCityDto(City city) {
		return new CityDto(city.getId(), city.getName());
	}
	
	//Sólo necesario si modificásemos o creasemos alguna ciudad
	/*public final static City toCity(CityDto cityDto) {
		return new City(cityDto.getName());
	}*/
}
