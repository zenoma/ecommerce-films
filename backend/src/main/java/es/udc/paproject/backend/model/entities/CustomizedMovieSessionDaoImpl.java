package es.udc.paproject.backend.model.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CustomizedMovieSessionDaoImpl implements CustomizedMovieSessionDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Set<MovieSession> findAllByRoomIdAndDate(Long roomId, LocalDateTime date) {

		String queryString = "SELECT ms, m, r FROM MovieSession ms, Movie m, Room r WHERE ms.room = "
				+ String.valueOf(roomId) + " AND year(ms.date) = ";
		queryString += String.valueOf(date.getYear());
		queryString += " AND month(ms.date) = ";
		queryString += String.valueOf(date.getMonthValue());
		queryString += " AND day(ms.date) = ";
		queryString += String.valueOf(date.getDayOfMonth());
		queryString += " AND hour(ms.date) >= ";
		queryString += String.valueOf(date.getHour());
		queryString += " AND minute(ms.date) >= ";
		queryString += String.valueOf(date.getMinute());
		queryString += " ORDER BY m.id";
		Query query = entityManager.createQuery(queryString);

		Set<MovieSession> resultList = new HashSet<>(query.getResultList());
		return resultList;
	}

}
