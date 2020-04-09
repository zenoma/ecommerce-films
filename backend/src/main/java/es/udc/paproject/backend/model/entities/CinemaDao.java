package es.udc.paproject.backend.model.entities;

import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CinemaDao extends PagingAndSortingRepository<Cinema, Long>{
	Set<Cinema> findByCityIdOrderByNameDesc(Long cityId);

}
