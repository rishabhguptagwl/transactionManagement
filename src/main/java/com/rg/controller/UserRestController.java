package com.rg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rg.model.User;
import com.rg.services.UserService;

@RestController
public class UserRestController {

	@Autowired
	UserService userService;

	@PostMapping("/Adduser")
	public String adduser(@RequestParam("username") String username, @RequestParam("password") String userpassword,
			@RequestParam("userAccountName") String userAccountName) {
		System.out.println("---------------Add USER Called------------------");
		User user = new User(username, userpassword, userAccountName);
		System.out.println("---------------" + user.toString() + "------------------");
		if (userService.AddUser(user)) {
			return "true";
		} else {
			return "false";
		}
	}

	@GetMapping("/getAll")
	public List<User> getAll() {
		return (userService.GetAll());
	}
	
	
	@SuppressWarnings("deprecation")
	@PostMapping("/user/login")
	public String AuthUser(@RequestParam("username")String username, @RequestParam("password")String password, HttpServletRequest request) {
		
		try {
			System.out.print("User Login Called ");
			JSONObject result = new JSONObject();
			User u = userService.getUserNameByUserIDAndPassword(username, password);
			if(u!=null) {
				request.getSession().putValue("user", u);
				result.put("status", "success");
				result.put("msg", "Login Success");
				return result.toString();
			}
			else
			{
				result.put("status", "success");
				result.put("msg", "Auth error");
				return result.toString();
			}	
		}
		catch(JSONException jsonException ) {
			jsonException.printStackTrace();
			return"json error";
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@GetMapping(value="user/out" , produces="application/json")
	public String out(HttpServletRequest request) {
		HttpSession session =request.getSession();
		if(session.getValue("user")!=null) {
		session.invalidate();
		}
		return "success";
		
		
		
	}
	
	
	
}
