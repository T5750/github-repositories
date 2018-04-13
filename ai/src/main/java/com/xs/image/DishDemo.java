package com.xs.image;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.xs.common.image.ImageAPI;
import com.xs.pojo.image.Dish;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 菜品识别
 * @author 小帅丶
 */
public class DishDemo {
	public static void main(String[] args) throws Exception {
		//返回字符串
		//getDishBean("图片本地路径", "用户自己的token");
		//返回java对象
		Dish dish =getDishBean("G:car.jpg", "24.509012e6b7fd242cff7557c13dc436de.2592000.1512873097.282335-10131029");
		System.out.println(dish.getLog_id());
	}
	/**
	 * 菜品识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return 字符串
	 * @throws Exception
	 */
	public static String getDishResult(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8") + "&top_num=5";
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.DISH_API, accessToken, param);
        System.out.println(result);
        return result;
	}
	/**
	 * 菜品识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return Dish对象
	 * @throws Exception
	 */
	public static Dish getDishBean(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8") + "&top_num=5";
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.DISH_API, accessToken, param);
		Dish dish = JSON.parseObject(result,Dish.class);
        System.out.println(result);
        return dish;
	}
}
