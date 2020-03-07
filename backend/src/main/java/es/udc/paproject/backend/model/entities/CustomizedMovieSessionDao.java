package es.udc.paproject.backend.model.entities;

import java.time.LocalDateTime;
import java.util.Set;

public interface CustomizedMovieSessionDao {

	Set<MovieSession> findAllByRoomIdAndDate(Long roomId, LocalDateTime date);
}
