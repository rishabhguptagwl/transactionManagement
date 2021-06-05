package com.rg.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rg.model.UserBank;

public interface UserBankRepository extends CrudRepository<UserBank, Integer> {
	@Query(value="SELECT b.bank_name,ua.Account_no FROM `test_db`.`user_account` ua ,bank b where b.bank_id=ua.bank_id and user_id=?",nativeQuery=true)
	public List<Object[]> getAllUserAccount(int userid);
	
	
}
