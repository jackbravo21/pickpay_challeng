package com.jackbravo21.picpay_desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jackbravo21.picpay_desafio.model.TransactionModel;

public interface TransactionRepository extends JpaRepository<TransactionModel, Long>{

	
}
