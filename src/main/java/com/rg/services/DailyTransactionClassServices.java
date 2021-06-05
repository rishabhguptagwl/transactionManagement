package com.rg.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rg.DAO.DailyTransactionClassRepository;
import com.rg.DAO.DailyTransactionRepository;
import com.rg.model.DailyTransactionClass;

@Service
@Transactional
public class DailyTransactionClassServices {
	
	@Autowired
	DailyTransactionClassRepository dailyTransactionClassRepository;
	
	@Autowired
	DailyTransactionRepository dailyTransactionRepository;
	
	
	public List<Object[]> getUnClassTransaction(){
		return dailyTransactionRepository.getUnclassTransaction();
	}
	
	public List<DailyTransactionClass> getDailyTranaction(){
		List<DailyTransactionClass> result = new ArrayList<DailyTransactionClass>();
		dailyTransactionClassRepository.findAll().forEach(i->result.add(i));
		return result;
	}
	
	
	public boolean saveTransaction(DailyTransactionClass dtc) {
		if(dailyTransactionClassRepository.save(dtc) != null)
			return true;
		else
			return false;
	}
	
	public List<DailyTransactionClass> getWeekTransaction(String form,String to){
		System.out.println("DailyTransactionClassService|getWeekTransaction|invoked");
		System.out.println("from"+form+"to"+to);
		return dailyTransactionClassRepository.getWeeklyTransaction(form, to);
	}
	
	public DailyTransactionClass getDailyTransactionByDate(String date) {
		try {
		System.out.println("Controller::"+date);
		DailyTransactionClass dtc =  dailyTransactionClassRepository.getTransactionByDate(date);
		System.out.println((dtc==null?"":dtc.toString())+"#################################");
		return dtc;
		}
		catch(Exception e) {
			System.out.println("Data access exception");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public int deleteTransactionByDate(String Date) {
		try {
		System.out.println("deleteing in date ->"+Date);
		return dailyTransactionClassRepository.deleteTransactionByDate(Date);
//		return 1;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateTransactionclass(String date,JSONArray transactionclassarr) {
		System.out.println(transactionclassarr);
		System.out.println(date);
		return dailyTransactionClassRepository.updateTransactionByDate(transactionclassarr.toString(), date);
	}
}
