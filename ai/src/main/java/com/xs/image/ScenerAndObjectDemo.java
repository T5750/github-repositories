package com.xs.image;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.xs.common.image.ImageAPI;
import com.xs.pojo.image.ScenerAndObject;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 通用物体&场景识别
 * @author 小帅丶
 * 通用物体及场景识别 输入一张图片 输出图片中的多个物体及场景标签
 */
public class ScenerAndObjectDemo {
	public static void main(String[] args) throws Exception {
		//返回字符串
		String result = getScenerAndObjectResult("图片路径", "自己应用apikey&sercetkey生成的AccessToken");
		System.out.println(result);
		//返回java对象
//		ScenerAndObject scenerAndObject = getPlantBean("图片路径", "自己应用apikey&sercetkey生成的AccessToken");
//		System.out.println(scenerAndObject.getResult().get(0).getKeyword());
	}
	/**
	 * 通用物体&场景识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return 字符串
	 * @throws Exception
	 */
	public static String getScenerAndObjectResult(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.SCENERANDOBJECT_API, accessToken, param);
        System.out.println(result);
        return result;
	}
	/**
	 * 通用物体&场景识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return scenerAndObject对象
	 * @throws Exception
	 */
	public static ScenerAndObject getScenerAndObjectBean(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.SCENERANDOBJECT_API, accessToken, param);
		ScenerAndObject scenerAndObject = JSON.parseObject(result,ScenerAndObject.class);
        System.out.println(result);
        return scenerAndObject;
	}
}
