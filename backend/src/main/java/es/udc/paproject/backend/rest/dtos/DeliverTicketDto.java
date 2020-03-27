package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;

public class DeliverTicketDto {
	private Long credit_card;
	private Long code;

	public DeliverTicketDto() {
	}

	public DeliverTicketDto(Long credit_card, Long code) {
		this.credit_card = credit_card;
		this.code = code;
	}

	@NotNull
	public Long getCredit_card() {
		return credit_card;
	}

	public void setCredit_card(Long credit_card) {
		this.credit_card = credit_card;
	}

	@NotNull
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

}
