package com.xs.youtu.util;

import java.io.BufferedReader;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.HttpURLConnection;  
import java.net.URL;  
import java.security.KeyManagementException;  
import java.security.NoSuchAlgorithmException;  
import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  
   
import javax.net.ssl.HostnameVerifier;  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.SSLSession;  
import javax.net.ssl.TrustManager;  
import javax.net.ssl.X509TrustManager; 


/**
 * http 工具类
 */
public class HttpUtil4YouTu {
	 private static class TrustAnyTrustManager implements X509TrustManager {  
         public void checkClientTrusted(X509Certificate[] chain, String authType)  
                 throws CertificateException {  
         }  
         public void checkServerTrusted(X509Certificate[] chain, String authType)  
                 throws CertificateException {  
         }  
         public X509Certificate[] getAcceptedIssuers() {  
             return new X509Certificate[] {};  
         }  
     }  
    
     private static class TrustAnyHostnameVerifier implements HostnameVerifier {  
         public boolean verify(String hostname, SSLSession session) {  
             return true;  
         }  
     }  
	/**
	 * 
	 * @param requestUrl 接口地址
	 * @param params 参数
	 * @param sign 签名值
	 * @return
	 * @throws Exception
	 */
    public static String post(String requestUrl,String params,String sign) throws Exception {
    	 SSLContext sc = SSLContext.getInstance("SSL");  
         sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },  
                 new java.security.SecureRandom());  
         URL console = new URL(requestUrl);  
         Integer length = params.length();  
         HttpURLConnection conn = (HttpURLConnection) console.openConnection();  
         //文档要求填写的Header参数                  
         conn.setRequestProperty("Host", "api.youtu.qq.com");  
         conn.setRequestProperty("Content-Length",length.toString());  
         conn.setRequestProperty("Content-Type", "text/json");  
         conn.setRequestProperty("Authorization", sign);  
         conn.setDoOutput(true);  
         conn.connect();  
         DataOutputStream out = new DataOutputStream(conn.getOutputStream());  
         out.write(params.getBytes("UTF-8"));  
         // 刷新、关闭  
         out.flush();  
         out.close();  
         BufferedReader in = null;  
         in = new BufferedReader(  
                 new InputStreamReader(conn.getInputStream(), "UTF-8"));  
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

