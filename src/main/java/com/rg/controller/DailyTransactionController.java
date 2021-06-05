package com.rg.controller;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rg.model.DailyTransaction;
import com.rg.model.DailyTransactionClass;
import com.rg.services.DailyTransactionClassServices;
import com.rg.services.DailyTransactionServices;
import com.sun.org.apache.bcel.internal.generic.NEW;


@CrossOrigin(origins = "*") //
//@CrossOrigin(origins = "http://192.168.43.49:4200") // for local
//@CrossOrigin(origins = "http://localhost:8080") //for server
@RestController
public class DailyTransactionController {
	final int ESSESTIALS=1;
	final int LIFESTYLE=2;
	
	
	
	
	
	final String ip = "http://192.168.43.49:4200";
	@Autowired
	DailyTransactionServices dailyTransactionServices;

	@Autowired
	DailyTransactionClassServices dailyTransactionClassServices;

	@GetMapping(value = "getAllTransaction", produces = "application/json")
	public List<DailyTransaction> getAllTransaction() {
		return dailyTransactionServices.getAllTransaction();
	}

	
	
	
	
	
	
	@GetMapping(value = "monthtilldate", produces = "application/json")
	public List<DailyTransaction> getMonthTillDate() {
		return dailyTransactionServices.getMonthTillDateTransaction();
	}

//	@PostMapping(value = "dailytransaction/save", produces = "application/json")
//	public String addNewTransaction(@RequestBody String dt) {
//		try {
//			System.out.println(dt);
//			JSONArray arrDailyTrans = new JSONArray();
//			JSONObject j = new JSONObject(dt);
//			int length = j.getInt("length");
//			JSONObject data = j.getJSONObject("data");
//			String date = data.getString("transDate");
//			for (int i = 0; i < length; i++) {
//				JSONObject exp = new JSONObject();
//				exp.put("amount", data.getString("transactionAmount" + i));
//				exp.put("name", data.getString("transactionName" + i));
//
//				arrDailyTrans.put(exp);
//			}
//			DailyTransaction dailyTransaction = new DailyTransaction(date,
//					new JSONObject().put("transactions", arrDailyTrans).toString());
//			dailyTransactionServices.saveTransaction(dailyTransaction);
//			return new JSONObject().put("Status", "Success").toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "Error";
//		}
//	}

	@PostMapping(value = "getdailytransactionbydate", produces = "application/json")
	public List<DailyTransaction> getTransactionBydate(@RequestBody String Dt) {
		try {
			JSONObject j = new JSONObject(Dt);
			String form = j.getString("form");
			String to = j.getString("to");
			System.out.println(form + " : " + to);
			return dailyTransactionServices.DailyTransactionByDate(form, to);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* Expected request JSON as {"date":"2019-05-20"} */

	@SuppressWarnings("deprecation")
	@PostMapping("/WeeklyTransaction")
	public List<DailyTransaction> getWeekTransactions(@RequestBody String req) {
		System.out.println("DailyTransactionController | getWeekTransaction | invoked");
		try {
			JSONObject j = new JSONObject(req);
			String s = j.getString("date");
//			System.out.println("End date:" +s);
			String[] str = s.split("-");
			Date d = new Date();
			d.setDate(Integer.parseInt(str[2]));
			d.setMonth(Integer.parseInt(str[1]) - 1);
			d.setYear(Integer.parseInt(str[0]));
			Date sd = new Date(d.getTime() - (24 * 3600000 * d.getDay()));
			Date ed = (new Date(d.getTime() + (24 * 3600000 * (6 - d.getDay()))));
			String StartDate = (sd.getYear() + "-"+((sd.getMonth()+1)<10?"0":"") + (sd.getMonth() + 1) + "-" + sd.getDate());
			s = ed.getYear() + "-" + ((ed.getMonth()+1)<10?"0":"") + (ed.getMonth() + 1) + "-" + ed.getDate();
//			System.out.println("Start date "+d.toString());
			System.out.println("Start Date " + sd + "::End date" + ed);
			dailyTransactionClassServices.getWeekTransaction(StartDate, s).forEach(i->System.out.println(i.toString()));
			System.out.println("DailyTransactionController | getWeekTransaction | exits");
			return dailyTransactionServices.DailyTransactionByDate(StartDate, s);
//			return null;	
		} catch (JSONException je) {
			je.printStackTrace();
			System.out.println("DailyTransactionController | getWeekTransaction | exits");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DailyTransactionController | getWeekTransaction | exits");
			return null;
		}
	}

	@PostMapping("/todaytransaction")
	public String getTransactionByDate(@RequestBody String req) {
		try {
			JSONObject j = new JSONObject(req);
			String date = j.getString("date");
			System.out.println("----------------------------------------------------------");
			System.out.println("Date:"+date);
			 DailyTransactionClass dailyTransactionclass = (dailyTransactionClassServices.getDailyTransactionByDate(date));
			 if(dailyTransactionclass==null) {
				 return (new JSONObject().put("Result","No data found")).toString();
			 }
			 DailyTransaction dailyTransaction =(dailyTransactionServices.getTransactionByDate(date));
			JSONArray arrResultDailyTransactions = new JSONArray();
			JSONArray transactions = (new JSONObject(dailyTransaction.getTransaction()).getJSONArray("transactions"));
			JSONArray transactionclass = (new JSONArray(dailyTransactionclass.getType()));
			for(int j1=0;j1<transactions.length();j1++) {
				JSONObject resultDailyTransactions = new JSONObject();
				JSONObject transaction = new JSONObject(transactions.get(j1).toString());
				JSONObject SingletransactionClass = new JSONObject(transactionclass.get(j1).toString());
				resultDailyTransactions.put("amount", transaction.getInt("amount"));
				resultDailyTransactions.put("name", transaction.getString("name"));
				resultDailyTransactions.put("value", SingletransactionClass.getInt("value")==ESSESTIALS?"Essentials":"Life Style");
				arrResultDailyTransactions.put(resultDailyTransactions);
			}
			System.out.println("----------------------------------------------------------");
			return (new JSONObject().put("Result",arrResultDailyTransactions)).toString();
//			return dailyTransactionServices.getTransactionByDate(date);
			
		} catch (JSONException je) {
			je.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	@PostMapping(value="/dailytransaction/updateDailyTransaction", produces="application/json")
	public String updateDailyTranscation(@RequestBody String data) {
		System.out.println(data);
		try {
		JSONObject requestData = new JSONObject(data);
		JSONArray transactionArray = requestData.getJSONArray("data");
		String date = requestData.getString("date");
		JSONArray updatedTransaction = new JSONArray();
		JSONArray updatedTransactionClass = new JSONArray();
		for(int i=0;i<transactionArray.length();i++) {
			JSONObject transaction = transactionArray.getJSONObject(i);
			
			String amount = transaction.getString("amount");
			String name = transaction.getString("name");
			String value = transaction.getString("value");
			JSONObject updatedobject = new JSONObject();
			updatedobject.put("amount", amount);
			updatedobject.put("name", name);
			updatedTransaction.put(updatedobject);
			updatedTransactionClass.put(new JSONObject().put("value",(value.equals("Essentials")?1:2)));
		}
		System.out.println(dailyTransactionClassServices.updateTransactionclass(date, updatedTransactionClass));
		System.out.println(dailyTransactionServices.updateTransaction(date, new JSONObject().put("transactions", updatedTransaction)));
		return "{\"status\":\"success\"}";
		}
		catch(JSONException jsonexception) {
			System.out.println(jsonexception);
			return "{\"status\":\"error\"}";
		}
		catch(Exception exception) {
			System.out.println(exception);
			return "{\"status\":\"error\"}";
		}
		
		
		
	}
	

	@PostMapping(value = "dailytransaction/savetest", produces = "application/json")
	public String addNewTransactionTEST(@RequestBody String dt) {
		try {
			System.out.println(dt);
			JSONArray arrDailyTrans = new JSONArray();
			JSONArray arrDailyTransClass = new JSONArray();
			JSONObject j = new JSONObject(dt);
			
			int length = j.getInt("length");
			JSONObject data = j.getJSONObject("data");
			String date = data.getString("transDate");
			date = date.substring(0, 10);
			System.out.println("Date to store in DB:"+date);
			for (int i = 0; i < length; i++) {
				JSONObject exp = new JSONObject();
				exp.put("amount", data.getString("transactionAmount" + i));
				exp.put("name", data.getString("transactionName" + i));
				arrDailyTransClass.put(new JSONObject().put("value",data.getInt("type"+i)));
				arrDailyTrans.put(exp);
			}
			DailyTransaction dailyTransaction = new DailyTransaction(date,
						new JSONObject().put("transactions", arrDailyTrans).toString());
			DailyTransactionClass dtclass = new DailyTransactionClass();
			dtclass.setId(dailyTransactionServices.saveTransaction(dailyTransaction).getId());
			dtclass.setType(arrDailyTransClass.toString());
			System.out.println(dtclass.toString());
			if(dailyTransactionClassServices.saveTransaction(dtclass))
			 	return new JSONObject().put("Status", "Success").toString();
		 	else
			 	return new JSONObject().put("Status", "Failure").toString();
//			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "\"Status\":\"Error\"";
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "dailytransaction/Transactionbyclass", produces = "application/json")
	public String GetTransactionByClass(@RequestBody String st) {
		try {

		System.out.println(st);
		JSONObject j = new JSONObject(st);
		String s = j.getString("date");
		String[] str = s.split("-");
		Date d = new Date();
		d.setDate(Integer.parseInt(str[2]));
		d.setMonth(Integer.parseInt(str[1]) - 1);
		d.setYear(Integer.parseInt(str[0]));
		Date sd = new Date(d.getTime() - (24 * 3600000 * d.getDay()));
		Date ed = (new Date(d.getTime() + (24 * 3600000 * (6 - d.getDay()))));
		String StartDate = (sd.getYear() + "-"+((sd.getMonth()+1)<10?"0":"") + (sd.getMonth() + 1) + "-" + sd.getDate());
		s = ed.getYear() + "-"+((ed.getMonth()+1)<10?"0":"") + (ed.getMonth() + 1) + "-" + ed.getDate();
		System.out.println("Start Date " + sd + "::End date" + ed);
		List<DailyTransaction> dailyTransaction = dailyTransactionServices.DailyTransactionByDate(StartDate, s);
		List<DailyTransactionClass> dailyTransactionclass =  dailyTransactionClassServices.getWeekTransaction(StartDate,s);
		System.out.println(dailyTransaction);
		System.out.println(dailyTransactionclass);
		JSONObject result = new JSONObject();
		JSONArray resultArr = new JSONArray();
		for(int i=0;i<dailyTransaction.size();i++) {
			JSONArray arrResultDailyTransactions = new JSONArray();
			JSONArray transactions = (new JSONObject(dailyTransaction.get(i).getTransaction()).getJSONArray("transactions"));
			JSONArray transactionclass = (new JSONArray(dailyTransactionclass.get(i).getType()));
			
			for(int j1=0;j1<transactions.length();j1++) {
				JSONObject resultDailyTransactions = new JSONObject();
				JSONObject transaction = new JSONObject(transactions.get(j1).toString());
				JSONObject SingletransactionClass = new JSONObject(transactionclass.get(j1).toString());
				resultDailyTransactions.put("amount", transaction.getInt("amount"));
				resultDailyTransactions.put("name", transaction.getString("name"));
				resultDailyTransactions.put("value", SingletransactionClass.getInt("value")==ESSESTIALS?"Essentials":"Life Style");
				arrResultDailyTransactions.put(resultDailyTransactions);
			}
			JSONObject oneDayTransaction = new JSONObject();
			oneDayTransaction.put("date",dailyTransaction.get(i).getDate());
			oneDayTransaction.put("Transactions",arrResultDailyTransactions);
			resultArr.put(oneDayTransaction);
		}
		result.put("transaction", resultArr);
		System.out.println(resultArr.toString());
		return result.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	
	
	
	@PostMapping(value="/dailytransaction/delete" ,produces="application/json")
	public String deleteTransaction(@RequestBody String date) {
		System.out.println("-------------------------");
		System.out.println(date);
		try {
			JSONObject requestJson = new JSONObject(date);
			date = requestJson.getString("date");
			if(this.dailyTransactionClassServices.deleteTransactionByDate(date)==1)
			{
			 if((this.dailyTransactionServices.deleteTransactionbyDate(date))==1)
				 return "{\"result\":\"success\"}";
			 else
				 return "{\"result\":\"fail\"}";
			}
			else {
				return "{\"result\":\"fail\"}";
			}
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-------------------------");
			return "{\"result\":\"fail\"}";
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("-------------------------");
			return "{\"result\":\"fail\"}";
		}
		
		
	}
	
	
	
//	@SuppressWarnings("deprecation")
//	@RequestMapping(value="dailytransaction/transactionMonthly")
//	public String getTransactionByMonthWeekWise(@RequestBody String requestbody) {
//		String result = null;
//		try {
//			JSONObject json = new JSONObject(requestbody);
//			String month = json.getString("month");
//			String year = json.getString("year");
//			String day = "01";
//			Date d = new SimpleDateFormat("yyyy-MM-dd").parse( year+"-"+month+"-"+day);
////			d.setDate(Integer.parseInt(day));
////			d.setMonth(Integer.parseInt(month));
////			d.setYear(Integer.parseInt(year));
//			System.out.println(d);
//			System.out.println("day of week "+d.getDay());
//			//result = this.getWeekTransactions(new JSONObject().put("date", year+"-"+month+"-"+day).toString()).toString();
//			
//			System.out.println(result);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
}
