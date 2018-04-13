package com.xs.ocr;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

import com.xs.util.baidu.AIUtil;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 表格文字识别Java代码示例
 * @author 小帅丶
 *
 */
public class FormOcr2Text {
	public String FORM_OCR_URL = "https://aip.baidubce.com/rest/2.0/solution/v1/form_ocr/request";
	public String GET_REQUEST_RESULT_URL ="https://aip.baidubce.com/rest/2.0/solution/v1/form_ocr/get_request_result";
	public static void main(String[] args) {
		FormOcr2Text bankCard2Text = new FormOcr2Text();
		String result = bankCard2Text.getText("G:/excel2.jpg");
		JSONObject jsonObject =JSONObject.fromObject(result);
		JSONObject requestresult = JSONObject.fromObject(jsonObject.getJSONArray("result").get(0));
		bankCard2Text.getRequestResult(requestresult.getString("request_id"));
//		bankCard2Text.getRequestResult("9429648_3596");
	}
	/**
	 * 表格文字识别  通过图片获取表格文字信息
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
//	            BDAccessToken bdAccessToken= util.getAccessToken(APIContants.IDCARD_API_KEY, APIContants.IDCARD_SERCET_KEY);
//	            String accessToken = bdAccessToken.getAccess_token().toString();
//	            String accessToken = "24.9f7401e8c9f8ebf2a2fe9da7dac34e59.2592000.1503476383.282335-9429648";
	            result = HttpUtil.post(FORM_OCR_URL,"24.9f7401e8c9f8ebf2a2fe9da7dac34e59.2592000.1503476383.282335-9429648", params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 return result;
	}
	/**
	 * 表格文字识别  通过图片获取表格文字信息
	 * 返回字符串
	 * @param filePath 图片文件目录
	 * @return text
	 */
	public String getRequestResult(String request_id){
		AIUtil util = new  AIUtil();
		String result = "";
		 try {
	           //简单识别图片上的文字
	            String params = "request_id="+request_id+"&result_type=json";
	            /**
	             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	             * 为了方便。直接调用了获取accesstoken的方法
	             */
//	            BDAccessToken bdAccessToken= util.getAccessToken(APIContants.IDCARD_API_KEY, APIContants.IDCARD_SERCET_KEY);
//	            String accessToken = bdAccessToken.getAccess_token().toString();
//	            String accessToken = "24.9f7401e8c9f8ebf2a2fe9da7dac34e59.2592000.1503476383.282335-9429648";
	            result = HttpUtil.post(GET_REQUEST_RESULT_URL,"24.9f7401e8c9f8ebf2a2fe9da7dac34e59.2592000.1503476383.282335-9429648", params);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 return result;
	}
}
