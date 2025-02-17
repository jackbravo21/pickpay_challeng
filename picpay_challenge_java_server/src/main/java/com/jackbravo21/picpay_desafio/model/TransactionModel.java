package com.jackbravo21.picpay_desafio.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name="transactions")
@Table(name="transactions")
public class TransactionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal value;
	@ManyToOne
	@JoinColumn(name="sender_id")
	private UserModel senderID;
	@ManyToOne
	@JoinColumn(name="receiver_id")
	private UserModel receiverID;
	private LocalDateTime timestamp = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
	
	public TransactionModel() {}

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

	public UserModel getSenderID() {
		return senderID;
	}

	public void setSenderID(UserModel senderID) {
		this.senderID = senderID;
	}

	public UserModel getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(UserModel receiverID) {
		this.receiverID = receiverID;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
