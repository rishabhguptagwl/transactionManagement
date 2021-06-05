package com.rg.controller;

//import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rg.model.Budget;
import com.rg.services.BudgetService;






@CrossOrigin(origins = "*") 
//@CrossOrigin(origins = "http://192.168.43.49:4200") // for local
//@CrossOrigin(origins = "http://localhost:8080") //for server
@RestController
public class BudgetController {

	@Autowired
	BudgetService budgetService;

	@RequestMapping(value = "/budget/getuserbudget", method=RequestMethod.POST)
	public Budget getBudgetByUserIDAndMonthAndYear(@RequestBody String req) {
		Budget budget = null; 
		try {
			JSONObject request = new JSONObject(req);
			int year = request.getInt("year");
			int month = request.getInt("month");
			int userid = 1;
			budget =  budgetService.getBudgetbyUserAndMonthAndYear(userid, month, year);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return budget;
		
	}
		
	@RequestMapping(value="/budget/savebudget", method=RequestMethod.POST)
	public Budget SaveBudget(@RequestBody String req) {
		try {
			JSONObject request = new JSONObject(req);
			int year = request.getInt("year");
			int month = request.getInt("month");
			int userid = 1;
			long budget = request.getLong("budget");
			Budget budgetObj = new Budget();
			budgetObj.setBudget(budget);
			budgetObj.setYear(year);
			budgetObj.setMonth(month);
			budgetObj.setUserid(userid);
			return budgetService.saveBudget(budgetObj);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
