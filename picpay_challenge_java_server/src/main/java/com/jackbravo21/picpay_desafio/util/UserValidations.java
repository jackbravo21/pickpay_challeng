package com.jackbravo21.picpay_desafio.util;

import com.jackbravo21.picpay_desafio.dto.UserDTO;
import com.jackbravo21.picpay_desafio.exceptions.CustomException;

public class UserValidations {

	public UserValidations(){}	
	
	public void userValidation(UserDTO userDTO) {
			
		String firstName = userDTO.getFirstName();
		String lastName = userDTO.getLastName();
		String document = userDTO.getDocument();
		String mail = userDTO.getMail();
		String password = userDTO.getPassword();
				
		if(firstName == null || firstName.trim().isEmpty()){
			throw new CustomException(406, "Error! Fill in the field First Name!");
		}
		
		if(lastName == null || lastName.trim().isEmpty()){
			throw new CustomException(406, "Error! Fill in the field Last Name!");
		}
		
		if(document == null || document.trim().isEmpty()){
			throw new CustomException(406, "Error! Fill in the DOCUMENT field!");
	    }		
		
		if (mail == null || mail.trim().isEmpty()) {
			throw new CustomException(406, "Error! Fill in the MAIL field!");
		}
		
	    if(!mail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
	    	throw new CustomException(406, "Error! Invalid MAIL format!");
	    }
	    
	    if(password.length() < 5){
	    	throw new CustomException(406, "Error! Password must be 5 characters or longer!");
	    }
	}	
}
