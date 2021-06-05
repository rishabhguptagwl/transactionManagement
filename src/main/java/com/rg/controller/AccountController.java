package com.rg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.rg.model.Admin;
import com.rg.services.UserService;

@Controller
//@SessionAttributes("user")

public class AccountController extends WebMvcConfigurerAdapter {

	@Autowired
	UserService userServices;

//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/main").setViewName("index");
//	}

	@GetMapping("/")
	public String defaultMap() {
		return "index";
	}

	@GetMapping("/account")
	public String home() {
		return "index";
	}

	@SuppressWarnings("deprecation")
	@GetMapping("/index")
	public String index(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin u = (Admin) session.getValue("user");
		if (u != null) {
			return "index";
		} else {
			return "admin";
		}
	}

	@GetMapping("/Dash")
	public String Dashborad() {
		return "BulkEntry";
	}

	@GetMapping("/user")
	public String User(HttpServletRequest request) {
		HttpSession session = request.getSession();
		@SuppressWarnings("deprecation")
		com.rg.model.User u = (com.rg.model.User) session.getValue("user");
		if (u != null)
			return "userHome";
		else
			return "Login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		return "Login";
	}

	@GetMapping("/bank")
	public String bank(HttpServletRequest request) {
		HttpSession session = request.getSession();
		@SuppressWarnings("deprecation")
		Admin u = (Admin) session.getValue("user");
		if (u != null)
			return "bank";
		else
			return "admin";
	}

//	@GetMapping("/")
//	public String login() {
//		return "Login";
//	}

	@GetMapping("/main")
	public String Admin() {
		return "index";
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".html");
		return resolver;
	}

}
