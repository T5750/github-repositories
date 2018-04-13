package com.xs.image;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.xs.common.image.ImageAPI;
import com.xs.pojo.image.BodyAttrBean;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;

public class BodyAttrSample {
	public static void main(String[] args) throws Exception {
		String lower_color = "";
		//返回字符串
//		String result = getBodyAttrResult("G:/girl.jpg", "自己的token");
//		System.out.println(result);
		//返回java对象
		BodyAttrBean bodyAttrBean = getBodyAttrean("G:/girl.jpg", "自己的token");
		if(bodyAttrBean.getPerson_info().get(0).getAttributes().getLower_color()==null){
			lower_color="未能分析出";
		}else{
			lower_color=bodyAttrBean.getPerson_info().get(0).getAttributes().getLower_color().getName();
		}
		System.out.println("图中有位:"+bodyAttrBean.getPerson_info().get(0).getAttributes().getAge().getName()+"-"+bodyAttrBean.getPerson_info().get(0).getAttributes().getGender().getName()
				+",上身穿着:"+bodyAttrBean.getPerson_info().get(0).getAttributes().getUpper_wear().getName()
				+",下身穿着:"+bodyAttrBean.getPerson_info().get(0).getAttributes().getLower_wear().getName()
				+",上衣颜色:"+bodyAttrBean.getPerson_info().get(0).getAttributes().getUpper_color().getName()
				+",下衣颜色:"+lower_color
				+",动作姿态:"+bodyAttrBean.getPerson_info().get(0).getAttributes().getAction().getName());
	}
	/**
	 * 人体属性识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return 字符串
	 * @throws Exception
	 */
	public static String getBodyAttrResult(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8")+"&type=gender,age,action, hair_length, bag,lower_wear,upper_wear, headwear,glasses,upper_color, lower_color,smoke,cellphone";;
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.BODYATTR_API, accessToken, param);
        System.out.println(result);
        return result;
	}
	/**
	 * 人体属性识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return CarMode对象
	 * @throws Exception
	 */
	public static BodyAttrBean getBodyAttrean(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8")+"&type=gender,age,action, hair_length, bag,lower_wear,upper_wear, headwear,glasses,upper_color, lower_color,smoke,cellphone";
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.BODYATTR_API, accessToken, param);
		BodyAttrBean bodyAttrBean = JSON.parseObject(result,BodyAttrBean.class);
        System.out.println(result);
        return bodyAttrBean;
	}
}
