package com.rg.services;

import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONObject;
//import org.json.JSONArray;
//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rg.DAO.DailyTransactionRepository;
import com.rg.model.DailyTransaction;

@Transactional
@Service
public class DailyTransactionServices {

	@Autowired
	DailyTransactionRepository dailyTransactionRepository;

	public List<DailyTransaction> getAllTransaction() {
		return dailyTransactionRepository.getAlltransaction();
	}

	public DailyTransaction saveTransaction(DailyTransaction dt) {
		return dailyTransactionRepository.save(dt);
	}

	public List<DailyTransaction> DailyTransactionByDate(String fromdate, String toDate) {
		List<DailyTransaction> lst = dailyTransactionRepository.getTransactionByDate(fromdate, toDate);
		return lst;
	}
	
	public DailyTransaction getTransactionByDate(String formdate) {
		System.out.println("DailyTransactionService| getTransactionByDate|"+formdate);
		return dailyTransactionRepository.findByDate(formdate);
		
	}
		
	
	public List<DailyTransaction> getMonthTillDateTransaction() {
		LocalDate d = LocalDate.now();
		String crrdate = d.toString();
		String crrdatearr[] = crrdate.split("-");
		String startDate = crrdatearr[0] + "-" + crrdatearr[1] + "-" + 01;
		System.out.println(crrdate + " " + startDate);
		return (dailyTransactionRepository.getTransactionByDate(startDate, crrdate));
	}
	
	
	public int deleteTransactionbyDate(String date) {
		try {
		System.out.println("deleteing in date ->"+date);
		return dailyTransactionRepository.deleteTransactionByDate(date);
//		return 1;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public int updateTransaction(String date,JSONObject transactionarr) {
		System.out.println(transactionarr);
		System.out.println(date);
		return dailyTransactionRepository.updateTransactionByDate(transactionarr.toString(),date);
	}
	
	
	
	

	

}
