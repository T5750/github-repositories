package com.xs.tencent.aai;

import java.util.HashMap;

import com.xs.tencent.HttpsUtil4Tencent;
import com.xs.tencent.TencentAPI;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;

public class AaiTTATest {
	public static void main(String[] args) throws Exception {
		//时间戳
		String time_stamp = System.currentTimeMillis()/1000+"";
		//合成的文本信息
		String text = "你好小帅开发者";
		//随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		HashMap<String, String> bodys = new HashMap<String, String>();
		bodys.put("app_id","1106471787");
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("speed","0");
		bodys.put("model_type", "0");
		bodys.put("text", text);
		//计算SIGN
		String sign = TencentAISignSort.getSignature(bodys);
		bodys.put("sign", sign);
		//拼接参数
		String param = TencentAISignSort.getParams(bodys);
		param = param.substring(0, param.length()-1);
		System.out.println(param);
		//接口返回数据
		String result = HttpsUtil4Tencent.getforVoice(TencentAPI.AAI_TTA, param);
		System.out.println(result);
		//返回保存的语音文件路径
//		String voicePath = HttpsUtil4Tencent.getMP3Voice(result);
//		System.out.println(voicePath);
	}
}
