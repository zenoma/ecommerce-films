package es.udc.paproject.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieSessionDao extends PagingAndSortingRepository<MovieSession, Long>, CustomizedMovieSessionDao{
	
}
