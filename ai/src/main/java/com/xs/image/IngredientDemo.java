package com.xs.image;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.xs.common.image.ImageAPI;
import com.xs.pojo.image.Ingredient;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 食材识别
 * @author 小帅丶
 */
public class IngredientDemo {
	public static void main(String[] args) throws Exception {
		//返回字符串
		//getDishBean("图片本地路径", "用户自己的token");
		//返回java对象
		Ingredient ingredient  =getIngredientBean("G:/onion.jpg", "用户自己的token");
		System.out.println(ingredient.getResult().get(0).getName());
	}
	/**
	 * 食材识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return 字符串
	 * @throws Exception
	 */
	public static String getIngredientResult(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8") + "&top_num=5";
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.INGREDIENT_API, accessToken, param);
        System.out.println(result);
        return result;
	}
	/**
	 * 食材识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return Ingredient对象
	 * @throws Exception
	 */
	public static Ingredient getIngredientBean(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8") + "&top_num=5";
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.INGREDIENT_API, accessToken, param);
		Ingredient ingredient = JSON.parseObject(result,Ingredient.class);
        System.out.println(result);
        return ingredient;
	}
}
