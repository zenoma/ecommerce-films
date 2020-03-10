package es.udc.paproject.backend.model.entities;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookDao extends PagingAndSortingRepository<Book, Long> {

    Optional<Book> findByCode(Long code);

    Set<Book> findAllByUserId(Long userId);
}
