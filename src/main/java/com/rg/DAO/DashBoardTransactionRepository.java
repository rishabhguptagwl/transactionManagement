package com.rg.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rg.model.DashboardTransaction;

public interface DashBoardTransactionRepository extends CrudRepository<DashboardTransaction, Integer>{
	
	@Query(value="SELECT * FROM analyticstransaction a WHERE STR_TO_DATE(date, '%d-%m-%Y') >= STR_TO_DATE(?,'%d-%m-%Y') and STR_TO_DATE(date, '%d-%m-%Y') <= STR_TO_DATE(?,'%d-%m-%Y')", nativeQuery=true)
	public List<DashboardTransaction> getTransactionBetweenDates(String from ,String to);	
}
