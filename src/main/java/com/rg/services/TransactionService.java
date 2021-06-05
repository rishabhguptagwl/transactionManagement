package com.rg.services;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rg.DAO.TransactionRepository;
import com.rg.model.Transcation;

@Transactional
@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	public Transcation add(Transcation t) {
		return transactionRepository.save(t);
	}
	
	public Transcation addcr(Transcation t) {
		return transactionRepository.save(t);
	}
	

	public List<Transcation> get(int userid) {
		try {
			List<Transcation> allTransaction = transactionRepository.getAllTransactions(userid);
			return allTransaction;
		} catch (Exception exception) {
			System.out.println("Exception");
			return null;
		}
	}

}
