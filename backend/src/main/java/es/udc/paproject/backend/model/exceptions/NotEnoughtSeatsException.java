package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class NotEnoughtSeatsException extends Exception {

    private Long sessionId;
    private int seatsLeft;

    public NotEnoughtSeatsException(Long sessionId, int seatsLeft) {
    	this.sessionId = sessionId;
		this.seatsLeft = seatsLeft;
    }

    public final Long getSessionId() {
    	return sessionId;
    }

    public final int getSeatsLeft() {
    	return seatsLeft;
    }
}
