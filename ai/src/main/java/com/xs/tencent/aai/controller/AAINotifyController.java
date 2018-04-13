package com.xs.tencent.aai.controller;

import java.io.BufferedReader;




import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xs.tencent.aai.bean.TXNotify;
import com.xs.tencent.aai.bean.TXResponse;
/**
 * 腾讯AI 长语音识别回调方法
 * @author 小帅丶
 * 开发者们根据自己的实际情况进行修改哦。
 * 注释掉的注解请自行引入根据自己的实际项目架构来。
 * 鄙人是springmvc 本项目只做示例代码。就不引入springmvc相关的jar了
 */
//@Controller
public class AAINotifyController {
	/**
	 * 长语音识别回调方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
//	@RequestMapping(value="/txnotify",method={RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
//	@ResponseBody
	public String TxNotify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		TXResponse txResponse = null;
		InputStream inputStream;
		String result = "";
		String response_result = "";
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			inputStream = request.getInputStream();
			String s = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			while ((s = in.readLine()) != null) {
				result = result + s;
			}
			System.out.println("tx接收到的数据==="+result);
			TXNotify notify = JSONObject.toJavaObject(JSON.parseObject(result), TXNotify.class);
			if(notify.getRet()==0){
				txResponse = new TXResponse();
				txResponse.setMsg("ok");
				txResponse.setRet(0);
				result = JSONObject.toJSONString(txResponse);
				System.out.println("返回的数据==="+result);
				return result;
			}else{
				txResponse = new TXResponse();
				txResponse.setMsg("fail");
				txResponse.setRet(1);
				result = JSONObject.toJSONString(txResponse);
				System.out.println("返回的数据==="+result);
				return result;
			}
		} catch (Exception e) {
			System.out.println("====TX回调出错了"+e.getMessage()+"=========响应结果"+response_result);
			txResponse = new TXResponse();
			txResponse.setMsg("error");
			txResponse.setRet(5);
			result = JSONObject.toJSONString(txResponse);
			System.out.println("返回的数据==="+result);
			return result;
		}
	}
}
