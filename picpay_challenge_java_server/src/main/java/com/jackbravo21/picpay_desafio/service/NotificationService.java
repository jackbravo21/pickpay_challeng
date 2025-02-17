package com.jackbravo21.picpay_desafio.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jackbravo21.picpay_desafio.dto.NotificationDTO;
import com.jackbravo21.picpay_desafio.exceptions.CustomException;
import com.jackbravo21.picpay_desafio.model.UserModel;

@Service
public class NotificationService {

	private RestTemplate restTemplate;
	
	public NotificationService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public void sendNotification(UserModel userModel, String message){
		String mail = userModel.getMail();
		
		NotificationDTO notificationRequest = new NotificationDTO(mail, message);
		
		ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://localhost:9001/api/notify", notificationRequest, String.class);

		if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
			throw new CustomException(500, "SNotification service is down!");
		}
	}	
}
