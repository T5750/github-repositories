package com.xs.tencent.vision;

import java.util.HashMap;

import com.xs.tencent.TencentAPI;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 智能鉴黄  识别一个图像是否为色情图像
 * @author 小帅丶
 */
public class VisionPornTest {
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
	   //计算SIGN 
		String sign = TencentAISignSort.getSignature(bodys);
		bodys.put("sign", sign);
		//拼接一下带sign的参数
		String params = TencentAISignSort.getParams(bodys);
		//自行在TencentAPI 增加鉴黄接口即可
		String result = HttpUtil.post(TencentAPI.VISION_PORN, params);
		System.out.println(result);
	}
}
