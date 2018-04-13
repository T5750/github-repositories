package com.xs.test;

import com.xs.common.APIContants;
import com.xs.pojo.BDAccessToken;
import com.xs.util.baidu.AIUtil;
import com.xs.util.baidu.HttpUtil;

public class TestGetAT {
	public static void main(String[] args) throws Exception {
		HttpUtil httpUtil = new  HttpUtil();
		String requestUrl = "https://www.xsshome.cn/test/testCon/test";
		String params ="test";
		String str = httpUtil.post(requestUrl, params);
		System.out.println(str);
	}
}
