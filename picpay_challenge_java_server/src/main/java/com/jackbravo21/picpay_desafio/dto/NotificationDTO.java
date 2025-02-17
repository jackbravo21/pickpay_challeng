package com.jackbravo21.picpay_desafio.dto;

public class NotificationDTO {

	String mail;
	String message;
	
	public NotificationDTO(String mail, String message) {
		this.mail = mail;
		this.message = message;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}


