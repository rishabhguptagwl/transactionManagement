package com.rg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="analyticstransaction")
public class DashboardTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String date;
	long amount;
	long saving;
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
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
	
	public long getSave() {
		return saving;
	}
	public void setSave(long save) {
		this.saving = save;
	}
	
	@Override
	public String toString() {
		return "DashboardTransaction [id=" + id + ", date=" + date + ", amount=" + amount + ", save=" + saving + "]";
	}
	public DashboardTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DashboardTransaction(int id, String date, long amount, long save) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.saving = save;
	}
	
	
	
}
