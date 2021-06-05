package com.rg.DAO;

import org.springframework.data.repository.CrudRepository;

import com.rg.model.Income;

public interface IncomeRepository extends CrudRepository<Income, Integer> {
	
	
	public Income findIncomeByYearAndMonthAndUserid(int year,int month,int userid);
}	
