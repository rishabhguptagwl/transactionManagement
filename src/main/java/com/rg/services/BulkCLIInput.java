package com.rg.services;

import java.util.Scanner;

import com.rg.model.DashboardTransaction;

public class BulkCLIInput {
	
	
	
	
	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		
		
		@SuppressWarnings("resource")
		Scanner sc =new Scanner(System.in);
		String a [] = (sc.nextLine().split(";"));
		for(int i=0;i<a.length;i++) {
			DashboardTransaction dt = new DashboardTransaction();
			String tmp []= a[i].split(",");
			dt.setDate(tmp[1]);
			dt.setAmount(Long.parseLong(tmp[0]));
			
		}
	}

}
