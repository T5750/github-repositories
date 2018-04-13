package com.xs.tencent.ocr;

import java.util.HashMap;

import com.xs.tencent.TencentAPI;
import com.xs.tencent.sign.Base64Util;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;



/**
 * @author 小帅丶
 * @类名称  DriverLicenseOCR
 * @remark 行驶证驾驶证OCR识别
 * @date  2017-12-11
 */
public class DriverLicenseOCR {
	public static void main(String[] args) throws Exception {
		//时间戳
		String time_stamp = System.currentTimeMillis()/1000+"";
		//图片的二进制数组数据
		byte [] imageData = FileUtil.readFileByBytes("G:/1201.jpg");
		//图片的base64编码数据
		String image = Base64Util.encode(imageData);
		//随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("time_stamp", time_stamp);
		params.put("image", image);
		params.put("nonce_str", nonce_str);
		params.put("app_id", "自己的appid");
		params.put("type", "0");
		String sign = TencentAISignSort.getSignature(params);
		params.put("sign",sign);
		String param = TencentAISignSort.getParams(params);
		String result = HttpUtil.post(TencentAPI.OCR_DRIVERLICENSEOCR, param);
		System.out.println(result);
	}
}
