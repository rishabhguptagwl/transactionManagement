package com.rg.DAO;

import org.springframework.data.repository.CrudRepository;

import com.rg.model.Budget;

public interface BudgetRepository extends CrudRepository<Budget, Integer>{
	
	
	public Budget findBudgetByYearAndMonthAndUserid(int year,int month,int userid);
}
