package com.xs.face;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xs.common.APIContants;
import com.xs.pojo.BDAccessToken;
import com.xs.pojo.face.FaceDetectBean;
import com.xs.pojo.face.FaceDetectBean;
import com.xs.util.baidu.AIUtil;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtils;

public class FaceDetect {
	/*
	 * age,年龄
	 * beauty,美丑打分，范围0-100，越大表示越美。
	 * expression,表情，0，不笑；1，微笑；2，大笑。
	 * faceshape,脸型置信度。face_fields包含faceshape时返回
	 * gender,性别，male男、female女
	 * glasses,是否带眼镜，0-无眼镜，1-普通眼镜，2-墨镜
	 * landmark,4个关键点位置，左眼中心、右眼中心、鼻尖、嘴中心。
	 * race,所属种族  yellow、white、black、arabs(阿拉伯)。
	 * qualities 人脸质量信息
	 */
	/**
	 * 人脸检测URL
	 */
	public static String FACE_DETECT_URL="https://aip.baidubce.com/rest/2.0/face/v1/detect?access_token=ACCESS_TOKEN";
	
	public static void main(String[] args) throws Exception {
		AIUtil aiUtil = new AIUtil();
		//得到AccessToken
		BDAccessToken accessToken = aiUtil.getAccessToken("", "");
		String url = FACE_DETECT_URL.replace("ACCESS_TOKEN",accessToken.getAccess_token());
        String filePath ="G:/test2.jpg";
		String result = faceDetect(url,filePath,"1","age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities");
		System.out.println(result);
		JSON json = JSON.parseObject(result);
		FaceDetectBean bean = JSONObject.toJavaObject(json, FaceDetectBean.class);
		System.out.println("美丑打分:"+bean.getResult().get(0).getBeauty());
	}
	/**
	 * 所有参数人脸检测
	 * @param url  接口地址 包含了AccessToken
	 * @param filePath 图片路径数据，图片大小不超过2M。
	 * @param max_face_num 最多处理人脸数目，默认值1
	 * @param face_fields 包括age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities信息，逗号分隔，默认只返回人脸框、概率和旋转角度。
	 * @return
	 * @throws Exception 
	 */
	public static String faceDetect(String url,String filePath,String max_face_num,String face_fields) throws Exception {
		byte[] imgData = FileUtil.readFileByBytes(filePath);
        String imgStr = Base64Util.encode(imgData);
		Map<String, String> headers = new HashMap<String, String>(); 
		Map<String, String> bodys= new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		bodys.put("image", imgStr);
		bodys.put("max_face_num", max_face_num);
		bodys.put("face_fields", face_fields);
		HttpResponse responseData = HttpUtils.doPostBD(url, headers, bodys);
		String result = EntityUtils.toString(responseData.getEntity());
		return result;
	}
	/**
	 * 默认参数人脸检测
	 * @param url  接口地址 包含了AccessToken
	 * @param image base64编码后的图片数据，图片大小不超过2M。
	 * @return
	 * @throws Exception 
	 */
	public static String faceDetect(String url,String filePath) throws Exception {
		byte[] imgData = FileUtil.readFileByBytes(filePath);
        String imgStr = Base64Util.encode(imgData);
		Map<String, String> headers = new HashMap<String, String>(); 
		Map<String, String> bodys= new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		bodys.put("image", imgStr);
		HttpResponse responseData = HttpUtils.doPostBD(url,headers, bodys);
		String result = EntityUtils.toString(responseData.getEntity());
		return result;
	}
}
