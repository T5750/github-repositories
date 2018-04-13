package com.xs.tencent.ptu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.xs.tencent.HttpsUtil4Tencent;
import com.xs.tencent.TencentAPI;
import com.xs.tencent.sign.TencentAISign;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
//人脸变装示例代码
public class FaceDecoration {
	public static void main(String[] args) throws Exception {
		// 时间戳
		String time_stamp = System.currentTimeMillis() / 1000 + "";
		// 图片的二进制数组数据
		byte[] imageData = FileUtil.readFileByBytes("G:/car.jpg");
		// 图片的base64编码数据
		String img64 = Base64Util.encode(imageData);
		// 随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		String sign = TencentAISign.appSignBaseAI4FaceDecoration(TencentAPI.APP_ID_AI,nonce_str,img64,time_stamp, 8);
		System.out.println(sign);
		Map<String, String> headers = new HashMap<String, String>();
		Map<String, String> bodys = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		bodys.put("app_id",TencentAPI.APP_ID_AI.toString());
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("sign", sign);
		bodys.put("image", img64);
		bodys.put("decoration", "8");
		HttpResponse responseBD = HttpsUtil4Tencent.doPostTencentAI(TencentAPI.FACE_DECORATION, headers, bodys);
		System.out.println(EntityUtils.toString(responseBD.getEntity()));
		//返回的image进行处理后 首加 data:image/jpg;base64,即可
	}
}
