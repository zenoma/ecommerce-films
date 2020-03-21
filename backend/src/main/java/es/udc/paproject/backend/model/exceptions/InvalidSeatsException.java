package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class InvalidSeatsException extends Exception{
    private int seats;

    public InvalidSeatsException(int seats) {
		this.seats = seats;
    }

    public final int getSeats() {
    	return seats;
    }
}
