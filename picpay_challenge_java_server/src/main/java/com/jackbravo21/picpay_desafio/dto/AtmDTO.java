package com.jackbravo21.picpay_desafio.dto;

import java.math.BigDecimal;

public class AtmDTO {

	private Long id;
	private BigDecimal value;
	private AtmOperation operation;
	
	public AtmDTO() {}

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

	public AtmOperation getOperation() {
		return operation;
	}

	public void setOperation(AtmOperation operation) {
		this.operation = operation;
	}

	
	
}
