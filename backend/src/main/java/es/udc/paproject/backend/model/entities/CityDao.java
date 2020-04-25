package es.udc.paproject.backend.model.entities;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityDao extends PagingAndSortingRepository<City, Long>{
	List<City> findAllByOrderByName();
}
