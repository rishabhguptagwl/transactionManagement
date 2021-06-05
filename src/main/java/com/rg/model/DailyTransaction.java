package com.rg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dailytransaction")
public class DailyTransaction {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String date;
	public String transaction;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	@Override
	public String toString() {
		return "DailyTransaction [id=" + id + ", date=" + date + ", transaction=" + transaction + "]";
	}
	public DailyTransaction( String date, String transaction) {
		super();
		this.date = date;
		this.transaction = transaction;
	}
	public DailyTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
}
