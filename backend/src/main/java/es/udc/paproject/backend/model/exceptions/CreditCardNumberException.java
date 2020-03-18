package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class CreditCardNumberException extends Exception{
	private Long creditCard;
	
	public CreditCardNumberException(Long creditCard) {
		this.creditCard=creditCard;
	}
	
	public final Long getCreditCard() {
		return creditCard;
	}
}
