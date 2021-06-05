package com.rg.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rg.model.DailyTransactionClass;

public interface DailyTransactionClassRepository extends CrudRepository<DailyTransactionClass, Integer> {
	
@Query(value="select * from dailytransaction dt ,dailytransactionclass dtc where dt.id = dtc.dtid and STR_TO_DATE(date, '%Y-%m-%d') >= STR_TO_DATE(?,'%Y-%m-%d') and STR_TO_DATE(date, '%Y-%m-%d') <= STR_TO_DATE(?,'%Y-%m-%d')", nativeQuery=true)
public List<DailyTransactionClass>getWeeklyTransaction(String form,String to);


@Query(value="select dtc.* from dailytransaction dt ,dailytransactionclass dtc where dt.id = dtc.dtid  and dt.date=STR_TO_DATE(?,'%Y-%m-%d')", nativeQuery=true)
public DailyTransactionClass getTransactionByDate(String date);

@Modifying
@Query(value="delete FROM `test_db`.`dailytransactionclass`where dtid in (select id FROM `test_db`.`dailytransaction` where date = STR_TO_DATE(?,'%d-%m-%Y'))", nativeQuery=true)
public int deleteTransactionByDate(String date);


@Modifying
@Query(value="update `test_db`.`dailytransactionclass` set type = ? where dtid = (select id from `test_db`.`dailytransaction` where date =STR_TO_DATE(?,'%Y-%m-%d'))", nativeQuery=true)
public int updateTransactionByDate(String transactionclassarr,String date);
}



