package com.xs.image;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.xs.common.image.ImageAPI;
import com.xs.pojo.image.Animal;
import com.xs.pojo.image.CarMode;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;

/**
 * 车型识别Demo
 * @author 小帅丶
 *
 */
public class CarModelDemo {
	public static void main(String[] args) throws Exception {
		//返回字符串
//		String result = getCarModeResult("图片路径", "自己应用apikey&sercetkey生成的AccessToken");
//		System.out.println(result);
		//返回java对象
		CarMode plant = getCarModeBean("图片路径", "自己应用apikey&sercetkey生成的AccessToken");
		System.out.println(plant.getResult().get(0).getName());
	}
	/**
	 * 车型识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return 字符串
	 * @throws Exception
	 */
	public static String getCarModeResult(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.CAR_API, accessToken, param);
        System.out.println(result);
        return result;
	}
	/**
	 * 车型识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return CarMode对象
	 * @throws Exception
	 */
	public static CarMode getCarModeBean(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.CAR_API, accessToken, param);
		CarMode carMode = JSON.parseObject(result,CarMode.class);
        System.out.println(result);
        return carMode;
	}
}
