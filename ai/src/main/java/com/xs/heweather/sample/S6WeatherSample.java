package com.xs.heweather.sample;

import java.util.Date;
import java.util.HashMap;

import com.xs.heweather.util.HeWeatherSIGNUtil;
import com.xs.heweather.util.HttpUtilForHeWeather;

/**
 * 和风S6接口SIGN示例代码
 * https://free-api.heweather.com/s6/weather/now?parameters
 * 实况天气示例代码(非官方)
 * @author 小帅丶
 *
 */
public class S6WeatherSample {
	public static String username="自己的用户ID在和风控制台看";
	public static void main(String[] args) throws Exception {
		String t = String.valueOf(new Date().getTime()/1000);
		System.out.println(t);
		HashMap<String,String> params = new HashMap<String, String>();
		params.put("location", "北京");
		params.put("username", username);
		params.put("t", t);
		String sign = HeWeatherSIGNUtil.getSignature(params, "自己的key控制台查看");
		String url = "https://free-api.heweather.com/s6/weather/forecast?location=北京&sign="+sign+"&username="+username+"&t="+t;
		System.out.println(url);
		String result = HttpUtilForHeWeather.post(url, "");
		System.out.println(result);
	}
}
