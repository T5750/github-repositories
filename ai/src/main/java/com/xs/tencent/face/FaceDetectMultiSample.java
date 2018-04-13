package com.xs.tencent.face;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xs.tencent.TencentAPI;
import com.xs.tencent.pojo.MultiFaceBean;
import com.xs.tencent.sign.TencentAISign;
import com.xs.tencent.sign.TencentAISignSort;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 腾讯AI 多人脸检测 	
 * @author 小帅丶
 * @date 2018年2月28日
 */
public class FaceDetectMultiSample {
	public static void main(String[] args) throws Exception {
		//时间戳
		String time_stamp = System.currentTimeMillis()/1000+"";
		//图片的二进制数组数据
		byte [] imageData = FileUtil.readFileByBytes("G:/dface.jpg");
		//图片的base64编码数据
		String img64 = Base64Util.encode(imageData);
		//随机字符串
		String nonce_str = TencentAISign.getRandomString(10);
		//计算SIGN
		HashMap<String, String> bodys = new HashMap<String, String>();
		bodys.put("app_id",TencentAPI.APP_ID_AI.toString());
		bodys.put("time_stamp",time_stamp);
		bodys.put("nonce_str", nonce_str);
		bodys.put("image", img64);
		String sign = TencentAISignSort.getSignature(bodys);
		bodys.put("sign", sign);
		String param = TencentAISignSort.getParams(bodys);
		String result = HttpUtil.post(TencentAPI.FACE_DETECTMULTI, param);
		MultiFaceBean bean = JSONObject.parseObject(result, MultiFaceBean.class);
		System.out.println("图中大概包含:"+bean.getData().getFace_list().size()+"个人脸");
	}
}
