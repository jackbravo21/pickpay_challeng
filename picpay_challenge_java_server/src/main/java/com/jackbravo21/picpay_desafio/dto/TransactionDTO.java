package com.jackbravo21.picpay_desafio.dto;

import java.math.BigDecimal;

public class TransactionDTO {

	private Long id;
	private BigDecimal value;
	private Long senderID;
	private Long receiverID;
	
	
	public TransactionDTO() {}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Long getSenderID() {
		return senderID;
	}

	public void setSenderID(Long senderID) {
		this.senderID = senderID;
	}

	public Long getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(Long receiverID) {
		this.receiverID = receiverID;
	}
		
}
