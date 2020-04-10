package es.udc.paproject.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Book;

public class BookConversor {

	private BookConversor() {
	}

	public final static List<BookParamsDto> toBookParamsDtos(List<Book> books) {
		return books.stream().map(book -> toBookParamsDto(book)).collect(Collectors.toList());
	}

	public final static BookParamsDto toBookParamsDto(Book book) {
		return new BookParamsDto(book.getTickets(), book.getMovieSession().getId(), book.getCreditcard());
	}

	public final static List<BookDto> toBookDtos(List<Book> books) {
		return books.stream().map(book -> toBookDto(book)).collect(Collectors.toList());
	}

	public final static BookDto toBookDto(Book book) {
		return new BookDto(book.getDate(), book.getMovieSession().getMovie().getTitle(), book.getTickets(),
				book.getTickets() * book.getMovieSession().getPrice().doubleValue(), book.getMovieSession().getDate());
	}
}
