package com.rg.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rg.DAO.UserRepository;
import com.rg.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	public UserRepository userRespository;

	public boolean AddUser(User user) {
		try {
			if (user != null) {
				userRespository.save(user);
				return true;
			} else {
				System.out.println("user is null");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public List<User> GetAll(){
		try {
			List<User> result = new ArrayList<User>();
			for(User u  : userRespository.findAll()) {
				result.add(u);
			}
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public User getUserNameByUserIDAndPassword(String User_name,String password) {
		User u = userRespository.findUserByUser_nameAndUser_Password(User_name, password);
		return u;
	}
	

}
