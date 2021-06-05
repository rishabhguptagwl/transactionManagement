package com.rg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="income")
public class Income {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int month;
	int year;
	int userid;
	long income;
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
	public long getIncome() {
		return income;
	}
	@Override
	public String toString() {
		return "Income [id=" + id + ", month=" + month + ", year=" + year + ", userid=" + userid + ", income=" + income
				+ "]";
	}
	
	
	public Income() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Income(int id, int month, int year, int userid, long income) {
		super();
		this.id = id;
		this.month = month;
		this.year = year;
		this.userid = userid;
		this.income = income;
	}
	public void setIncome(long income) {
		this.income = income;
	}
	
	
	
	
}
