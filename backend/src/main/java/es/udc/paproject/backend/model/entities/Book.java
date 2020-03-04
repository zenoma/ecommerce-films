package es.udc.paproject.backend.model.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	private Long id;
	private int tickets;
	private MovieSession movieSession;
	private Long credit_card;
	private User user;
	private Date date;
	private boolean withdraw;
	
	public Book() {}
	
	public Book(int tickets, MovieSession movieSession, Long credit_card, User user, Date date, boolean withdraw) {
		this.tickets=tickets;
		this.movieSession=movieSession;
		this.credit_card=credit_card;
		this.user=user;
		this.date=date;
		this.withdraw=withdraw;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="sessionMovieId")
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
	
	@ManyToOne(optional=true, fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isWithdraw() {
		return withdraw;
	}

	public void setWithdraw(boolean withdraw) {
		this.withdraw = withdraw;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credit_card == null) ? 0 : credit_card.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((movieSession == null) ? 0 : movieSession.hashCode());
		result = prime * result + tickets;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + (withdraw ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (credit_card == null) {
			if (other.credit_card != null)
				return false;
		} else if (!credit_card.equals(other.credit_card))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (movieSession == null) {
			if (other.movieSession != null)
				return false;
		} else if (!movieSession.equals(other.movieSession))
			return false;
		if (tickets != other.tickets)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (withdraw != other.withdraw)
			return false;
		return true;
	}
	
	
}
