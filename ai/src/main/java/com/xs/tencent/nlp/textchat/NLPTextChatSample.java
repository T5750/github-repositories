package com.xs.tencent.nlp.textchat;

import java.util.HashMap;

import com.xs.tencent.TencentAPI;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;
import com.xs.util.baidu.HttpUtil;
/**
 * 腾讯AI 基础闲聊 接口示例代码
 * @author 小帅丶
 * @date 2018年
 */
public class NLPTextChatSample {
	public static void main(String[] args) throws Exception {
		//时间戳
		String time_stamp = System.currentTimeMillis()/1000+"";
		String text = new String("北京今天天气不错呀");
		//随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		//计算SIGN
		HashMap<String, String> bodys = new HashMap<String, String>();
		bodys.put("app_id",TencentAPI.APP_ID_AI.toString());
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("session",nonce_str);
		bodys.put("question", text);
		String sign = TencentAISignSort.getSignature(bodys);
		bodys.put("sign", sign);
		String params = TencentAISignSort.getParams(bodys);
		System.out.println(params);
		//如果乱码。请把HttpUtil的post方法中判断接口包含nlp更改编码格式的代码注释掉
		String result = HttpUtil.post(TencentAPI.NLP_TEXTCHAT, params);
		System.out.println(result);
	}
}
