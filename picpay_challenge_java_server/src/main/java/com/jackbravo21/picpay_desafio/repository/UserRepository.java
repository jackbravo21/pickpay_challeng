package com.jackbravo21.picpay_desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jackbravo21.picpay_desafio.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{

	int getUserById(Long id);
	int countByMail(String mail);
	int countByDocument(String document);
	int countByMailAndIdNot(String mail, Long id);
	int countByDocumentAndIdNot(String document, Long id);
}
