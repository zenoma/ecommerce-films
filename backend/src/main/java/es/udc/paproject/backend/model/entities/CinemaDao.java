package es.udc.paproject.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CinemaDao extends PagingAndSortingRepository<Cinema, Long>{
	
	Optional<Cinema> findByName(String name);

}
