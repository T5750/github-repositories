package com.xs.ocr;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.xs.pojo.ocr.idcard.IdCardBean;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 身份证识别示例Demo Java-API方式
 * @author 小帅丶
 * @date 2017年11月16日
 */
public class IDCard2Text {
	/**
	 * 身份证识别接口API
	 */
	public static String IDCARD_URL ="https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
	public static void main(String[] args) throws Exception {
		//返回字符串
//		String result = getIDCradResult("图片路径", "自己的AccessToken","front：身份证正面；back：身份证背面");
//		System.out.println(result);
		//返回对象
		IdCardBean idCardBean = getIDCradFrontBean("图片路径", "自己的AccessToken","front：身份证正面；back：身份证背面");
		System.out.println("签发机关:"+idCardBean.getWords_result().getAuthority().getWords());
	}
	/**
	 * 身份证识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @param id_card_side front：身份证正面；back：身份证背面
	 * @return 字符串
	 * @throws Exception
	 */
	public static String getIDCradResult(String imagePath,String accessToken,String id_card_side) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8")+"&id_card_side="+id_card_side+"&detect_direction=true&detect_risk=true";
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(IDCARD_URL, accessToken, param);
        System.out.println(result);
        return result;
	}
	/**
	 * 身份证识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @param id_card_side front：身份证正面；back：身份证背面
	 * @return 对象
	 * @throws Exception
	 */
	public static IdCardBean getIDCradFrontBean(String imagePath,String accessToken,String id_card_side) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8")+"&id_card_side="+id_card_side+"&detect_direction=true&detect_risk=true";
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(IDCARD_URL, accessToken, param);
		IdCardBean idCardFront = JSON.parseObject(result,IdCardBean.class);
        System.out.println(result);
        return idCardFront;
	}
}
