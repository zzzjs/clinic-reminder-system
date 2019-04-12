package com.pitt.zjs.springboot.restful.util;

import java.util.List;

import com.pitt.zjs.springboot.restful.entity.Reminder;


public class Utility {
	public static String listToString(List<Reminder> list) {
		String liString = "";
		for (Reminder reminder : list) {
			liString+= reminder.toString() +"\n";
		}
		return liString;
	}
}
