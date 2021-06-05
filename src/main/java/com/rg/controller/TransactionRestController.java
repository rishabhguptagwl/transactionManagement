package com.rg.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rg.model.DashboardTransaction;
import com.rg.model.Transcation;
import com.rg.model.User;
import com.rg.services.DashBoardTransactionService;
import com.rg.services.TransactionService;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://192.168.43.49:4200") // for local
//@CrossOrigin(origins = "http://localhost:8080") //for server
public class TransactionRestController {

	@Autowired
	TransactionService transactionService;
	@Autowired
	DashBoardTransactionService DTservice;
	
	@PostMapping("transaction/add")
	public String add(@RequestParam("user_account") String uaid,@RequestParam("amount")String amount, @RequestParam("description")String description) {
		Transcation t = new Transcation(Integer.parseInt(uaid),Integer.parseInt(amount), description, new Date().toString());
		return transactionService.add(t).toString();
	}
	
	@GetMapping(value="transaction/get")
	public String get(HttpServletRequest request) {
		User u = getCurrentUser(request);
		if(u!=null) 
			return transactionService.get(u.getUser_id()).toString();
		else
			return "Error";
	}
	
	
	@SuppressWarnings("deprecation")
	public User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (User)session.getValue("user");
	}
	
	@GetMapping(value="transaction/getTransaction")
	public List<Transcation> getAllTransactions(HttpServletRequest request)
	{
		User u = getCurrentUser(request);
		
		List<Transcation> trans = transactionService.get(u.getUser_id());
//		for(Transcation t  : trans) {
//			Date d = new Date(t.getTime_stamp());
//			t.setTime_stamp(d.getHours()+":"+d.getMinutes()+":"+d.getSeconds()+" "+d.getDay()+"/"+d.getMonth()+"/"+d.getYear());
//		}
		return trans;
	}
	
	

	@GetMapping(value="dashboard/getAll")
	
	public List<DashboardTransaction> getAllDashboardTransaction(){
		return DTservice.getAllTransaction();
	}
	
	
	@PostMapping(value="dashboard/save")
	
	public boolean savedata(@RequestParam("data") String data) {
		return DTservice.addBulk(data);
	}
	
	@GetMapping(value="dashboard/getAll2", produces="application/json")
	
	public String getAllDashboardTransaction2(){
		try {
		return new JSONObject().put("Data",DTservice.getAllTransaction()).toString();
		}
		catch(JSONException e){
			return null;
		}
	}
	
	
	
	
	@PostMapping(value="dashboard/filter")
	public List<DashboardTransaction> transactionBrtweenDates(@RequestParam("from") String from,@RequestParam("to") String to ) {
		System.out.println("Transaction date :-------------"+from +"       "+to);
		if(from =="" || to=="") {
			return DTservice.getAllTransaction();
		}
		else
		{
		String data [] = from.split("-");
		from= data[2]+"-"+data[1]+"-"+data[0];
		data = to.split("-");
		to= data[2]+"-"+data[1]+"-"+data[0];
		System.out.println("Rest HIT"+from+" "+to);
		return DTservice.getTransactionsBrtweenDates(from, to);
		}
	}
	
	
	
	@PostMapping(value="dashboard/saveSingleRest")
	public boolean saveTransaction(@RequestBody DashboardTransaction trans) {
		String tdate[] = trans.getDate().split("-");
		trans.setDate(tdate[2]+"-"+tdate[1]+"-"+tdate[0]);
		return DTservice.save(trans);
	}
	
	
	@PostMapping(value="dashboard/getTransactionBetweenDates", produces="application/json")
	public List<DashboardTransaction> getTransactionBetweenDate(@RequestBody String req) {
		try {
			JSONObject j = new JSONObject(req);
			String from = j.getString("from");
			String to = j.getString("to");
			String data [] = from.split("-");
			from= data[2]+"-"+data[1]+"-"+data[0];
			data = to.split("-");
			to= data[2]+"-"+data[1]+"-"+data[0];
			System.out.println("Rest HIT"+from+" "+to);
			return DTservice.getTransactionsBrtweenDates(from, to);
		}
		catch(Exception e) {
			System.out.println("JSONError");
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
	@PostMapping(value="dashboard/saveSingle")
	public boolean saveTransaction(@RequestParam("date") String date,@RequestParam("amount") int amount, @RequestParam("saving") int saving) {
		DashboardTransaction trans = new DashboardTransaction();
		String tdate[] = date.split("-");
		date= tdate[2]+"-"+tdate[1]+"-"+tdate[0];
		trans.setDate(date);
		trans.setAmount(amount);
		trans.setSave(saving);
		return DTservice.save(trans);
	}
	
	
	
	
	@GetMapping(value="update", produces="application/json")
	public String update(){
		DTservice.updateTrans();
		return "{\"status\":\"success\"}";
	}
	
	
}
