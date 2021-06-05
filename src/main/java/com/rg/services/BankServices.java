package com.rg.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rg.DAO.BankRepository;
import com.rg.DAO.UserBankRepository;
import com.rg.model.Bank;
import com.rg.model.UserBank;

@Service
@Transactional
public class BankServices {

	@Autowired
	BankRepository bankrepository;
	@Autowired
	UserBankRepository userBankRepository;

	public boolean AddBank(Bank bank) {
		if (bank != null) {
			System.out.println("---------------Saved-----Bank------------" + bankrepository.save(bank));
			return true;
		} else {
			return false;
		}
	}

	public List<Bank> GetAllBanks() {
		List<Bank> banks = new ArrayList<Bank>();
		bankrepository.findAll().forEach(i -> banks.add(i));
		return banks;
	}

	public String addUserBank(int user_id, int bankid) {
		try {
		UserBank ub = new UserBank(bankid, user_id, 0);
		userBankRepository.save(ub);
		JSONObject result = new JSONObject();
		result.put("status", "success");
		return result.toString();
		}
		catch(JSONException jsonException) {
			System.out.println("jsonexception");
			jsonException.printStackTrace();
			return "JSON error";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	
		
	}

	public String findallUserAccount(int userid) {
		try {
		List<Object[]> UserAccounts=  userBankRepository.getAllUserAccount(userid);
		JSONArray result = new JSONArray();
		for(Object[] obj :UserAccounts) {
			JSONObject object = new JSONObject();
			object.put("Bank_id", obj[1]);
			object.put("Bank_name", obj[0]);
			result.put(object);
		}
		return result.toString();
		}
		catch(JSONException jsonException)
		{
			System.out.println("JSONException");
			jsonException.printStackTrace();
			return "JSON error";
		}
		catch(Exception Exception)
		{
			System.out.println("JSONException");
			Exception.printStackTrace();
			return "error";
		}
	}

	public List<Bank> getUserNewBank(int user_id) {
		return bankrepository.getAllNewBankUser(user_id);
	}

}
