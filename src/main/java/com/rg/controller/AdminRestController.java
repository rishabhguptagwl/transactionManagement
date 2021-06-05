package com.rg.controller;

import java.util.Date;
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

import com.rg.model.Admin;
import com.rg.services.AdminServices;

@RestController
public class AdminRestController {

	@Autowired
	AdminServices adminServices;

	@GetMapping("/Admin/get")
	public List<Admin> getAll() {
		return adminServices.getAll();
	}

	@GetMapping("Admin/add")
	public Admin Add(@RequestParam("name") String name, @RequestParam("username") String username,
			@RequestParam("password") String password, HttpServletRequest request) {
		Admin admin = new Admin(username, name, password, new Date().toString(), 0, true);
		return adminServices.save(admin);
	}

	@SuppressWarnings("deprecation")
	@PostMapping("/login")
	public String Auth(@RequestParam("username") String userName, @RequestParam("password") String password,
			HttpServletRequest request) {
		System.out.println(userName + "::" + password);
		Admin admin = adminServices.getUserNameByUserIDAndPassword(userName, password);
		System.out.println(admin);
		try {

			if (adminServices.GetLoginCount(userName) < 3) {
				if (admin != null) {
					adminServices.UpdateLastLogin(admin.getId());
					HttpSession session = request.getSession();
					session.putValue("user", admin);
					JSONObject j = new JSONObject();
					j.put("status", "success");
					adminServices.resetLoginCount(admin.getId());
					return j.toString();
				} else {
					adminServices.increaseLoginCount(userName);
					JSONObject j = new JSONObject();
					j.put("status", "invalid credentials");
					return j.toString();

				}
			}
			else
			{
				adminServices.increaseLoginCount(userName);
				JSONObject j = new JSONObject();
				j.put("status", "Locked");
				return j.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@GetMapping(value = "/admin/loginCount", produces = "application/Json")
	public String getLoginCount(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			@SuppressWarnings("deprecation")
			Admin admin = (Admin) session.getValue("user");
			if (admin != null) {
				int count = adminServices.GetLoginCount(admin.getUserName());
				JSONObject result = new JSONObject();
				result.put("result", "success");
				result.put("count", count);
				return result.toString();
			} else {
				JSONObject result = new JSONObject();
				result.put("result", "Session Expired");
				return result.toString();
			}
		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();
			return "JSON error";
		}
	}

}
