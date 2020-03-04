package es.udc.paproject.backend.model.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Book;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;
import es.udc.paproject.backend.model.entities.MovieSession;
import es.udc.paproject.backend.model.entities.User;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private MovieDao movieDao;

	@Override
	@Transactional(readOnly=true)
	public Optional<Movie> findMovie(Long movieId) {
		return movieDao.findById(movieId);
	}

	@Override
	public Long bookTicket(int seats, Long creditCard, MovieSession sessionId, User userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deliverTicket(Long creditCard, Long bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Book> getBookRecord(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
