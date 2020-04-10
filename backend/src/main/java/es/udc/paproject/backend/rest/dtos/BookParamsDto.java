package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class BookParamsDto {

	public interface AllValidations {
	}

	public interface UpdateValidations {
	}

	private int tickets;
	private Long movieSessionId;
	private String creditcard;

	public BookParamsDto(int tickets, Long movieSessionId, String creditcard) {
		this.tickets = tickets;
		this.movieSessionId = movieSessionId;
		this.creditcard = creditcard;
	}

	@NotNull
	@Min(1)
	@Max(10)
	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	@NotNull
	public Long getMovieSessionId() {
		return movieSessionId;
	}

	public void setMovieSessionId(Long movieSession) {
		this.movieSessionId = movieSession;
	}

	@NotNull
	@Length(min=16,max=16)
	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

}
