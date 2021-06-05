package com.rg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="user")
public class User {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private String user_name;
	private String user_password;
	private String user_account_name;
	
	public User() {
	}
	
	
	public User(String user_name, String user_passsword, String user_account_name) {
		super();
		this.user_name = user_name;
		this.user_password = user_passsword;
		this.user_account_name = user_account_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_account_name() {
		return user_account_name;
	}
	public void setUser_account_name(String user_account_name) {
		this.user_account_name = user_account_name;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_passsword=" + user_password
				+ ", user_account_name=" + user_account_name + "]";
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	
	
	
	
}
