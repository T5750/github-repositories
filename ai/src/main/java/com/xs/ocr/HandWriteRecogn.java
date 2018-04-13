package com.xs.ocr;

import java.net.URLEncoder;

import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;

/**
 * 手写字体识别
 * @author 小帅丶
 *
 */
public class HandWriteRecogn {
	//接口地址
	public String HANDWRITE_URL = "https://aip.baidubce.com/rest/2.0/ocr/v1/handwriting";
	public static void main(String[] args) {
		HandWriteRecogn handWriteRecogn = new HandWriteRecogn();
		String result=handWriteRecogn.getText("C:/Users/Administrator/Desktop/xs.jpg");
		System.out.println(result);
	}
	
	/**
	 * 手写文字识别 通过图片获取手写文字内容
	 * 返回字符串
	 * @param filePath 图片文件目录
	 * @return text
	 */
	public  String getText(String filePath){
		String result = "";
		 try {
	            byte[] imgData = FileUtil.readFileByBytes(filePath);
	            String imgStr = Base64Util.encode(imgData);
	           //识别图片上的手写文字
	            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
	            /**
	             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	             * 为了方便。直接调用了获取accesstoken的方法
	             */
//	            BDAccessToken bdAccessToken= util.getAccessToken(APIContants.IDCARD_API_KEY, APIContants.IDCARD_SERCET_KEY);
//	            String accessToken = bdAccessToken.getAccess_token().toString();
	            result = HttpUtil.post(HANDWRITE_URL,"自己的accesstoken", params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 return result;
	}
}
