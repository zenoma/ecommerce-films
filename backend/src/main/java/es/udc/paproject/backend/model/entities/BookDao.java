package es.udc.paproject.backend.model.entities;

import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookDao extends PagingAndSortingRepository<Book, Long> {

    Set<Book> findByUserIdOrderByDateDesc(Long userId);
}
