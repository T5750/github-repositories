package com.xs.tencent.ptu;

import java.io.IOException;
import java.util.HashMap;


import com.xs.tencent.TencentAPI;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 颜龄检测 	给定图片，对原图进行人脸颜龄检测处理
 * @author 小帅丶
 * @date 2018年2月1日
 */
public class FaceAgeSample {
	
	public static void main(String[] args) throws Exception {
		String result = faceAge("G:/test2.jpg", TencentAPI.APP_ID_AI.toString());
		System.out.println(result);
	}
	/**
	 * 给定图片，对原图进行人脸颜龄检测处理
	 * @param imagePath 图片路径
	 * @param app_id 自己的appid
	 * @return
	 */
	public static String faceAge(String imagePath,String app_id){
		// 时间戳
		String time_stamp = System.currentTimeMillis() / 1000 + "";
		// 图片的二进制数组数据
		byte[] imageData;
		String result ="";
		try {
			imageData = FileUtil.readFileByBytes(imagePath);
		// 图片的base64编码数据
		String img64 = Base64Util.encode(imageData);
		// 随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		HashMap<String, String> bodys = new HashMap<String, String>();
		bodys.put("app_id",app_id);
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("image", img64);
		String sign = TencentAISignSort.getSignature(bodys);
		bodys.put("sign", sign);
		String params = TencentAISignSort.getParams(bodys);
		result = HttpUtil.post(TencentAPI.PTU_FACEAGE, params);
		return result;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			result="";
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result="";
			return result;
		}
	}
}
