package com.rg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController


@SessionAttributes("user")
public class AccountRestController {

	@GetMapping("/hello")
	public String hello() {
		return ("hello");
	}
}
