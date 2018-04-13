/**
 * @author 小帅丶
 */
package com.xs.tencent.ocr;

import java.net.URLEncoder;
import java.util.HashMap;

import com.xs.tencent.TencentAPI;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;



/**
 * @author 小帅丶
 * @类名称  GeneralOCR
 * @remark 
 * @date  2017-11-27
 */
public class GeneralOCR {
	public static void main(String[] args) throws Exception {
		//时间戳
		String time_stamp = System.currentTimeMillis()/1000+"";
		//图片的二进制数组数据
		byte [] imageData = FileUtil.readFileByBytes("G:/ocr.png");
		//图片的base64编码数据
		String image = Base64Util.encode(imageData);
		//随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		HashMap<String, String> bodys = new HashMap<String, String>();
		bodys.put("app_id","1106471787");
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("image", image);
		//计算SIGN 旧方法
//	   String sign =TencentAISign.appSignAI4GeneralOCR(TencentAPI.APP_ID_AI, image,nonce_str);
//	   String params ="app_id="+TencentAPI.APP_ID_AI.toString()+"&time_stamp="+time_stamp+"&nonce_str="+nonce_str+"&sign="+sign+"&image="+URLEncoder.encode(image,"UTF-8");
//	   System.out.println(params);
//	   String result = HttpUtil.post(TencentAPI.OCR_GENERAL, params);
//	   System.out.println(result);
	   //计算SIGN 新方法
		String sign = TencentAISignSort.getSignature(bodys);
		bodys.put("sign", sign);
		//拼接一下带sign的参数
		String params = TencentAISignSort.getParams(bodys);
		String result = HttpUtil.post(TencentAPI.OCR_GENERAL, params);
		System.out.println(result);
	}
}
