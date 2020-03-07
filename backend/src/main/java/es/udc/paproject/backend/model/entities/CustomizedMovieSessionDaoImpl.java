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

	@Override
	public Set<MovieSession> findAllByRoomIdAndDate(Long roomId, LocalDateTime date) {

		String queryString = "SELECT ms FROM MovieSession ms WHERE year(ms.date) = ";
		queryString += String.valueOf(date.getYear());
		queryString += " AND month(ms.date) = ";
		queryString += String.valueOf(date.getMonthValue());
		queryString += " AND day(ms.date) = ";
		queryString += String.valueOf(date.getDayOfMonth());
		queryString += " ORDER BY ms.date";
		Query query = entityManager.createQuery(queryString);
		
		Set<MovieSession> resultList = new HashSet<MovieSession>(query.getResultList());
		return resultList;
	}

}
