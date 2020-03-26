package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.UserConversor.toAuthenticatedUserDto;
import static es.udc.paproject.backend.rest.dtos.UserConversor.toUser;
import static es.udc.paproject.backend.rest.dtos.UserConversor.toUserDto;

import java.net.URI;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.udc.paproject.backend.model.entities.Book;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.exceptions.BookAlreadyTakenException;
import es.udc.paproject.backend.model.exceptions.CodeAndCreditCardNotMatchException;
import es.udc.paproject.backend.model.exceptions.CreditCardNumberException;
import es.udc.paproject.backend.model.exceptions.DuplicateInstanceException;
import es.udc.paproject.backend.model.exceptions.IncorrectLoginException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidSeatsException;
import es.udc.paproject.backend.model.exceptions.MovieSessionAlreadyStartedException;
import es.udc.paproject.backend.model.exceptions.NotEnoughtSeatsException;
import es.udc.paproject.backend.model.exceptions.PermissionException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.BookingService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.common.JwtGenerator;
import es.udc.paproject.backend.rest.common.JwtInfo;
import es.udc.paproject.backend.rest.dtos.AuthenticatedUserDto;
import es.udc.paproject.backend.rest.dtos.BookParamsDto;
import es.udc.paproject.backend.rest.dtos.UserDto;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private BookingService bookingService;
	
	private final static String NOT_ENOUGH_SEATS_EXCEPTION_CODE = "project.exceptions.NotEnoughtSeatsException";
	private final static String INVALID_SEATS_EXCEPTION_CODE = "project.exceptions.InvalidSeatsException";
	private final static String CREDIT_CARD_NUMBER_EXCEPTION_CODE = "project.exceptions.CreditCardNumberException";
	private final static String MOVIE_SESSION_ALREADY_STARTED_EXCEPTION_CODE = "project.exceptions.MovieSessionAlreadyStartedException";
	private final static String CODE_AND_CREDITCARD_NOT_MATCH_EXCEPTION_CODE = "project.exceptions.CodeAndCreditCardNotMatchException";
	private final static String BOOK_ALREADY_TAKEN_EXCEPTION_CODE = "project.exceptions.BookAlreadyTakenException";
	
	
	@ExceptionHandler(NotEnoughtSeatsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleNotEnoughtSeatsException(NotEnoughtSeatsException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(NOT_ENOUGH_SEATS_EXCEPTION_CODE, null,
				NOT_ENOUGH_SEATS_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
		
	}
	
	@ExceptionHandler(InvalidSeatsException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public ErrorsDto handleInvalidSeatsException(InvalidSeatsException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(INVALID_SEATS_EXCEPTION_CODE, null,
				INVALID_SEATS_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
		
	}
	
	@ExceptionHandler(CreditCardNumberException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public ErrorsDto handleCreditCardNumberException(CreditCardNumberException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(CREDIT_CARD_NUMBER_EXCEPTION_CODE, null,
				CREDIT_CARD_NUMBER_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
		
	}
	
	@ExceptionHandler(MovieSessionAlreadyStartedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleMovieSessionAlreadyStartedException(MovieSessionAlreadyStartedException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(MOVIE_SESSION_ALREADY_STARTED_EXCEPTION_CODE, null,
				MOVIE_SESSION_ALREADY_STARTED_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
		
	}
	
	@ExceptionHandler(CodeAndCreditCardNotMatchException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public ErrorsDto handleCodeAndCreditCardNotMatchException(CodeAndCreditCardNotMatchException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(CODE_AND_CREDITCARD_NOT_MATCH_EXCEPTION_CODE, null,
				CODE_AND_CREDITCARD_NOT_MATCH_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
		
	}
	
	@ExceptionHandler(BookAlreadyTakenException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleBookAlreadyTakenException(BookAlreadyTakenException exception, Locale locale) {
		
		String errorMessage = messageSource.getMessage(BOOK_ALREADY_TAKEN_EXCEPTION_CODE, null,
				BOOK_ALREADY_TAKEN_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);
		
	}
	
	@PostMapping("/bookticket")
	public Long bookTicket(
		@Validated({BookParamsDto.AllValidations.class}) @RequestBody BookParamsDto bookParamsDto) throws InstanceNotFoundException, NotEnoughtSeatsException, CreditCardNumberException, InvalidSeatsException, MovieSessionAlreadyStartedException{
		
		return bookingService.bookTicket(bookParamsDto.getTickets(), bookParamsDto.getCredit_card(), bookParamsDto.getMovieSession().getId(), bookParamsDto.getUser().getId());
		
	}
	
	@PutMapping("/deliverticket")
	public void deliverTicket(@RequestBody Long creditCard, @RequestBody Long code) throws InstanceNotFoundException, CodeAndCreditCardNotMatchException, BookAlreadyTakenException, MovieSessionAlreadyStartedException 
	{
				
		bookingService.deliverTicket(creditCard, code);
		
	}
	
	@GetMapping("/{userId}")
	public Block<Book> findOrder(@PathVariable Long userId) throws InstanceNotFoundException {
		
		return bookingService.getBookRecord(userId);
		
	}
	
}
