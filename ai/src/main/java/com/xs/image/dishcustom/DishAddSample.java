package com.xs.image.dishcustom;

import java.net.URLEncoder;
import java.util.Date;

import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;




/**
 * 自定义菜品入库
 * @author 小帅丶
 *
 */
public class DishAddSample {
	private static final String DISH_ADD = "https://aip.baidubce.com/rest/2.0/image-classify/v1/realtime_search/dish/add";
	//更换为自己应用的AccessToken
	static String token= "应用的AccessToken";
	//调用示例代码
	public static void main(String[] args) {
		String id = new Date().getTime()/1000+"";
		//入库的图片本地路径
		String imagePath = "图片本地路径";
		//菜品名称摘要信息
		String brief = id;
		//默认传appid
		String sub_lib="appid";
		String result = DishAdd(imagePath, token, brief, sub_lib);
		System.out.println(result);
	}
	/**
	 * 
	 * @param path 图片路径
	 * @param token AccessToken
	 * @param brief 菜品名称摘要信息,检索时带回,不超过256B。由于检索后需要与本地数据库关联,建议 brief可传入图片在本地标记id、图片url、图片名称等
	 * @param sub_lib 默认传appid
	 * @return
	 */
	public static String DishAdd(String path,String token,String brief,String sub_lib) {
		String result="";
		try {
            String filePath = path;
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam+"&brief="+brief+"&sub_lib="+sub_lib;
            result = HttpUtil.post(DISH_ADD, token, param);
        } catch (Exception e) {
        	result = e.getMessage();
        }
        return result;
    }
}
