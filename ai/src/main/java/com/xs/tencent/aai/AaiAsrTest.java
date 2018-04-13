package com.xs.tencent.aai;

import java.util.HashMap;

import com.xs.tencent.TencentAPI;
import com.xs.tencent.sign.Base64Util;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 语音识别-echo版示例代码
 * @author 小帅丶
 * 如果文件是MP3格式  转PCM文件请参考 com/xs/audio/tns/MP3ConvertPCM.java
 */
public class AaiAsrTest {
	public static void main(String[] args) throws Exception {
		//时间戳
		String time_stamp = System.currentTimeMillis()/1000+"";
		//语音文件的二进制数据 最好是PCM WAV AMR SILK
		byte[] mp3byte = FileUtil.readFileByBytes("C:/Users/Administrator/text2audio/VOICE1513237078.pcm");
		//语音文件的base64
		String mp3base64 = Base64Util.encode(mp3byte);
		//随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		HashMap<String, String> bodys = new HashMap<String, String>();
		bodys.put("app_id","自己的APPID");
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("format", "1");//PCM-1 WAV-2 AMR-3 SILK-4
		bodys.put("speech",mp3base64);
		bodys.put("rate","16000");//此参数可有可无
		//计算SIGN
		String sign = TencentAISignSort.getSignature(bodys);
		System.out.println(sign);
		bodys.put("sign", sign);
		//拼接参数
		String param = TencentAISignSort.getParams(bodys);
		//接口返回数据
		String result = HttpUtil.post(TencentAPI.AAI_ASR, param);
		System.out.println(result);
	}
}
