package com.rg.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rg.model.DailyTransaction;

public interface DailyTransactionRepository extends CrudRepository<DailyTransaction,Integer>{

	
	@Query(value="SELECT id,DATE_FORMAT(STR_TO_DATE(date, '%Y-%m-%d'),\"%d-%m-%Y\") as \"date\", transaction FROM dailytransaction a" , nativeQuery=true)
	public List<DailyTransaction> getAlltransaction();
	
	@Query(value="SELECT id,DATE_FORMAT(STR_TO_DATE(date, '%Y-%m-%d'),\"%d-%m-%Y\") as \"date\", transaction FROM dailytransaction a where STR_TO_DATE(date, '%Y-%m-%d') >= STR_TO_DATE(?,'%Y-%m-%d') and STR_TO_DATE(date, '%Y-%m-%d') <= STR_TO_DATE(?,'%Y-%m-%d')", nativeQuery=true)
	public List<DailyTransaction> getTransactionByDate(String form,String to);
	
	
	@Query(value="SELECT id, transaction FROM dailytransaction d where id not in (select dtid from dailytransactionclass)", nativeQuery=true)
	public List<Object[]> getUnclassTransaction();
	
	public DailyTransaction findByDate(String date);
	
	@Modifying
	@Query(value="delete FROM `test_db`.`dailytransaction` where date = STR_TO_DATE(:date,'%d-%m-%Y')", nativeQuery=true)
	public int deleteTransactionByDate(@Param("date") String date); 
	
	
	@Modifying
	@Query(value="update `test_db`.`dailytransaction` set transaction=? where date=STR_TO_DATE(?,'%Y-%m-%d')", nativeQuery=true)
	public int updateTransactionByDate(String transactionarr,String date); 

}
