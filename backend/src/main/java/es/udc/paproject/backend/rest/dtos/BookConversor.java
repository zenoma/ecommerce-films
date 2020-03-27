package es.udc.paproject.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Book;

public class BookConversor {
	
	private BookConversor() {}
	
	public final static List<BookParamsDto> toBookDtos(List<Book> books) {
		return books.stream().map(book -> toBookDto(book)).collect(Collectors.toList());
	}
	
	public final static BookParamsDto toBookDto(Book book) {
		return new BookParamsDto(book.getId(), book.getTickets(), book.getMovieSession(), book.getCredit_card(), book.getUser(), book.getDate(), book.isWithdraw());
	}
}
