package com.xs.tencent.vision;

import java.util.HashMap;

import com.xs.tencent.TencentAPI;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;

public class VisionImgidentifySample {
	public static void main(String[] args) throws Exception {
		//时间戳
		String time_stamp = System.currentTimeMillis()/1000+"";
		//图片的二进制数组数据
		byte [] imageData = FileUtil.readFileByBytes("G:/yl.jpg");
		//图片的base64编码数据
		String image = Base64Util.encode(imageData);
		//随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		HashMap<String, String> bodys = new HashMap<String, String>();
		bodys.put("app_id",TencentAPI.APP_ID_AI.toString());
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("image", image);
		//识别场景，1-车辆识别，2-花草识别
		bodys.put("scene", "2");
	   //计算SIGN 
		String sign = TencentAISignSort.getSignature(bodys);
		bodys.put("sign", sign);
		//拼接一下带sign的参数
		String params = TencentAISignSort.getParams(bodys);
		//自行在TencentAPI 增加花草/车辆识别API地址即可
//		String result = HttpUtil.post(TencentAPI.VISION_IMGIDENTIFY, params);
//		System.out.println(result);
	}
}
