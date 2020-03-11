package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class BookAlreadyTakenException extends Exception {

    private Long bookId;

    public BookAlreadyTakenException(Long bookId) {
	this.bookId = bookId;
    }

    public final Long getBookId() {
	return bookId;
    }
}
