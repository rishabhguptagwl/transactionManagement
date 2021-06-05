package com.rg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user_account")
public class UserBank {

	
	
	@Id
	@Column(name = "Account_no")
	private int Account_no;
	
	@Column(name="bank_id")
	private int bank_id;
	
	@Column(name="user_id")
	private int user_id;
	
	@Column(name="balance")
	private int balance;

	public UserBank(int bank_id, int user_id, int balance) {
		super();
		this.bank_id = bank_id;
		this.user_id = user_id;
		this.balance = balance;
	}

	public UserBank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAccount_no() {
		return Account_no;
	}

	public void setAccount_no(int account_no) {
		Account_no = account_no;
	}

	public int getBank_id() {
		return bank_id;
	}

	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "UserBank [Account_no=" + Account_no + ", bank_id=" + bank_id + ", user_id=" + user_id + ", balance="
				+ balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Account_no;
		result = prime * result + balance;
		result = prime * result + bank_id;
		result = prime * result + user_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBank other = (UserBank) obj;
		if (Account_no != other.Account_no)
			return false;
		if (balance != other.balance)
			return false;
		if (bank_id != other.bank_id)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}
	
	
	
	
	
	
}
