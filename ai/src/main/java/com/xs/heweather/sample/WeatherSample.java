package com.xs.heweather.sample;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xs.heweather.bean.WeatherBean;
import com.xs.heweather.util.HttpUtilForHeWeather;
/**
 * 天气接口工具类
 * @author 小帅丶
 * @类名称  WeatherUtil
 * @remark 
 * @date  2017-8-8
 */
public class WeatherSample {
	/**
	 * 接口请求地址
	 */
	public static String FREE_API_WEATHER_URL="https://free-api.heweather.com/v5/weather?city=yourcity&key=yourkey";
	/**
	 * 应用的key
	 */
	public static String HEWEATHER_KEY = "";
	/**
	 * 查询全部天气信息方法
	 * @param city
	 * @return
	 * @throws Exception
	 */
	public static WeatherBean getWeatherForecast(String city) throws Exception{
		HttpUtilForHeWeather httpUtil = new HttpUtilForHeWeather();
		String url = FREE_API_WEATHER_URL.replace("yourcity", URLEncoder.encode(city,"UTF-8").replace("yourkey",HEWEATHER_KEY));
		System.out.println("请求的url====="+url);
		String result = httpUtil.post(url, "");
		JSON json = JSON.parseObject(result);
		WeatherBean weatherBean = JSONObject.toJavaObject(json, WeatherBean.class);
		return weatherBean;
	}
}
