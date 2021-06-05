package com.rg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="bank")
public class Bank {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bank_id;
	private String bank_name;
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bank(String bank_name) {
		super();
		this.bank_name = bank_name;
	}
	public int getId() {
		return bank_id;
	}
	public void setId(int id) {
		this.bank_id = id;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	@Override
	public String toString() {
		return "Bank [id=" + bank_id + ", bank_name=" + bank_name + "]";
	}
	
	
	
}
