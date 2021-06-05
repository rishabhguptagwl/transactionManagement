package com.rg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="transaction")
public class Transcation {

	@Id
	@Column(name="t_id")
	private int transaction_id;
	
	@Column(name="user_account")
	private int user_account;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="description")
	private String description;
	
	@Column(name = "time_stamp")
	private String time_stamp;

	public Transcation(int user_account, int amount, String description, String time_stamp) {
		super();
		this.user_account = user_account;
		this.amount = amount;
		this.description = description;
		this.time_stamp = time_stamp;
	}

	public Transcation() {
		super();
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getUser_account() {
		return user_account;
	}

	public void setUser_account(int user_account) {
		this.user_account = user_account;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((time_stamp == null) ? 0 : time_stamp.hashCode());
		result = prime * result + transaction_id;
		result = prime * result + user_account;
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
		Transcation other = (Transcation) obj;
		if (amount != other.amount)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (time_stamp == null) {
			if (other.time_stamp != null)
				return false;
		} else if (!time_stamp.equals(other.time_stamp))
			return false;
		if (transaction_id != other.transaction_id)
			return false;
		if (user_account != other.user_account)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transcation [transaction_id=" + transaction_id + ", user_account=" + user_account + ", amount=" + amount
				+ ", description=" + description + ", time_stamp=" + time_stamp + "]";
	}
	
	
	
	
	
	
}
