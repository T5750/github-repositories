package com.xs.youtu.ocr;

import com.alibaba.fastjson.JSON;
import com.xs.pojo.ocr.idcard.IdCardBean;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.youtu.common.YouTuAPIContants;
import com.xs.youtu.common.YouTuAppContants;
import com.xs.youtu.pojo.HandWritingOCRBean;
import com.xs.youtu.util.HttpUtil4YouTu;
import com.xs.youtu.util.YouTuSign;

/** 
 * 手写文字识别
 * @author 小帅丶 
 * @类名称  HandWritingOCRSample 
 * @remark  
 * @date  2018-03-22
 */ 
public class HandWritingOCRSample {
	public static void main(String[] args) throws Exception {
		String imagePath = "G:/hw2.jpg";
//		System.out.println(getHandWritingOCR(imagePath));
		HandWritingOCRBean writingOCRBean  = getHandWritingOCRBean(imagePath);
		for (int i = 0; i < writingOCRBean.getItems().size(); i++) {
			for (int j = 0; j < writingOCRBean.getItems().get(i).getWords().size(); j++) {
				System.out.print(writingOCRBean.getItems().get(i).getWords().get(j).getCharacter());
			}
		}
		
	}
	/**
	 * 返回字符串
	 * @param imagePath 图片路径
	 * @return
	 * @throws Exception
	 */
	public static String getHandWritingOCR(String imagePath) throws Exception{
		//获取SIGN值
		String sign = YouTuSign.getSign(YouTuAppContants.userQQ,
				YouTuAppContants.AppID, YouTuAppContants.SecretID,
				YouTuAppContants.SecretKey);
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
		String image = Base64Util.encode(imgData);
		String params = "{\"app_id\":\""+YouTuAppContants.AppID+"\",\"image\":\""+image+"\"}";
		String result = HttpUtil4YouTu.post(YouTuAPIContants.OCRAPI_HANDWRITINGOCR, params, sign);
		return result;
	}
	/**
	 * 返回HandWritingOCRBean
	 * @param imagePath 图片路径
	 * @return
	 * @throws Exception
	 */
	public static HandWritingOCRBean getHandWritingOCRBean(String imagePath) throws Exception{
		//获取SIGN值
		String sign = YouTuSign.getSign(YouTuAppContants.userQQ,
				YouTuAppContants.AppID, YouTuAppContants.SecretID,
				YouTuAppContants.SecretKey);
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
		String image = Base64Util.encode(imgData);
		String params = "{\"app_id\":\""+YouTuAppContants.AppID+"\",\"image\":\""+image+"\"}";
		String result = HttpUtil4YouTu.post(YouTuAPIContants.OCRAPI_HANDWRITINGOCR, params, sign);
		HandWritingOCRBean writingOCRBean = JSON.parseObject(result, HandWritingOCRBean.class);
		return writingOCRBean;
	}
}
