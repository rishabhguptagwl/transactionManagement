package com.rg.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rg.model.Transcation;

public interface TransactionRepository extends CrudRepository<Transcation, Integer>{
	
	@Query(value="SELECT t.* FROM `transaction` t,user_account ua,bank b where t.user_account = ua.Account_no and b.bank_id = ua.bank_id and ua.user_id=?",nativeQuery=true)
	public List<Transcation> getAllTransactions(int userid);
	
	

}
