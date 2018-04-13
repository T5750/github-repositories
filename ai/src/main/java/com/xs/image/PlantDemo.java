package com.xs.image;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.xs.common.image.ImageAPI;
import com.xs.pojo.image.Plant;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;

/**
 * 植物识别
 * @author 小帅丶
 *
 */
public class PlantDemo {
	public static void main(String[] args) throws Exception {
		//返回字符串
//		String result = getPlantResult("图片路径", "自己应用apikey&sercetkey生成的AccessToken");
//		System.out.println(result);
		//返回java对象
//		Plant plant = getPlantBean("图片路径", "自己应用apikey&sercetkey生成的AccessToken");
//		System.out.println(plant.getResult().get(0).getName());
	}
	/**
	 * 植物识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return 字符串
	 * @throws Exception
	 */
	public static String getPlantResult(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.PLANT_API, accessToken, param);
        System.out.println(result);
        return result;
	}
	/**
	 * 植物识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return LOGO对象
	 * @throws Exception
	 */
	public static Plant getPlantBean(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.PLANT_API, accessToken, param);
		Plant plant = JSON.parseObject(result,Plant.class);
        System.out.println(result);
        return plant;
	}
}
