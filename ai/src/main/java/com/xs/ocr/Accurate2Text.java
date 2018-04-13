package com.xs.ocr;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

import com.xs.common.APIContants;
import com.xs.util.baidu.AIUtil;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;

/**
 * 百度通用文字识别（含位置高精度版） 非官方示例代码
 * @author 小帅丶
 * 2017年8月1日16:59:49
 */
public class Accurate2Text {
	/**
	 * 接口地址
	 */
	public static String ACCURATE_URL = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate";
	
	public static void main(String[] args)throws Exception {
		String filePath ="F:/juli2.jpg";
		String recognize_granularity ="small";
		Boolean detect_direction = true;
		String vertexes_location="true";
		String probability ="true";
//		getAccurate2Text(filePath, recognize_granularity, detect_direction, vertexes_location, probability);
		getAccurate2Text(filePath);
	}
	/**
	 * 所有参数请求
	 * @param filePath 要识别的图片路径
	 * @param recognize_granularity  是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
	 * @param detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。
	 * 							可选值包括:- true：检测朝向；- false：不检测朝向。
	 * @param vertexes_location 是否返回文字外接多边形顶点位置，不支持单字位置。默认为false
	 * @param probability 是否返回识别结果中每一行的置信度
	 * @return
	 */
	public static JSONObject getAccurate2Text(String filePath,String recognize_granularity,Boolean detect_direction,String vertexes_location,String probability){
		String result = "";
		try {
				byte[] imgData = FileUtil.readFileByBytes(filePath);
	            String imgStr = Base64Util.encode(imgData);
	           //简单识别图片上的文字
	            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8")
	            		+"&recognize_granularity="+recognize_granularity
	            		+"&detect_direction="+detect_direction
	            		+"&vertexes_location="+vertexes_location
	            		+"&probability="+probability;
	            result = HttpUtil.post(ACCURATE_URL,"替换成自己的accestoken", params);
	            //得到的结果转JSON
	    		JSONObject jsonObject = JSONObject.fromObject(result);
	    		System.out.println(jsonObject);
	    		return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 只传递必须参数的方法
	 * @param filePath 要识别的图片路径
	 * @return
	 */
	public static JSONObject getAccurate2Text(String filePath){
		String result = "";
		try {
				byte[] imgData = FileUtil.readFileByBytes(filePath);
	            String imgStr = Base64Util.encode(imgData);
	           //简单识别图片上的文字
	            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
	            result = HttpUtil.post(ACCURATE_URL,"替换成自己的accestoken", params);
	            //得到的结果转JSON
	    		JSONObject jsonObject = JSONObject.fromObject(result);
	    		System.out.println(jsonObject);
	    		return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
