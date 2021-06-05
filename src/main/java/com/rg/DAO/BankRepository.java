package com.rg.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rg.model.Bank;

public interface BankRepository extends CrudRepository<Bank, Integer> {

	
	
	@Query(value = "select * from bank where bank_id not in (select bank_id from user_account where user_id=?)", nativeQuery=true)
	public List<Bank> getAllNewBankUser(int user_id);
	
	
	
}
