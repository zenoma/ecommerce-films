package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.BookConversor.toBookDtos;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.paproject.backend.model.entities.Book;
import es.udc.paproject.backend.model.exceptions.BookAlreadyTakenException;
import es.udc.paproject.backend.model.exceptions.CodeAndCreditCardNotMatchException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.MovieSessionAlreadyStartedException;
import es.udc.paproject.backend.model.exceptions.NotEnoughtSeatsException;
import es.udc.paproject.backend.model.exceptions.PermissionException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.BookingService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.BookDto;
import es.udc.paproject.backend.rest.dtos.BookParamsDto;
import es.udc.paproject.backend.rest.dtos.DeliverTicketDto;
import es.udc.paproject.backend.rest.dtos.IdDto;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BookingService bookingService;

	private final static String NOT_ENOUGH_SEATS_EXCEPTION_CODE = "project.exceptions.NotEnoughtSeatsException";
	private final static String MOVIE_SESSION_ALREADY_STARTED_EXCEPTION_CODE = "project.exceptions.MovieSessionAlreadyStartedException";
	private final static String CODE_AND_CREDITCARD_NOT_MATCH_EXCEPTION_CODE = "project.exceptions.CodeAndCreditCardNotMatchException";
	private final static String BOOK_ALREADY_TAKEN_EXCEPTION_CODE = "project.exceptions.BookAlreadyTakenException";

	@ExceptionHandler(NotEnoughtSeatsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleNotEnoughtSeatsException(NotEnoughtSeatsException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(NOT_ENOUGH_SEATS_EXCEPTION_CODE, new Object[] {exception.getSessionId(),exception.getSeatsLeft()},
				NOT_ENOUGH_SEATS_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	@ExceptionHandler(MovieSessionAlreadyStartedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleMovieSessionAlreadyStartedException(MovieSessionAlreadyStartedException exception,
			Locale locale) {

		String errorMessage = messageSource.getMessage(MOVIE_SESSION_ALREADY_STARTED_EXCEPTION_CODE,
				new Object[] {exception.getMovieId()},MOVIE_SESSION_ALREADY_STARTED_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	@ExceptionHandler(CodeAndCreditCardNotMatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleCodeAndCreditCardNotMatchException(CodeAndCreditCardNotMatchException exception,
			Locale locale) {

		String errorMessage = messageSource.getMessage(CODE_AND_CREDITCARD_NOT_MATCH_EXCEPTION_CODE, 
				new Object[] {exception.getCode(), exception.getCreditCard()}, CODE_AND_CREDITCARD_NOT_MATCH_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	@ExceptionHandler(BookAlreadyTakenException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleBookAlreadyTakenException(BookAlreadyTakenException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(BOOK_ALREADY_TAKEN_EXCEPTION_CODE,
				new Object[] {exception.getBookId()},BOOK_ALREADY_TAKEN_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	@PostMapping("/books/buy")
	public IdDto buyTickets(@Validated @RequestBody BookParamsDto bookParamsDto, @RequestAttribute Long userId)
			throws InstanceNotFoundException, NotEnoughtSeatsException, MovieSessionAlreadyStartedException {

		return new IdDto(bookingService.bookTicket(bookParamsDto.getTickets(), bookParamsDto.getCreditcard(),
				bookParamsDto.getMovieSessionId(), userId));
	}

	@PostMapping("/books/deliver")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deliverTicket(@Validated @RequestBody DeliverTicketDto deliverTicketDto)
			throws InstanceNotFoundException, CodeAndCreditCardNotMatchException, BookAlreadyTakenException,
			MovieSessionAlreadyStartedException {

		bookingService.deliverTicket(deliverTicketDto.getCreditcard(), deliverTicketDto.getCode());

	}

	@GetMapping("/books")
	public BlockDto<BookDto> findBooks(@RequestAttribute Long userId, @RequestParam Long user,
			@RequestParam(defaultValue = "0") int page) throws InstanceNotFoundException, PermissionException {
		if (user != userId) {
			throw new PermissionException();
		}

		Block<Book> books = bookingService.getBookRecord(user, page, 10);

		return new BlockDto<BookDto>(toBookDtos(books.getItems()), books.getExistMoreItems());

	}

}
