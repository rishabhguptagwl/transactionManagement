package com.rg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashBoardController {

	
	@GetMapping("/stock")
	public String getdata() {
		return "[\r\n" + 
				"          {\"year\":\"2013-01\",\"value\":53},\r\n" + 
				"          {\"year\":\"2013-02\",\"value\":165},\r\n" + 
				"          {\"year\":\"2013-03\",\"value\":269},\r\n" + 
				"          {\"year\":\"2013-04\",\"value\":344},\r\n" + 
				"          {\"year\":\"2013-05\",\"value\":376},\r\n" + 
				"          {\"year\":\"2013-06\",\"value\":410},\r\n" + 
				"          {\"year\":\"2013-07\",\"value\":421},\r\n" + 
				"          {\"year\":\"2013-08\",\"value\":405},\r\n" + 
				"          {\"year\":\"2013-09\",\"value\":376},\r\n" + 
				"          {\"year\":\"2013-10\",\"value\":359},\r\n" + 
				"          {\"year\":\"2013-11\",\"value\":392},\r\n" + 
				"          {\"year\":\"2013-12\",\"value\":433},\r\n" + 
				"          {\"year\":\"2013-13\",\"value\":455},\r\n" + 
				"          {\"year\":\"2013-14\",\"value\":479}] ";
	}
	
	
}
