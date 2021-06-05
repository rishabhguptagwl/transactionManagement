package com.rg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "budget")
public class Budget {

	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int month;
	int year;
	int userid;
	long budget;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public long getBudget() {
		return budget;
	}
	public void setBudget(long budget) {
		this.budget = budget;
	}
	@Override
	public String toString() {
		return "Budget [id=" + id + ", month=" + month + ", year=" + year + ", userid=" + userid + ", budget=" + budget
				+ "]";
	}
	public Budget(int id, int month, int year, int userid, long budget) {
		super();
		this.id = id;
		this.month = month;
		this.year = year;
		this.userid = userid;
		this.budget = budget;
	}
	public Budget() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
