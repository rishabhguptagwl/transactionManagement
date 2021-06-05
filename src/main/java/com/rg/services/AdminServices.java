package com.rg.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestParam;

import com.rg.DAO.AdminRepository;
import com.rg.model.Admin;

@Service
@Transactional
public class AdminServices {

	
	@Autowired
	AdminRepository adminRepository;
	
	public void UpdateLastLogin(int id) {
		Date d = new Date();
		adminRepository.updateLastLogin(d.toString(), id);
	}
	public int GetLoginCount(String user) {
		String result = adminRepository.getLoginCount(user);
		if(result!=null)
			return Integer.parseInt(result);
		else
			return -1;
	}
	public String increaseLoginCount(String user) {
		adminRepository.incrementLoginCount(user);
		return "success";
	}
	public String resetLoginCount(int user) {
		adminRepository.resetLoginCount(user);
		return "success";
	}
	public List<Admin> getAll(){
		List<Admin> admins = new ArrayList<Admin>();
		adminRepository.findAll().forEach(i->admins.add(i));
		return admins;
	}
	public Admin save(Admin admin) {
		return adminRepository.save(admin);
	}
	public Admin getUserNameByUserIDAndPassword(String userName, String password) {
		return adminRepository.LoginAdmin(userName, password);
		
	}
	
	
	
	
	
	
	
}
