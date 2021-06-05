package com.rg.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rg.DAO.BudgetRepository;
import com.rg.model.Budget;

@Service
@Transactional
public class BudgetService {

	@Autowired
	BudgetRepository budgetRepository;
	
	
	public Budget getBudgetbyUserAndMonthAndYear(int userid,int month,int year) {
		return budgetRepository.findBudgetByYearAndMonthAndUserid(year, month, userid);
	}
	public Budget saveBudget(Budget budget) {
		return budgetRepository.save(budget);
	}	
}
