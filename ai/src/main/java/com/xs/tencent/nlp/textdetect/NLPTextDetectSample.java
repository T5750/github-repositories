package com.xs.tencent.nlp.textdetect;

import java.util.HashMap;

import com.xs.tencent.TencentAPI;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;
import com.xs.util.baidu.HttpUtil;
/**
 * 识别给出文本的语种
 * @author 小帅丶
 *
 */
public class NLPTextDetectSample {
	public static void main(String[] args){
		try {
		//时间戳
		String time_stamp = System.currentTimeMillis()/1000+"";
		String text = new String("こんにちは");
		//随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		//计算SIGN
		HashMap<String, String> bodys = new HashMap<String, String>();
		bodys.put("app_id",TencentAPI.APP_ID_AI.toString());
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("text", text);
		bodys.put("force", "0");
		String sign = TencentAISignSort.getSignature4TransText(bodys);
		bodys.put("sign", sign);
		String params = TencentAISignSort.getParams(bodys);
		System.out.println(params);
		//如果乱码。请把HttpUtil的post方法中判断接口包含nlp更改编码格式的代码注释掉
		String result = HttpUtil.post(TencentAPI.NLP_TEXTDETECT, params);
		System.out.println(result);
		//{  "ret": 0,  "msg": "ok",  "data": {	  "lang": "jp"  }}
		} catch (Exception e) {
			System.out.println("错误信息:"+e.getMessage());
		}
	}
}
