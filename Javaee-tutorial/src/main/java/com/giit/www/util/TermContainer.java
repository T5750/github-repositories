package com.giit.www.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Created by c0de8ug on 16-2-15.
 */
// TODO 这个类的名字..!!呵呵,实在想不到了
@Component
public class TermContainer {
	// TODO 这个volatile用的好像很蠢...一个月一次目测不会出事。。
	static List<Term> termList;
	// static LocalDate beginTime;
	static Date beginTime;
	static HashMap<String, String> map = new HashMap<String, String>();
	final static String lastTermStr = "上学期";
	final static String nextTermStr = "下学期";
	String lastSemester = "2,3,4,5,6,7,8";
	String nextSemester = "1,9,10,11,12";

	TermContainer() {
		for (String temp : lastSemester.split(",")) {
			map.put(temp, lastTermStr);
		}
		for (String temp : nextSemester.split(",")) {
			map.put(temp, nextTermStr);
		}
		// beginTime = LocalDate.of(2014, 1, 1);
		beginTime = java.sql.Date.valueOf("2014-01-01");
		update();
	}

	public static void update() {
		termList = new ArrayList<Term>();
		// LocalDate today = LocalDate.now();
		Date today = new Date();
		int beginYear = beginTime.getYear();
		int todayYear = today.getYear();
		int count = todayYear - beginYear;
		// if
		// (map.get(String.valueOf(today.getMonthValue())).equals(nextTermStr))
		// {
		if (map.get(String.valueOf(today.getMonth())).equals(nextTermStr)) {
			Term term = new Term();
			term.setVal(todayYear + "2");
			term.setText(todayYear + nextTermStr);
			termList.add(term);
		}
		Term term = new Term();
		term.setVal(todayYear + "1");
		term.setText(todayYear + lastTermStr);
		termList.add(term);
		for (int i = 0; i < count; i++) {
			todayYear--;
			term = new Term();
			term.setVal(todayYear + "2");
			term.setText(todayYear + nextTermStr);
			termList.add(term);
			term = new Term();
			term.setVal(todayYear + "1");
			term.setText(todayYear + lastTermStr);
			termList.add(term);
		}
	}

	public static String now() {
		return termList.get(0).getVal();
	}

	public static List<Term> getTermList() {
		return termList;
	}
}
