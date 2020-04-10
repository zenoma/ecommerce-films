package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class DeliverTicketDto {
	private String creditcard;
	private Long code;

	public DeliverTicketDto() {
	}

	public DeliverTicketDto(String creditcard, Long code) {
		this.creditcard = creditcard;
		this.code = code;
	}

	@NotNull
	@Length(min=16,max=16)
	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	@NotNull
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

}
