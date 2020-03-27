package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookDao extends PagingAndSortingRepository<Book, Long> {

    Slice<Book> findByUserIdOrderByDateDesc(Long userId, Pageable pageable);
}
