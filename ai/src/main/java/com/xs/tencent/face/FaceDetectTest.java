package com.xs.tencent.face;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.xs.tencent.HttpsUtil4Tencent;
import com.xs.tencent.TencentAPI;
import com.xs.tencent.pojo.MultiFaceBean;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 腾讯AI人脸检测示例Demo
 * @author 小帅
 *
 */
public class FaceDetectTest {
	public static void main(String[] args) throws Exception {
		//时间戳
		String time_stamp = System.currentTimeMillis()/1000+"";
		//图片的二进制数组数据
		byte [] imageData = FileUtil.readFileByBytes("G:/test2.jpg");
		//图片的base64编码数据
		String img64 = Base64Util.encode(imageData);
		//随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		//参数拼接
		HashMap<String, String> bodys = new HashMap<String, String>();
		bodys.put("app_id",TencentAPI.APP_ID_AI.toString());
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("image", img64);
		bodys.put("mode", "1");
		//计算SIGN
		String sign = TencentAISignSort.getSignature(bodys);
		bodys.put("sign", sign);
		String param = TencentAISignSort.getParams(bodys);
		String result = HttpUtil.post("https://api.ai.qq.com/fcgi-bin/face/face_detectface", param);
		System.out.println(result);
	}
}
