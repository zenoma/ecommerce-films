package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;

import es.udc.paproject.backend.model.entities.MovieSession;
import es.udc.paproject.backend.model.entities.User;

public class BookParamsDto {
	
	public interface AllValidations {}
	
	public interface UpdateValidations {}

	private Long id;
    private int tickets;
    private MovieSession movieSession;
    private Long credit_card;
    private User user;
    private LocalDateTime date;
    private boolean withdraw;
    
	public BookParamsDto(Long id, int tickets, MovieSession movieSession, Long credit_card, User user,
			LocalDateTime date, boolean withdraw) {
		super();
		this.id = id;
		this.tickets = tickets;
		this.movieSession = movieSession;
		this.credit_card = credit_card;
		this.user = user;
		this.date = date;
		this.withdraw = withdraw;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	public MovieSession getMovieSession() {
		return movieSession;
	}

	public void setMovieSession(MovieSession movieSession) {
		this.movieSession = movieSession;
	}

	public Long getCredit_card() {
		return credit_card;
	}

	public void setCredit_card(Long credit_card) {
		this.credit_card = credit_card;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isWithdraw() {
		return withdraw;
	}

	public void setWithdraw(boolean withdraw) {
		this.withdraw = withdraw;
	}
    
}
