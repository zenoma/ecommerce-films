package es.udc.paproject.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

import es.udc.paproject.backend.model.services.Block;

public interface BookDao extends PagingAndSortingRepository<Book, Long> {

    Block<Book> findByUserIdOrderByDateDesc(Long userId);
}
