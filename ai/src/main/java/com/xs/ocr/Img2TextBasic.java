package com.xs.ocr;



import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.xs.common.APIContants;
import com.xs.pojo.BDAccessToken;
import com.xs.pojo.ocr.basic.OcrBasic;
import com.xs.pojo.ocr.basic.WordsResult;
import com.xs.util.baidu.AIUtil;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 百度通用文字OCR识别JavaDemo(非官方)
 * @author 小帅丶
 * @date  2017年6月13日16:04:12
 * @git http://git.oschina.net/xshuai/ai
 */
public class Img2TextBasic {
	
	public String GENERALBASIC_URL = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
	
	public static void main(String[] args) {
		Img2TextBasic basic = new Img2TextBasic();
		String test = basic.getText("F://juli2.jpg");
		//得到的结果转JSON
		JSONObject jsonObject = JSONObject.fromObject(test);
		/*
		 * 因为是复杂的Javabean 所以需要map转换为指定的目标类
		 * 有下列约定的map：
		 * 每一个键必须是一个字符串。
		 * 每一个值必须是一个类。
		 * 键可能是正则表达式。
		 * */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("words_result", WordsResult.class);
		OcrBasic basic2 = (OcrBasic) JSONObject.toBean(jsonObject, OcrBasic.class,map);
		
	}
	/**
	 * 通用文字识别 通过图片获取文字
	 * 返回字符串
	 * @param filePath 图片文件目录
	 * @return text
	 */
	public String getText(String filePath){
		AIUtil util = new  AIUtil();
		String result = "";
		 try {
	            byte[] imgData = FileUtil.readFileByBytes(filePath);
	            String imgStr = Base64Util.encode(imgData);
	           //简单识别图片上的文字
	            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
	            /**
	             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	             * 为了方便。直接调用了获取accesstoken的方法
	             */
	            BDAccessToken bdAccessToken= util.getAccessToken(APIContants.IDCARD_API_KEY, APIContants.IDCARD_SERCET_KEY);
	            String accessToken  = bdAccessToken.getAccess_token().toString();
	            result = HttpUtil.post(GENERALBASIC_URL,accessToken, params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 return result;
	}
	/**
	 * 通用文字识别 通过图片获取文字
	 * 直接返回对象
	 * @param filePath 图片文件目录
	 * @return basic
	 */
	public OcrBasic getText2Bean(String filePath){
		OcrBasic basic = new  OcrBasic();
		AIUtil util = new  AIUtil();
		String result = "";
		 try {
	            byte[] imgData = FileUtil.readFileByBytes(filePath);
	            String imgStr = Base64Util.encode(imgData);
	           //简单识别图片上的文字
	            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
	            /**
	             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	             * 为了方便。直接调用了获取accesstoken的方法
	             */
	            BDAccessToken bdAccessToken= util.getAccessToken(APIContants.IDCARD_API_KEY, APIContants.IDCARD_SERCET_KEY);
	            String accessToken  = bdAccessToken.getAccess_token().toString();
	            result = HttpUtil.post(GENERALBASIC_URL,accessToken, params);
	            //得到的结果转JSON
	    		JSONObject jsonObject = JSONObject.fromObject(result);
	    		/*
	    		 * 因为是复杂的Javabean 所以需要map转换为指定的目标类
	    		 * 有下列约定的map：
	    		 * 每一个键必须是一个字符串。
	    		 * 每一个值必须是一个类。
	    		 * 键可能是正则表达式。
	    		 * */
	    		Map<String, Object> map = new HashMap<String, Object>();
	    		map.put("words_result", WordsResult.class);
	    		basic = (OcrBasic) JSONObject.toBean(jsonObject, OcrBasic.class,map);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 return basic;
	}
}
