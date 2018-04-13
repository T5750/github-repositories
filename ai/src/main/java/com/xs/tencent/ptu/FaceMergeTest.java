package com.xs.tencent.ptu;

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
 * 人脸融合示例代码
 * @author 小帅丶
 *
 */
public class FaceMergeTest {
	public static void main(String[] args) throws Exception {
		// 时间戳
		String time_stamp = System.currentTimeMillis() / 1000 + "";
		// 图片的二进制数组数据
		byte[] imageData = FileUtil.readFileByBytes("G:/test2.jpg");
		// 图片的base64编码数据
		String img64 = Base64Util.encode(imageData);
		// 随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		HashMap<String, String> bodys = new HashMap<String, String>();
		bodys.put("app_id",TencentAPI.APP_ID_AI.toString());
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("image", img64);
		bodys.put("model", "12");
		//返回的image进行处理后 首加 data:image/jpg;base64,即可
		String sign = TencentAISignSort.getSignature(bodys);
		bodys.put("sign", sign);
		String params = TencentAISignSort.getParams(bodys);
		String result = HttpUtil.post(TencentAPI.FACE_MERGE, params);
		System.out.println(result);
	}
}
