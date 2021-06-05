package com.rg.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rg.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	
	
	
	
	@Query(value="SELECT * FROM User u WHERE u.user_account_name=?1 and u.user_password=?2" , nativeQuery=true)
	User findUserByUser_nameAndUser_Password(String user_name,String password);
}
