package com.rg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="dailytransactionclass")
public class DailyTransactionClass {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dtid")
	int id;
	String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "DailyTransactionClass [id=" + id + ", type=" + type + "]";
	}
	public DailyTransactionClass(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	public DailyTransactionClass() {
		super();
		// TODO Auto-generated constructor stub
	}
}
