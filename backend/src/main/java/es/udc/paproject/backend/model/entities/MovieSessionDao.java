package es.udc.paproject.backend.model.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieSessionDao extends PagingAndSortingRepository<MovieSession, Long>{
	List<MovieSession> findByRoomCinemaIdAndDateBetweenOrderByMovieTitleAscDateAsc(Long cinemaId, LocalDateTime startDate, LocalDateTime endDate);
}
