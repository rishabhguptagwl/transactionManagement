package com.rg.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rg.DAO.IncomeRepository;
import com.rg.model.Income;

@Service
@Transactional
public class IncomeService {

	
	@Autowired
	IncomeRepository incomeRepository;
	
	public Income getIncomeByMonthandYear(int month,int year,int userid) {
		return incomeRepository.findIncomeByYearAndMonthAndUserid(year, month, userid);
	}
	
	
	
	public Income SaveIncome(Income income) {
		return incomeRepository.save(income);
	}
	
	
	
}
