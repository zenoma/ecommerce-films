package es.udc.paproject.backend.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.MovieDao;

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

}
