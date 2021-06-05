package com.rg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {

	
	@Id
	private int id;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="lastlogin")
	private String lastLogin;
	
	@Column(name="invalidlogin")
	private int invalidLogin;
	
	@Column(name="active")
	private Boolean active;

	public Admin() {
		super();
	}

	public Admin(String userName, String name, String password, String lastLogin, int invalidLogin, Boolean active) {
		super();
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.lastLogin = lastLogin;
		this.invalidLogin = invalidLogin;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getInvalidLogin() {
		return invalidLogin;
	}

	public void setInvalidLogin(int invalidLogin) {
		this.invalidLogin = invalidLogin;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", userName=" + userName + ", name=" + name + ", password=" + password
				+ ", lastLogin=" + lastLogin + ", invalidLogin=" + invalidLogin + ", active=" + active + "]";
	}
	
	
	
	
	
}
