package com.xs.face;

import java.net.URLEncoder;

import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 人脸对比示例代码
 * @author 小帅丶
 * Java-API
 */
public class FaceMatchSample {
	//接口地址
	public static String FACE_MATCH ="https://aip.baidubce.com/rest/2.0/face/v2/match";
	public static void main(String[] args) {
		String filePath1 = "G:/32801.jpg";
        String filePath2 = "G:/32802.bmp";
        String token = "自己的token";
		System.out.println(FaceMatch(filePath1,filePath2,token));
	}
	/**
	 * 人脸对比示例代码
	 * @param path1 图片本地路径1
	 * @param path2 图片本地路径1
	 * @param token AccessToken
	 * @return
	 */
	public static String FaceMatch(String path1,String path2,String token) {
        try {
            // 本地文件路径
            String filePath1 = path1;
            String filePath2 = path2;
            byte[] imgData1 = FileUtil.readFileByBytes(filePath1);
            byte[] imgData2 = FileUtil.readFileByBytes(filePath2);
            String imgStr1 = Base64Util.encode(imgData1);
            String imgStr2 = Base64Util.encode(imgData2);
            String imgParam1 = URLEncoder.encode(imgStr1, "UTF-8");
            String imgParam2 = URLEncoder.encode(imgStr2, "UTF-8");
            String param = "images=" + imgParam1 + "," + imgParam2;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = token;
            String result = HttpUtil.post(FACE_MATCH, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
