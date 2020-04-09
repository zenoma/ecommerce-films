package es.udc.paproject.backend.model.entities;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieSessionDao extends PagingAndSortingRepository<MovieSession, Long>{
	Set<MovieSession> findByRoomCinemaIdAndDateBetweenOrderByMovieTitleDescDateAsc(Long cinemaId, LocalDateTime startDate, LocalDateTime endDate);
}
