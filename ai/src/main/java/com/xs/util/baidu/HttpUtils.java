package com.xs.util.baidu;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * httpClient工具类
 * @author 小帅帅丶
 * @date  2017-3-21上午09:28:21
 */
public class HttpUtils {
	public static void main(String[] args) {
	}
	public static String request(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type",
	                        "application/x-www-form-urlencoded");
	        // 填入apikey到HTTP header
//	        connection.setRequestProperty("apikey",  APIConstants.API_KEY);
	        connection.setDoOutput(true);
	        connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	/**
	 * 
	 * @Title doPostBD
	 * @param url 接口地址
	 * @param method 请求方式
	 * @param headers 
	 * @param bodys
	 * @return response
	 * @throws Exception
	 * @author 小帅帅丶
	 * @date 2017-3-20
	 *
	 */
	public static HttpResponse doPostBD(String url, 
			Map<String, String> headers, 
			Map<String, String> bodys)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(url);
    	HttpPost request = new HttpPost(url);
        for (Map.Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }
        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList);
            formEntity.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
            request.setEntity(formEntity);
        }

        return httpClient.execute(request);
    }
	
	private static HttpClient wrapClient(String host) {
		HttpClient httpClient = new DefaultHttpClient();
		if (host.startsWith("https://")) {
			sslClient(httpClient);
		}
		return httpClient;
	}
	private static void sslClient(HttpClient httpClient) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] xcs, String str) {
                	
                }
                public void checkServerTrusted(X509Certificate[] xcs, String str) {
                	
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
        	throw new RuntimeException(ex);
        }
    }
	/**  
     * POST请求，字符串形式数据  
     * @param url 请求地址  
     * @param param 请求数据  
     * @param charset 编码方式  
     */  
    public static String sendPostUrl(String url, String param, String charset) {  
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            // 发送请求参数  
            out.print(param);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(  
                    conn.getInputStream(), charset));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送 POST 请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        // 使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  
	 public static String postT(String requestUrl,String params) throws Exception {
	        URL url = new URL(requestUrl);
	        // 打开和URL之间的连接
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        // 设置通用的请求属性
//	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        connection.setRequestProperty("Connection", "Keep-Alive");
	        connection.setRequestProperty("Content-Encoding", "UTF-8");
	        connection.setUseCaches(false);
	        connection.setDoOutput(true);
	        connection.setDoInput(true);

	        // 得到请求的输出流对象
	        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
	        out.writeBytes(params);
	        out.flush();
	        out.close();

	        // 建立实际的连接
	        connection.connect();
	        // 获取所有响应头字段
	        Map<String, List<String>> headers = connection.getHeaderFields();
	        // 遍历所有的响应头字段
	        for (String key : headers.keySet()) {
	            System.out.println(key + "--->" + headers.get(key));
	        }
	        // 定义 BufferedReader输入流来读取URL的响应
	        BufferedReader in = null;
	        if (requestUrl.contains("nlp"))
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
	        else
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	        String result = "";
	        String getLine;
	        while ((getLine = in.readLine()) != null) {
	            result += getLine;
	        }
	        in.close();
	        System.out.println("result:" + result);
	        return result;
	    }
	   public static String post(String requestUrl, String accessToken, String params) throws Exception {
	        String generalUrl = requestUrl + "?access_token=" + accessToken;
	        URL url = new URL(generalUrl);
	        // 打开和URL之间的连接
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        // 设置通用的请求属性
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        connection.setRequestProperty("Connection", "Keep-Alive");
	        connection.setUseCaches(false);
	        connection.setDoOutput(true);
	        connection.setDoInput(true);

	        // 得到请求的输出流对象
	        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
	        out.writeBytes(params);
	        out.flush();
	        out.close();

	        // 建立实际的连接
	        connection.connect();
	        // 获取所有响应头字段
	        Map<String, List<String>> headers = connection.getHeaderFields();
	        // 遍历所有的响应头字段
	        for (String key : headers.keySet()) {
	            System.out.println(key + "--->" + headers.get(key));
	        }
	        // 定义 BufferedReader输入流来读取URL的响应
	        BufferedReader in = null;
	        if (requestUrl.contains("nlp"))
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
	        else
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	        String result = "";
	        String getLine;
	        while ((getLine = in.readLine()) != null) {
	            result += getLine;
	        }
	        in.close();
	        System.out.println("result:" + result);
	        return result;
	    }
}
