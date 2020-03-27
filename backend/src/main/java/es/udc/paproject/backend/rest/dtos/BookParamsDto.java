package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import es.udc.paproject.backend.model.entities.MovieSession;

public class BookParamsDto {

	public interface AllValidations {
	}

	public interface UpdateValidations {
	}

	private int tickets;
	private Long movieSessionId;
	private Long credit_card;

	public BookParamsDto(int tickets, Long movieSessionId, Long credit_card) {
		this.tickets = tickets;
		this.movieSessionId = movieSessionId;
		this.credit_card = credit_card;
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
	public Long getCredit_card() {
		return credit_card;
	}

	public void setCredit_card(Long credit_card) {
		this.credit_card = credit_card;
	}

}
