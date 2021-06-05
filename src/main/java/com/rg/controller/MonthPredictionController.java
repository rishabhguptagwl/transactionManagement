package com.rg.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value="*")
public class MonthPredictionController {
	
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/month/daysclassfcation", method=RequestMethod.POST , produces="application/json")
	public String MonthClassfiction(@RequestBody String body) {
		
		
		try {
		JSONObject request = new JSONObject(body);
		
		int year = request.getInt("year");
		int month = request.getInt("month");
		SimpleDateFormat dateformat = new SimpleDateFormat("DD-MM-YYYY");
		System.out.println(month+"-"+year);
		System.out.println("1-"+month+"-"+year);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(year, month);

		date.setDate(1);
		date.setMonth(month);
		date.setYear(year);
		System.out.println(date);
		JSONObject result = new JSONObject();
		result.put("date", date.toGMTString());
		return result.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
	
	

}
