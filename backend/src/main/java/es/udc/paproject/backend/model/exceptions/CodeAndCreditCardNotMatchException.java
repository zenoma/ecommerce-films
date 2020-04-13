package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class CodeAndCreditCardNotMatchException extends Exception {

    private Long code;
    private String creditCard;

    public CodeAndCreditCardNotMatchException(Long code, String creditCard) {
		this.code = code;
		this.creditCard = creditCard;
    }

    public final Long getCode() {
    	return code;
    }

    public final String getCreditCard() {
    	return creditCard;
    }
}
