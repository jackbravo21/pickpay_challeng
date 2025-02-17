package com.jackbravo21.picpay_desafio.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jackbravo21.picpay_desafio.dto.AtmDTO;
import com.jackbravo21.picpay_desafio.dto.AtmOperation;
import com.jackbravo21.picpay_desafio.dto.TransactionDTO;
import com.jackbravo21.picpay_desafio.dto.UserType;
import com.jackbravo21.picpay_desafio.exceptions.CustomException;
import com.jackbravo21.picpay_desafio.model.TransactionModel;
import com.jackbravo21.picpay_desafio.model.UserModel;
import com.jackbravo21.picpay_desafio.repository.TransactionRepository;
import com.jackbravo21.picpay_desafio.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	private UserRepository userRepository;
	private TransactionRepository transactionRepository;
	private NotificationService notificationService;
	private RestTemplate restTemplate;
	
	public TransactionService(
			UserRepository userRepository, 
			TransactionRepository transactionRepository, 
			NotificationService notificationService, 
			RestTemplate restTemplate) {
		this.userRepository = userRepository;
		this.transactionRepository = transactionRepository;
		this.notificationService = notificationService;
		this.restTemplate = restTemplate;
	}
	
	@Transactional
	public TransactionModel createTransaction(TransactionDTO transactionDTO) {

		UserModel sender = userRepository.findById(transactionDTO.getSenderID())
	            .orElseThrow(() -> new CustomException(404, "Sender user not found!"));
	    
	    UserModel receiver = userRepository.findById(transactionDTO.getReceiverID())
	            .orElseThrow(() -> new CustomException(404, "Recipient user not found"));
		
	    boolean isAuthorized = authorizeTransaction();   
		if(!isAuthorized) {
			throw new CustomException(406, "Unauthorized transaction!");
		}
		
	    validateTransaction(sender, transactionDTO.getValue());
	    processTransaction(sender, receiver, transactionDTO.getValue());
	    
	    TransactionModel transaction = new TransactionModel();
	    transaction.setSenderID(sender);
	    transaction.setReceiverID(receiver);
	    transaction.setValue(transactionDTO.getValue());
	    transaction.setTimestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
	    
	    transactionRepository.save(transaction);
	    
		notificationService.sendNotification(sender, "Payment transaction completed successfully!");
		notificationService.sendNotification(receiver, "Payment receipt transaction received successfully!");
		
		return transaction;	    
	}

	public void validateTransaction(UserModel sender, BigDecimal amount) {
		if(sender.getUserType() == UserType.MERCHANT) {
			throw new CustomException(406, "Logist type user is not authorized to perform the action!");
		}
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new CustomException(406, "Insufficient balance!");
		}	
	}
	
	private void processTransaction(UserModel sender, UserModel receiver, BigDecimal amount) {
		try{
			sender.setBalance(sender.getBalance().subtract(amount));
		    receiver.setBalance(receiver.getBalance().add(amount));

		    userRepository.save(sender);
		    userRepository.save(receiver);	
		}
		catch(Exception e){
			throw new CustomException(406, "Erro ao realizar a transferência!"  + e.getMessage());
		}		
	}
	
	public boolean authorizeTransaction(){
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("http://localhost:9001/api/authorize", Map.class);
			
		if(!(authorizationResponse.getStatusCode() == HttpStatus.OK)){
			throw new CustomException(500, "Checking service is down!");
		}
		
		@SuppressWarnings("unchecked")
		Map<String, Object> data = (Map<String, Object>)authorizationResponse.getBody().get("data");
				
		Boolean authorization = (Boolean) data.get("authorization");
		return authorization;
	}
	
	@Transactional
	public UserModel atmOperation(AtmDTO atmDTO){
		try {
			UserModel user = userRepository.findById(atmDTO.getId())
                    .orElseThrow(() -> new CustomException(404, "User not found!"));
			
		    if(AtmOperation.DEPOSIT == atmDTO.getOperation()) {
		        user.setBalance(user.getBalance().add(atmDTO.getValue()));
		    } 
		    
		    else if(AtmOperation.WITHDRAW == atmDTO.getOperation()) {
		        if(user.getBalance().compareTo(atmDTO.getValue()) < 0) {
		            throw new CustomException(400, "Insufficient balance for withdrawal!");
		        }
		        
				boolean isAuthorized = authorizeTransaction();
				if(!isAuthorized) {
					throw new CustomException(406, "Unauthorized transaction!");
				}
			    user.setBalance(user.getBalance().subtract(atmDTO.getValue()));
		    } 
		    
		    else{
		        throw new CustomException(400, "Invalid operation!");
		    }
		    
		    UserModel saveUser = userRepository.save(user);
		    
		    if(atmDTO.getOperation() == AtmOperation.DEPOSIT) {
		    	notificationService.sendNotification(user, "Deposit transaction received successfully!");
		    }
		    else if(atmDTO.getOperation() == AtmOperation.WITHDRAW) {
		    	notificationService.sendNotification(user, "Withdrawal transaction completed successfully!");	
		    }		

			return saveUser;
		}
		catch(Exception e){
			throw new CustomException(500, "Error when performing operation: " + e.getMessage());
		}
	}	
	
	public List<TransactionModel> getAllTransactions()
	{
		try {
			List<TransactionModel> transaction = transactionRepository.findAll();
			return transaction;
		} 
		catch(Exception e){
			throw new CustomException(500, "Error fetching transactions: " + e.getMessage());
		}
	}
}











