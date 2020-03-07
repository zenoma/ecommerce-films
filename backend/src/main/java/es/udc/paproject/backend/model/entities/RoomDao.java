package es.udc.paproject.backend.model.entities;

import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomDao extends PagingAndSortingRepository<Room, Long>{
 
	Set<Room> findAllByCinemaId(Long cinemaId);
}
