package com.xs.ocr;

import java.net.URLEncoder;

import com.xs.util.baidu.AIUtil;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;

public class BankCard2Text {
	public String BANKCARD_URL = "https://aip.baidubce.com/rest/2.0/ocr/v1/bankcard";
	public static void main(String[] args) {
		BankCard2Text bankCard2Text = new BankCard2Text();
		bankCard2Text.getText("G:/bank.jpg");
	}
	
	/**
	 * 银行卡识别 通过图片获取银行卡信息
	 * 返回字符串
	 * @param filePath 图片文件目录
	 * @return text
	 */
	public String getText(String filePath){
		String result = "";
		 try {
	            byte[] imgData = FileUtil.readFileByBytes(filePath);
	            String imgStr = Base64Util.encode(imgData);
	           //简单识别图片上的文字
	            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
	            /**
	             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	             */
	            String accessToken = "请自行通过接口获取ACCESSTOKEN";
	            result = HttpUtil.post(BANKCARD_URL,accessToken, params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 return result;
	}
}
