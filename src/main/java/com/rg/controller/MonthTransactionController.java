package com.rg.controller;


import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rg.common.CommonConstants;
import com.rg.model.DailyTransaction;
import com.rg.model.DailyTransactionClass;
import com.rg.services.DailyTransactionClassServices;
import com.rg.services.DailyTransactionServices;

@CrossOrigin(origins = "*") // for local
//@CrossOrigin(origins = "http://192.168.43.49:4200") // for local
//@CrossOrigin(origins = "http://localhost:8080") //for server
@RestController
public class MonthTransactionController {
	@Autowired
	DailyTransactionServices dailyTransactionServices;
	
	@Autowired
	DailyTransactionClassServices dailyTransactionClassServices;
	
	@PostMapping(value="monthlytransaction")
	public String GetMonthTransaction(@RequestBody String role) {
		System.out.println("hero:-"+role);
		return "{\"status\":\"success\"}";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="dailytransaction/transactionMonthly")
	public String getTransactionByMonthWeekWise(@RequestBody String requestbody) {
		String response = null;
		try {
			JSONObject request = new JSONObject(requestbody);
			System.out.println("Request body"+ request.toString());
			String formdate = "01";
			String month = request.getString("month");
			String year = request.getString("year");
			System.out.println(month+":"+year);
			Date d = new Date();
			d.setDate(Integer.parseInt(formdate));
			d.setMonth(Integer.parseInt(month));
			d.setYear(Integer.parseInt(year));
			String toDate = CommonConstants.getDaysInMonths(d.getMonth(), d.getYear())+"";			
			System.out.println("formDate"+formdate+":"+"toDate"+toDate);
			String StartDate = d.getYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+formdate;
			String endDate = d.getYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+toDate;
			
			List<DailyTransaction> dailyTransaction = dailyTransactionServices.DailyTransactionByDate(StartDate,endDate);
			List<DailyTransactionClass> dailyTransactionclass =  dailyTransactionClassServices.getWeekTransaction(StartDate,endDate);
			System.out.println("MonthlyTransaction"+dailyTransaction.toString());
			JSONObject result = new JSONObject();
			JSONArray resultArr = new JSONArray();
			System.out.println(dailyTransaction.size());
			System.out.println(dailyTransactionclass.size());
			
			for(int i=0;i<dailyTransaction.size();i++) {
				System.out.println("index going to access->"+i);
				JSONArray arrResultDailyTransactions = new JSONArray();
				JSONArray transactions = (new JSONObject(dailyTransaction.get(i).getTransaction()).getJSONArray("transactions"));
				JSONArray transactionclass = (new JSONArray(dailyTransactionclass.get(i).getType()));
				
				for(int j1=0;j1<transactions.length();j1++) {
					JSONObject resultDailyTransactions = new JSONObject();
					JSONObject transaction = new JSONObject(transactions.get(j1).toString());
					JSONObject SingletransactionClass = new JSONObject(transactionclass.get(j1).toString());
					resultDailyTransactions.put("amount", transaction.getInt("amount"));
					resultDailyTransactions.put("name", transaction.getString("name"));
					resultDailyTransactions.put("value", SingletransactionClass.getInt("value")== CommonConstants.ESSESTIALS?"Essentials":"Life Style");
					arrResultDailyTransactions.put(resultDailyTransactions);
				}
				JSONObject oneDayTransaction = new JSONObject();
				oneDayTransaction.put("date",dailyTransaction.get(i).getDate());
				oneDayTransaction.put("Transactions",arrResultDailyTransactions);
				resultArr.put(oneDayTransaction);
			}
			result.put("transaction", resultArr);
			System.out.println(resultArr.toString());
			response = result.toString();
			System.out.println(response);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="dailytransaction/transactionmonthlystats")
	public String getMonthlyTransactionStats(@RequestBody String requestbody) {
		String result=null;
		long min=100000,max=0;
		String minDate = "",maxDate="";
		try {
			JSONObject request = new JSONObject(requestbody);
			System.out.println("Request body"+ request.toString());
			String formdate = "01";
			String month = request.getString("month");
			String year = request.getString("year");
			System.out.println(month+":"+year);
			Date d = new Date();
			d.setDate(Integer.parseInt(formdate));
			d.setMonth(Integer.parseInt(month));
			d.setYear(Integer.parseInt(year));
			String toDate = CommonConstants.getDaysInMonths(d.getMonth(), d.getYear())+"";			
			System.out.println("formDate"+formdate+":"+"toDate"+toDate);
			String StartDate = d.getYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+formdate;
			String endDate = d.getYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+toDate;
			List<DailyTransaction> dailyTransaction = dailyTransactionServices.DailyTransactionByDate(StartDate,endDate);
			for(DailyTransaction dt :dailyTransaction) {
				JSONObject transactions = new JSONObject(dt.getTransaction());
				JSONArray dayTransaction = transactions.getJSONArray("transactions");
				long dayTransactionTotal=0;
				for(int i=0;i<dayTransaction.length();i++) {
					JSONObject indivisualDayTransaction = dayTransaction.getJSONObject(i);
					dayTransactionTotal+= indivisualDayTransaction.getLong("amount");
				}
				if(max<dayTransactionTotal) {
					max = dayTransactionTotal;
					maxDate = dt.getDate(); 
				}
				
				if(min>dayTransactionTotal) {
					min = dayTransactionTotal;
					minDate = dt.getDate();
				}
			}
			System.out.println("min:"+min+" mindate:"+minDate);
			System.out.println("max:"+max+" maxdate:"+maxDate);
			JSONObject resultJSON = new JSONObject();
			resultJSON.put("minimum_trans", min);
			resultJSON.put("minimum_trans_date", minDate);
			resultJSON.put("maximum_trans", max);
			resultJSON.put("maximum_trans_date", maxDate);
			result = resultJSON.toString();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
