package com.rg.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rg.DAO.DashBoardTransactionRepository;
import com.rg.model.DashboardTransaction;
@Service
@Transactional
public class DashBoardTransactionService {

	
	@Autowired
	DashBoardTransactionRepository DTrepo;
	
	
	public List<DashboardTransaction> getAllTransaction(){
//		List<DashboardTransaction> result = new ArrayList<DashboardTransaction>();
		LocalDate d = LocalDate.now();
		String crrdate = d.toString();
		String crrdatearr[] = crrdate.split("-");
		crrdate= crrdatearr[2]+"-"+crrdatearr[1]+"-"+crrdatearr[0];
		String startDate = "01"+"-"+crrdatearr[1]+"-"+crrdatearr[0];
		return DTrepo.getTransactionBetweenDates(startDate, crrdate);
	}
	
	
	
	public boolean addBulk(String data) {
		String a [] = data.split(";");
		for(int i=0;i<a.length;i++) {
			DashboardTransaction dt = new DashboardTransaction();
			String tmp []= a[i].split(",");
			dt.setDate(tmp[0]);
			dt.setAmount(Long.parseLong(tmp[1]));
			dt.setSave(Long.parseLong(tmp[2]));
			System.out.println(dt.toString());
			DTrepo.save(dt);
		}
		return true;
	}
	
	
	public List<DashboardTransaction>getTransactionsBrtweenDates(String from ,String to){
		System.out.println("service HIT"+from+" "+to);
		return DTrepo.getTransactionBetweenDates(from, to);
	}



	public boolean save(DashboardTransaction trans) {
		try {
		DTrepo.save(trans);
		return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public void updateTrans() {
		try {
			List<DashboardTransaction> trans = new ArrayList<DashboardTransaction>() ;
			DTrepo.findAll().forEach(i->trans.add(i));
			System.out.println(trans.size());
			JSONArray j = new JSONArray("[{\"date\":\"05-02-2019\"},{\"date\":\"06-02-2019\"},{\"date\":\"07-02-2019\"},{\"date\":\"08-02-2019\"},{\"date\":\"09-02-2019\"},{\"date\":\"10-02-2019\"},{\"date\":\"11-02-2019\"},{\"date\":\"12-02-2019\"},{\"date\":\"13-02-2019\"},{\"date\":\"14-02-2019\"},{\"date\":\"15-02-2019\"},{\"date\":\"16-02-2019\"},{\"date\":\"17-02-2019\"},{\"date\":\"18-02-2019\"},{\"date\":\"19-02-2019\"},{\"date\":\"20-02-2019\"},{\"date\":\"21-02-2019\"},{\"date\":\"22-02-2019\"},{\"date\":\"23-02-2019\"},{\"date\":\"24-02-2019\"},{\"date\":\"25-02-2019\"},{\"date\":\"26-02-2019\"},{\"date\":\"27-02-2019\"},{\"date\":\"28-02-2019\"},{\"date\":\"01-03-2019\"},{\"date\":\"02-03-2019\"},{\"date\":\"03-03-2019\"},{\"date\":\"04-03-2019\"},{\"date\":\"05-03-2019\"},{\"date\":\"06-03-2019\"},{\"date\":\"07-03-2019\"},{\"date\":\"08-03-2019\"},{\"date\":\"09-03-2019\"},{\"date\":\"10-03-2019\"},{\"date\":\"11-03-2019\"},{\"date\":\"12-03-2019\"},{\"date\":\"13-03-2019\"},{\"date\":\"14-03-2019\"},{\"date\":\"15-03-2019\"},{\"date\":\"16-03-2019\"},{\"date\":\"17-03-2019\"},{\"date\":\"18-03-2019\"},{\"date\":\"19-03-2019\"},{\"date\":\"20-03-2019\"},{\"date\":\"21-03-2019\"},{\"date\":\"22-03-2019\"},{\"date\":\"23-03-2019\"},{\"date\":\"24-03-2019\"},{\"date\":\"25-03-2019\"},{\"date\":\"26-03-2019\"},{\"date\":\"27-03-2019\"},{\"date\":\"28-03-2019\"},{\"date\":\"29-03-2019\"},{\"date\":\"30-03-2019\"},{\"date\":\"31-03-2019\"},{\"date\":\"01-04-2019\"},{\"date\":\"02-04-2019\"},{\"date\":\"03-04-2019\"},{\"date\":\"04-04-2019\"},{\"date\":\"05-04-2019\"},{\"date\":\"06-04-2019\"},{\"date\":\"07-04-2019\"},{\"date\":\"08-04-2019\"},{\"date\":\"09-04-2019\"},{\"date\":\"10-04-2019\"},{\"date\":\"11-04-2019\"},{\"date\":\"12-04-2019\"},{\"date\":\"13-04-2019\"},{\"date\":\"14-04-2019\"},{\"date\":\"15-04-2019\"},{\"date\":\"16-04-2019\"},{\"date\":\"17-04-2019\"},{\"date\":\"18-04-2019\"},{\"date\":\"19-04-2019\"},{\"date\":\"20-04-2019\"},{\"date\":\"21-04-2019\"},{\"date\":\"22-04-2019\"},{\"date\":\"23-04-2019\"},{\"date\":\"24-04-2019\"},{\"date\":\"25-04-2019\"},{\"date\":\"26-04-2019\"},{\"date\":\"27-04-2019\"},{\"date\":\"28-04-2019\"},{\"date\":\"29-04-2019\"},{\"date\":\"30-04-2019\"},{\"date\":\"01-05-2019\"},{\"date\":\"02-05-2019\"},{\"date\":\"03-05-2019\"},{\"date\":\"04-05-2019\"},{\"date\":\"05-05-2019\"},{\"date\":\"06-05-2019\"},{\"date\":\"07-05-2019\"},{\"date\":\"08-05-2019\"},{\"date\":\"09-05-2019\"},{\"date\":\"10-05-2019\"},{\"date\":\"11-05-2019\"},{\"date\":\"12-05-2019\"},{\"date\":\"13-05-2019\"},{\"date\":\"14-05-2019\"},{\"date\":\"15-05-2019\"},{\"date\":\"16-05-2019\"},{\"date\":\"17-05-2019\"},{\"date\":\"18-05-2019\"},{\"date\":\"19-05-2019\"},{\"date\":\"20-05-2019\"},{\"date\":\"21-05-2019\"},{\"date\":\"22-05-2019\"},{\"date\":\"23-05-2019\"},{\"date\":\"24-05-2019\"},{\"date\":\"25-05-2019\"},{\"date\":\"26-05-2019\"},{\"date\":\"27-05-2019\"},{\"date\":\"28-05-2019\"}]");
			
			for (int i=0;i<trans.size();i++) {
				DashboardTransaction dt = trans.get(i);
				JSONObject json =  j.getJSONObject(i);
				String date = json.getString("date");
				dt.setDate(date);
				System.out.println("corrected date : "+date+" "+i);
				DTrepo.save(dt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("JSON Error");
		}
	}
	
	
	
	
	
//	public boolean updateSaving(String data) {
//		String a[] = data.split(";");
//		for(int i=0;i<a.length;i++) {
//			
//		}
//	}
	
}
