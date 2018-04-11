package com.xai.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 接口请求的输出工具类
 * 
 * @author 小帅丶
 * @类名称 PrintUtil
 * @remark
 * @date 2017-6-28
 */
public class PrintUtil {
	private static Logger logger = Logger.getLogger(PrintUtil.class);
	/**
	 * 输出为xml格式的数据
	 * 
	 * @param response
	 * @param result
	 */
	public static void printXml(HttpServletResponse response, String result) {
		try {
			response.setContentType("text/xml; charset=UTF-8");
			PrintWriter sos = response.getWriter();
			sos.write(result);
			sos.flush();
			sos.close();
		} catch (IOException e) {
			logger.info("printXml读取数据错误\t:"+e.getMessage());
		}
	}

	/**
	 * 采用json 或 jsonp
	 * 或者使用了angularJS
	 * @param callback
	 * @param response
	 * @param result
	 */
	public static void printJson(String callback, HttpServletResponse response,
			String result) {
		boolean jsonP = false;
		if (callback != null) {
			jsonP = true;
			response.setContentType("text/javascript;charset=utf-8");
		} else {
			response.setContentType("application/x-json;charset=utf-8");
		}
		try {
			Writer out = response.getWriter();
			if (jsonP) {
				out.write(callback + "(");
			}
			out.write(result.toString());
			if (jsonP) {
				out.write(");");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.info("回调函数为:"+callback+"\t printJson读取数据错误\t:"+e.getMessage());
		}

	}

	/**
	 * 非跨域请求 或者本地请求
	 * 
	 * @param response
	 * @param result
	 */
	public static void printJson(HttpServletResponse response, String result) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter sos = response.getWriter();
			sos.write(result);
			sos.flush();
			sos.close();
		} catch (IllegalStateException e) {
			logger.info("====用户取消了下载 "+e.getMessage());
		} catch (IOException e) {
			logger.info("printJson读取数据错误\t:"+e.getMessage());
		}
	}
	/**
	 * 非跨域请求 或者本地请求
	 * 
	 * @param response
	 * @param result
	 */
	public static void printJsonOut(HttpServletResponse response, String result) {
		try {
			response.setCharacterEncoding("UTF-8");
			ServletOutputStream sos = response.getOutputStream();
			sos.print(result);
			sos.flush();
			sos.close();
		} catch (IOException e) {
			logger.info("printJson读取数据错误\t:"+e.getMessage());
		}
	}
}
