package com.rg.common;

public class CommonConstants {
	public static String ip;
	public final static int ESSESTIALS=1;
	public final static int LIFESTYLE=2;
	
	public static int getDaysInMonths(int monthIndex, int year) {
		final int[] DAYSINMONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (monthIndex == 1)
			if ((year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)) {
				return DAYSINMONTH[monthIndex]+1;
			}
		return DAYSINMONTH[monthIndex];
	}

}
