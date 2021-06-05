package com.rg.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.rg.model.Admin;
import com.rg.model.Bank;
import com.rg.model.User;
import com.rg.services.BankServices;

@RestController
public class BankRestController {

	@Autowired
	BankServices bankservices;

	@PostMapping("/AddBank")
	String Addbank(@RequestParam("bank_name") String bankName, HttpServletRequest request) {
		try {

			HttpSession session = request.getSession();
			@SuppressWarnings("deprecation")
			Admin user = (Admin) session.getValue("user");
			if (user != null) {	
				Bank bank = new Bank(bankName);
				if (bankservices.AddBank(bank))
					return ("success");
				else
					return ("error");
			}
			else
			{
				return "session expired";
			}
		} catch (Exception jsonexception) {
			System.out.println("----------------------------JSON------------Error---------------------");
			jsonexception.printStackTrace();
			return (null);
		}

	}

	@GetMapping("bank/get")
	List<Bank> getAllBanks(HttpServletRequest request) {
		HttpSession session = request.getSession();
		@SuppressWarnings("deprecation")
		User user =  (User)session.getValue("user");
		if(user!=null)
			return bankservices.GetAllBanks();
		else
			return null;
	}
	
	@GetMapping("bank/UserNewBankget")
	List<Bank> getUserNewBank(HttpServletRequest request) {
		HttpSession session = request.getSession();
		@SuppressWarnings("deprecation")
		User user =  (User)session.getValue("user");
		if(user!=null)
			return bankservices.getUserNewBank(user.getUser_id());
		else
			return null;
	}
	
	
	@PostMapping("userbank/add")
	String Addbank(@RequestParam("bank_id")int bankid , HttpServletRequest request) {
		User user = getUserfromSession(request);
		if(user!=null) {
			return bankservices.addUserBank(user.getUser_id(),bankid);
		}
		else {
			return "Error";
		}
	}
	
	@GetMapping(value = "userbank/get", produces="application/json")
	String User_account_all(HttpServletRequest request) {
		User user = getUserfromSession(request);
		if(user!=null) {
			return bankservices.findallUserAccount(user.getUser_id());
		}
		else {
			return "Error";
		}
	}
	
	@SuppressWarnings("deprecation")
	public User getUserfromSession(HttpServletRequest request) {
		try {
		return (User)request.getSession().getValue("user");
		}
		catch(Exception e) {
			System.out.println(new Date());
			e.printStackTrace();
			return null;
		}
	}

}
