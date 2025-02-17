package com.jackbravo21.picpay_desafio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jackbravo21.picpay_desafio.dto.AtmDTO;
import com.jackbravo21.picpay_desafio.dto.TransactionDTO;
import com.jackbravo21.picpay_desafio.model.TransactionModel;
import com.jackbravo21.picpay_desafio.model.UserModel;
import com.jackbravo21.picpay_desafio.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@PostMapping("/make")
	public ResponseEntity<TransactionModel> createTransaction(@RequestBody TransactionDTO transactionDTO){
		TransactionModel newTransaction = transactionService.createTransaction(transactionDTO);
		return ResponseEntity.ok(newTransaction);
	}
	
	@PostMapping("/atm")
	public ResponseEntity<UserModel> atm(@RequestBody AtmDTO atmDTO){
		UserModel money = transactionService.atmOperation(atmDTO);
		return ResponseEntity.ok(money);
	}
	
	@GetMapping("/history")
	public ResponseEntity<List<TransactionModel>> getAll(){
		List<TransactionModel> transactions = transactionService.getAllTransactions();
		return ResponseEntity.ok(transactions);
	}
}
