package com.xs.tencent.ptu;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.xs.tencent.HttpsUtil4Tencent;
import com.xs.tencent.TencentAPI;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;

/**
 * 人脸美妆
 * @author 小帅丶
 *
 */
public class FaceCosmeticTest {
	public static void main(String[] args) throws Exception {
		// 时间戳
		String time_stamp = System.currentTimeMillis() / 1000 + "";
		// 图片的二进制数组数据
		byte[] imageData = FileUtil.readFileByBytes("G:/test2.jpg");
		// 图片的base64编码数据
		String img64 = Base64Util.encode(imageData);
		// 随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("app_id", TencentAPI.APP_ID_AI.toString());
		params.put("time_stamp", time_stamp);
		params.put("nonce_str", nonce_str);
		params.put("image", img64);
		params.put("cosmetic", "1");
		String sign = TencentAISignSort.getSignature(params);
		params.put("sign", sign);
		String param = TencentAISignSort.getParams(params);
		String result = HttpUtil.post(TencentAPI.FACE_COSMETIC, param);
		System.out.println(result);
	}

}
