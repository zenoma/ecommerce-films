package es.udc.paproject.backend.model.entities;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CinemaDao extends PagingAndSortingRepository<Cinema, Long>{
	
	Optional<Cinema> findByName(String name);
	
	Set<Cinema> findByCityId(Long cityId);

}
