package com.xs.tencent.nlp.texttrans;

import java.util.HashMap;

import com.xs.tencent.TencentAPI;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;
import com.xs.util.baidu.HttpUtil;

/**
 * 文本翻译（AI Lab）
 * @author 小帅丶
 * 2018年2月9日
 */
public class NLPTextTransSample {
	public static void main(String[] args){
		try {
		//时间戳
		String time_stamp = System.currentTimeMillis()/1000+"";
		String text = new String("小帅开发者");
		//随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		//计算SIGN
		HashMap<String, String> bodys = new HashMap<String, String>();
		bodys.put("app_id",TencentAPI.APP_ID_AI.toString());
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("text", text);
		bodys.put("type", "0");
		String sign = TencentAISignSort.getSignature(bodys);
		bodys.put("sign", sign);
		String params = TencentAISignSort.getParams(bodys);
		System.out.println(params);
		//如果乱码。请把HttpUtil的post方法中判断接口包含nlp更改编码格式的代码注释掉
		String result = HttpUtil.post(TencentAPI.NLP_TEXTTRANS, params);
		System.out.println(result);
		} catch (Exception e) {
			System.out.println("错误信息:"+e.getMessage());
		}
	}
}
