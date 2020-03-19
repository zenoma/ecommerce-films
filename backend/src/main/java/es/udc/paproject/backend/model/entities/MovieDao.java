package es.udc.paproject.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieDao extends PagingAndSortingRepository<Movie, Long> {
	
	Optional<Movie> findByTitle(String tittle);

}
