package com.xs.image;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.xs.common.image.ImageAPI;
import com.xs.pojo.image.Logo;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * LOGO识别
 * @author 小帅丶
 *
 */
public class LogoDemo {
	public static void main(String[] args) throws Exception {
		//返回字符串
//		String result = getLogoResult("图片路径", "自己应用apikey&sercetkey生成的AccessToken");
//		System.out.println(result);
		//返回java对象
		Logo logo = getLogoBean("G:car.jpg", "24.509012e6b7fd242cff7557c13dc436de.2592000.1512873097.282335-10131029");
		System.out.println(logo.getResult().get(0).getName());
	}
	/**
	 * LOGO识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return 字符串
	 * @throws Exception
	 */
	public static String getLogoResult(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.LOGO_API, accessToken, param);
        System.out.println(result);
        return result;
	}
	/**
	 * LOGO识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return LOGO对象
	 * @throws Exception
	 */
	public static Logo getLogoBean(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(ImageAPI.LOGO_API, accessToken, param);
		Logo logo = JSON.parseObject(result,Logo.class);
        System.out.println(result);
        return logo;
	}
}
