package com.xs.tencent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xs.tencent.aai.bean.AaiTTA;
import com.xs.tencent.aai.bean.AaiTTS;

import sun.misc.BASE64Decoder;
 
public class HttpsUtil4Tencent {
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
	public static HttpResponse doPostTencentAI(String url, 
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
	/**
	 * get请求
	 * @param requestUrl
	 * @param params
	 * @return
	 * @throws Exception
	 */
	  public static String getforVoice(String requestUrl,String params) throws Exception {
	        String generalUrl = requestUrl;
	        URL url = new URL(generalUrl);
	        // 打开和URL之间的连接
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
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
	/**
	 * 保存语音文件
	 * @param result
	 * @return
	 */
	public static String getMP3Voice(String result) {
		String workspace = System.getProperty("user.home");
    	String path = workspace+"/text2audio/";
    	String suffixnam = ".mp3";
    	AaiTTS aaiTTS = JSONObject.toJavaObject(JSON.parseObject(result), AaiTTS.class);
    	try {
			if (!(new File(path).isDirectory())) {
				new File(path).mkdir();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
    	if(aaiTTS.getData().getFormat()==1){
    		suffixnam = ".pcm";
    	}else if (aaiTTS.getData().getFormat()==2) {
    		suffixnam= ".wav";
		}else if (aaiTTS.getData().getFormat()==3) {
			suffixnam = ".mp3";
		}else {
			suffixnam = ".mp3";
		}
    	String filePath = path+"VOICE"+new Date().getTime()/1000+suffixnam;
		if(result==null){
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] bytes = decoder.decodeBuffer(aaiTTS.getData().getSpeech());
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			fileOutputStream.write(bytes);
			fileOutputStream.close();
			System.out.println("请求结束"+new Date().getTime()/1000);
		    System.out.println("MP3文件保存目录:" + filePath);
			return filePath;
		} catch (Exception e) {
			System.out.println("请求出错"+e.getMessage());
			return null;
		}
	}
	/**
	 * 保存语音文件
	 * @param result
	 * @return
	 */
	public static String getMP3VoiceYouTu(String result) {
		String workspace = System.getProperty("user.home");
    	String path = workspace+"/text2audio/";
    	String suffixnam = ".mp3";
    	AaiTTA aaiTTA = JSONObject.toJavaObject(JSON.parseObject(result), AaiTTA.class);
    	try {
			if (!(new File(path).isDirectory())) {
				new File(path).mkdir();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
    	String filePath = path+"VOICE"+new Date().getTime()/1000+suffixnam;
		if(result==null){
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] bytes = decoder.decodeBuffer(aaiTTA.getData().getVoice());
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			fileOutputStream.write(bytes);
			fileOutputStream.close();
			System.out.println("请求结束"+new Date().getTime()/1000);
		    System.out.println("MP3文件保存目录:" + filePath);
			return filePath;
		} catch (Exception e) {
			System.out.println("请求出错"+e.getMessage());
			return null;
		}
	}
}
