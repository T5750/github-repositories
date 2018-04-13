package com.xs.util.baidu;

import net.sf.json.JSONObject;

import com.xs.common.APIContants;
import com.xs.pojo.BDAccessToken;

/**
 * 百度AI相关工具类(非官方)
 * @author 小帅丶
 * @date  2017-5-26上午10:38:51
 */
public class AIUtil {
	/**
	 * 获取AccessToken的接口地址
	 * ACCESS_TOKEN_URL
	 */
	public String ACCESS_TOKEN_URL = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=CLIENTID&client_secret=CLIENTSECRET";
	/**
	 * 
	 * 获取百度AccessToken
	 * @Title getAccessToken
	 * @param apiKey 
	 * @param sercetKey 
	 * @return BDAccessToken
	 * @author 小帅丶
	 * @throws Exception 
	 * @date 2017-5-26
	 *
	 */
	public  BDAccessToken getAccessToken(String apiKey,String sercetKey) throws Exception{
		BDAccessToken accessToken = new BDAccessToken();
		HttpUtil httpUtil = new HttpUtil();
		String url = ACCESS_TOKEN_URL.replace("CLIENTID",apiKey).replace("CLIENTSECRET",sercetKey);
		String param = "";
		String result = httpUtil.post(url, param);
		JSONObject object = JSONObject.fromObject(result);
		accessToken =  (BDAccessToken) JSONObject.toBean(object,BDAccessToken.class);
		return accessToken;
	}
}
