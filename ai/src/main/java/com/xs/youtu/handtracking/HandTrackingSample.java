package com.xs.youtu.handtracking;

import com.alibaba.fastjson.JSON;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.youtu.common.HandTrackEnum;
import com.xs.youtu.common.YouTuAPIContants;
import com.xs.youtu.common.YouTuAppContants;
import com.xs.youtu.pojo.HandTrackingBean;
import com.xs.youtu.util.HttpUtil4YouTu;
import com.xs.youtu.util.YouTuSign;
/**
 * 手势识别示例代码
 * @author 小帅丶
 *
 */
public class HandTrackingSample {
	public static void main(String[] args) throws Exception {
		String imagePath = "G:/ht.jpg";
//		System.out.println(getHandTracking(imagePath));
		HandTrackingBean handTrackingBean = getHandTrackingBean(imagePath);
		System.out.println("手势为:"+HandTrackEnum.getLabelName(handTrackingBean.getItems().get(0).getLabel()));
		
	}
	/**
	 * 返回字符串
	 * @param imagePath 图片路径
	 * @return
	 * @throws Exception
	 */
	public static String getHandTracking(String imagePath) throws Exception{
		//获取SIGN值
		String sign = YouTuSign.getSign(YouTuAppContants.userQQ,
				YouTuAppContants.AppID, YouTuAppContants.SecretID,
				YouTuAppContants.SecretKey);
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
		String image = Base64Util.encode(imgData);
		String params = "{\"app_id\":\""+YouTuAppContants.AppID+"\",\"image\":\""+image+"\"}";
		String result = HttpUtil4YouTu.post(YouTuAPIContants.HANDTRACKING_CLASSIFY, params, sign);
		return result;
	}
	/**
	 * 返回HandWritingOCRBean
	 * @param imagePath 图片路径
	 * @return
	 * @throws Exception
	 */
	public static HandTrackingBean getHandTrackingBean(String imagePath) throws Exception{
		//获取SIGN值
		String sign = YouTuSign.getSign(YouTuAppContants.userQQ,
				YouTuAppContants.AppID, YouTuAppContants.SecretID,
				YouTuAppContants.SecretKey);
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
		String image = Base64Util.encode(imgData);
		String params = "{\"app_id\":\""+YouTuAppContants.AppID+"\",\"image\":\""+image+"\"}";
		String result = HttpUtil4YouTu.post(YouTuAPIContants.HANDTRACKING_CLASSIFY, params, sign);
		HandTrackingBean handTrackingBean = JSON.parseObject(result, HandTrackingBean.class);
		return handTrackingBean;
	}
}
