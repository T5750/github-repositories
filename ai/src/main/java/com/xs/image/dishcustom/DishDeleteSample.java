package com.xs.image.dishcustom;

import java.net.URLEncoder;

import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;
/**
 * 自定义菜品删除
 * @author 小帅丶
 *
 */
public class DishDeleteSample {
		//菜品删除接口
		private static final String DISH_DELETE = "https://aip.baidubce.com/rest/2.0/image-classify/v1/realtime_search/dish/delete";
		//更换为自己应用的AccessToken
		static String token= "自己应用的AccessToken";
		//调用示例代码
		public static void main(String[] args) {
			//入库的图片本地路径
			String imagePath = "图片本地路径";
			//默认传appid
			String sub_lib="appid";
			String result = DishSearch(imagePath, token, sub_lib);
			System.out.println(new String(result));
		}
		/**
		 * 菜品删除
		 * @param path 图片路径
		 * @param token AccessToken
		 * @param sub_lib 默认传appid
		 * @return
		 */
		public static String DishSearch(String path,String token,String sub_lib) {
			String result="";
			try {
	            String filePath = path;
	            byte[] imgData = FileUtil.readFileByBytes(filePath);
	            String imgStr = Base64Util.encode(imgData);
	            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
	            String param = "image=" + imgParam+"&sub_lib="+sub_lib;
	            result = HttpUtil.post(DISH_DELETE, token, param);
	        } catch (Exception e) {
	        	result = e.getMessage();
	        }
	        return result;
	    }
}
