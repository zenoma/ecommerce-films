package es.udc.paproject.backend.model.entities;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CinemaDao extends PagingAndSortingRepository<Cinema, Long>{
	List<Cinema> findByCityIdOrderByName(Long cityId);

}
