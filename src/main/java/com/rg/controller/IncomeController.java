package com.rg.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rg.model.Income;
import com.rg.services.IncomeService;
@CrossOrigin(origins = "*") 
//@CrossOrigin(origins = "http://192.168.43.49:4200") // for local
//@CrossOrigin(origins = "http://localhost:8080") //for server
@RestController
public class IncomeController {

	@Autowired
	IncomeService incomeService;
	
	@RequestMapping(value = "/income/getuserincome", method=RequestMethod.POST)
	public Income getIncomeByUserIdAndMonthAndYear(@RequestBody String req) {
		try {
			JSONObject request = new JSONObject(req);
			System.out.println("IncomeController|getIncomeByUserIdAndMonthAndYear|Request object" + req);
			int month = request.getInt("month");
			int year = request.getInt("year");
			System.out.println("IncomeController|getIncomeByUserIdAndMonthAndYear|Request object "+month+":"+year);
			return incomeService.getIncomeByMonthandYear(month, year, 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping(method=RequestMethod.POST, value="/income/saveincome")
	public Income saveIncome(@RequestBody String req) {
		try {
			JSONObject request = new JSONObject(req);
			int month = request.getInt("month");
			int year = request.getInt("year");
			long income = request.getLong("income");
			int userId  = 1;
			Income monthIncome = new Income();
			monthIncome.setIncome(income);
			monthIncome.setMonth(month);
			monthIncome.setYear(year);
			monthIncome.setUserid(userId);
			System.out.println("IncomeController|SaveIncome|"+monthIncome.toString());
			return incomeService.SaveIncome(monthIncome);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping(method=RequestMethod.POST, value="/income/updateincome")
	public Income updateIncome(@RequestBody String req) {
		try {
			JSONObject request = new JSONObject(req);
			int month = request.getInt("month");
			int year = request.getInt("year");
			long income = request.getLong("income");
			int userId  = 1;
			Income monthIncome = incomeService.getIncomeByMonthandYear(month, year, userId);
			System.out.println("IncomeController|SaveIncome| before update income |"+monthIncome.toString());
			monthIncome.setIncome(income);
			System.out.println("IncomeController|SaveIncome| "+monthIncome.toString());
			return incomeService.SaveIncome(monthIncome);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
