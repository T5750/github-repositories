package com.xs.util.baidu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
/**
 * UNIT接口HTTP工具类
 * @author 小帅丶
 *
 */
public class UnitHttpUtil {

	public static String post(String requestUrl, String accessToken,
			String params) throws Exception {
		String contentType = "application/x-www-form-urlencoded";
		return UnitHttpUtil.post(requestUrl, accessToken, contentType, params);
	}

	public static String post(String requestUrl, String accessToken,
			String contentType, String params) throws Exception {
		String encoding = "UTF-8";
		if (requestUrl.contains("nlp")) {
			encoding = "GBK";
		}
		return UnitHttpUtil.post(requestUrl, contentType, accessToken, params,
				encoding);
	}

	public static String post(String requestUrl, String contentType,
			String accessToken, String params, String encoding)
			throws Exception {
		String url = requestUrl + "?access_token=" + accessToken;
		return UnitHttpUtil.postGeneralUrl(url, contentType, params, encoding);
	}

	public static String postGeneralUrl(String generalUrl, String contentType,
			String params, String encoding) throws Exception {
		URL url = new URL(generalUrl);
		// 打开和URL之间的连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		// 设置通用的请求属性
		connection.setRequestProperty("Content-Type", contentType);
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		connection.setDoInput(true);

		// 得到请求的输出流对象
		OutputStreamWriter out = new OutputStreamWriter(
				connection.getOutputStream(), encoding);
		out.write(params);
		out.flush();
		out.close();

		// 建立实际的连接
		connection.connect();
		// 获取所有响应头字段
		Map<String, List<String>> headers = connection.getHeaderFields();
		// 遍历所有的响应头字段
		for (String key : headers.keySet()) {
			System.err.println(key + "--->" + headers.get(key));
		}
		// 定义 BufferedReader输入流来读取URL的响应
		BufferedReader in = null;
		in = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), encoding));
		String result = "";
		String getLine;
		while ((getLine = in.readLine()) != null) {
			result += getLine;
		}
		in.close();
		System.err.println("result:" + result);
		return result;
	}

}
