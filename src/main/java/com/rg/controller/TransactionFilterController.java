package com.rg.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@CrossOrigin(origins="*")
@RestController
public class TransactionFilterController {

	
	
	@RequestMapping(value = "/transaction/filter", method=RequestMethod.GET)
	public void getAllFilterTransaction() {
		
		
	}
	
	
	
}
