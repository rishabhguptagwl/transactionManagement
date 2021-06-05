package com.rg.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rg.model.DailyTransactionClass;
import com.rg.services.DailyTransactionClassServices;


@CrossOrigin(origins = "*") 
//@CrossOrigin(origins = "http://192.168.43.49:4200") // for local
//@CrossOrigin(origins = "http://localhost:8080") //for server
@RestController
public class DailyTransactionClassController {
	
	@Autowired
	DailyTransactionClassServices dailyTransactionClassServices;

	@GetMapping(value = "unclasstransaction", produces="application/json")
	public String getUnclassTransaction(){
		try {
			List<Object[]> obj = dailyTransactionClassServices.getUnClassTransaction();
			JSONArray transArray = new JSONArray();
			for(Object[] o: obj) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", o[0]);
				jsonObject.put("transaction",o[1]);
				transArray.put(jsonObject);
			}
			return new JSONObject().put("result", transArray).toString();
		}
		catch(Exception e) {
			return "\"result\":\"error\"";
		}	
	}
	
	@PostMapping(value = "unclass/save", produces = "application/json")
	public String addNewTransaction(@RequestBody String dt) {
		try {
		JSONObject js = new JSONObject(dt);
		JSONObject values = new JSONObject(js.getString("data"));
		int size = js.getInt("size");
		JSONArray dbValues = new JSONArray();
		for(int i=0;i<size;i++) {
			dbValues.put (new JSONObject().put("value", values.getInt("type"+i)));
		}
		DailyTransactionClass dtclass = new DailyTransactionClass();
		dtclass.setId(js.getInt("id"));
		dtclass.setType(dbValues.toString());
		return dailyTransactionClassServices.saveTransaction(dtclass)?"\"success\"":"\"error\"";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "{\"status\":\"error\"}";
		}
		
	}
}
