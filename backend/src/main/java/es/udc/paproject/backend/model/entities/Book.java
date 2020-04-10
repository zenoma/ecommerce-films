package es.udc.paproject.backend.model.entities;

import java.time.LocalDateTime;

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
    private String creditcard;
    private User user;
    private LocalDateTime date;
    private boolean withdraw;

    public Book() {
    }

    public Book(int tickets, MovieSession movieSession, String creditcard, User user, LocalDateTime date,
	    boolean withdraw) {
		this.tickets = tickets;
		this.movieSession = movieSession;
		this.creditcard = creditcard;
		this.user = user;
		this.date = date;
		this.withdraw = withdraw;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "movieSessionId")
    public MovieSession getMovieSession() {
    	return movieSession;
    }

    public void setMovieSession(MovieSession movieSession) {
    	this.movieSession = movieSession;
    }

    public String getCreditcard() {
    	return creditcard;
    }

    public void setCreditcard(String creditcard) {
    	this.creditcard = creditcard;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
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
