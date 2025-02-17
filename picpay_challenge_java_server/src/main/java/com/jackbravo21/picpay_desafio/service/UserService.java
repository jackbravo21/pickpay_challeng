package com.jackbravo21.picpay_desafio.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jackbravo21.picpay_desafio.dto.UserDTO;
import com.jackbravo21.picpay_desafio.exceptions.CustomException;
import com.jackbravo21.picpay_desafio.model.UserModel;
import com.jackbravo21.picpay_desafio.repository.UserRepository;
import com.jackbravo21.picpay_desafio.util.EncryptionUtil;
import com.jackbravo21.picpay_desafio.util.UserValidations;


@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserModel register(UserDTO userDTO) {
		try 
		{			
			//userValidations.userValidation(userDTO);
						
			UserModel user = new UserModel();
			String document = userDTO.getDocument();
			String mail = userDTO.getMail();
			
			if(checkMail(mail) > 0) {
				 System.out.println("Error! Mail already registered!");
		         throw new CustomException(406, "Error! User already registered!");
			}
			
			if(checkDocument(document) > 0) {
				 System.out.println("Error! Document already registered!");
		         throw new CustomException(406, "Erro! Document already registered!");
			}
			
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setDocument(userDTO.getDocument());
			user.setMail(userDTO.getMail().toLowerCase());
			user.setPassword(EncryptionUtil.encryptPassword(userDTO.getPassword()));
			user.setBalance(userDTO.getBalance());
			user.setUserType(userDTO.getUserType());
			user.setTimestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
			
			UserModel saveUser = userRepository.save(user);
			return saveUser;
		}
		catch (Exception e) {
	        System.out.println("Error registering user: " + e.getMessage());
	        throw new CustomException(500, "Error registering user!");  
		}
	}
	
	public UserModel userEdit(UserDTO userDTO) {
		try{			
			//userValidations.userValidation(userDTO);
			
			Long id = userDTO.getId();
			String mail = userDTO.getMail();
			String document = userDTO.getDocument();
			
			int checkMail = userRepository.countByMailAndIdNot(mail, id);
			
			int checkDocument = userRepository.countByDocumentAndIdNot(document, id);
			
			if(checkMail > 0) {
				throw new CustomException(406, "Error! Already registered user!");
			}
			
			if(checkDocument > 0) {
				throw new CustomException(406, "Error! Document already registered!");
			}
			
	        UserModel user = userRepository.findById(id).orElseThrow(() -> 
            new CustomException(404, "User not found!")
	        );
	        	        
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setDocument(userDTO.getDocument());
			user.setMail(userDTO.getMail().toLowerCase());
			user.setPassword(EncryptionUtil.encryptPassword(userDTO.getPassword()));
			user.setUserType(userDTO.getUserType());
			
			UserModel saveUser = userRepository.save(user);
			return saveUser;
		}
		catch(Exception e){
			throw new CustomException(500, "Error editing user: " + e.getMessage());
		}
	}

	public String userDelete(Long id){
		try{
			if(!userRepository.existsById(id)) {
				throw new CustomException(404, "Error when deleting user: User not found!");
			}
	        userRepository.deleteById(id);
	        return "User "+ id +" Deleted Successfully!";
		}
		catch(Exception e){
			throw new CustomException(500, "Error deleting user: " + e.getMessage());
		}
	}
	
	public Optional<UserModel> getUser(Long id) {
		try{
			if(!userRepository.existsById(id)) {
				throw new CustomException(404, "Error when searching for user: User not found!");
			}			
			return userRepository.findById(id);
		} 
		catch(Exception e){
			throw new CustomException(500, "Error when searching for user: " + e.getMessage());
		}
	}

	public List<UserModel> getAllUsers()
	{
		try {
			List<UserModel> users = userRepository.findAll();
			return users;
		} 
		catch(Exception e){
			throw new CustomException(500, "Error when searching for users: " + e.getMessage());
		}
	}
	
	public int checkMail(String mail) {
		try{
			int response = userRepository.countByMail(mail);
			return response;
		}
		catch(Exception e){
			throw new CustomException(500, "Error when checking email: " + e.getMessage());
		}
	}
	
	public int checkDocument(String document) {
		try{
			int response = userRepository.countByDocument(document);
			return response;
		}
		catch(Exception e){
			throw new CustomException(500, "Erro ao checkar e-mail: " + e.getMessage());
		}
	}
}









