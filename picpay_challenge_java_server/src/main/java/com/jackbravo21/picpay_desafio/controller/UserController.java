package com.jackbravo21.picpay_desafio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jackbravo21.picpay_desafio.dto.UserDTO;
import com.jackbravo21.picpay_desafio.model.UserModel;
import com.jackbravo21.picpay_desafio.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<UserModel> register(@RequestBody UserDTO userDTO){
		UserModel saveUser = userService.register(userDTO);
		return ResponseEntity.ok(saveUser);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<UserModel> editUser(@RequestBody UserDTO userDTO){
		UserModel editUser = userService.userEdit(userDTO);
		return ResponseEntity.ok(editUser);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		String deleteUser = userService.userDelete(id);
		return ResponseEntity.ok(deleteUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<UserModel>> getUser(@PathVariable Long id){
		Optional<UserModel> user = userService.getUser(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserModel>> getFetchAll(){
		List<UserModel> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}	
}














