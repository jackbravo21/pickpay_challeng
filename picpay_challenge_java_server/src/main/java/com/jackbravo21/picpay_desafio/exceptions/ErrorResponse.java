package com.jackbravo21.picpay_desafio.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {

	private int status;
	private String message;
	private LocalDateTime dateTime;
	
	
	public ErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
		this.dateTime = LocalDateTime.now();
	}
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
		
}
