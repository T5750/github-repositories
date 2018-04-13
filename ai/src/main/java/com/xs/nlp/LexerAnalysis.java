package com.xs.nlp;

import java.util.List;
import com.alibaba.fastjson.JSON;
import com.xs.common.APIContants;
import com.xs.pojo.nlp.LexerAnalysisBean;
import com.xs.pojo.nlp.LexerAnalysisBean.Items;
import com.xs.util.baidu.HttpUtil;
/**
 * 自然语言处理-词法分析
 * @author 小帅丶
 *
 */
public class LexerAnalysis {
	/**
	 * 词法分析接口地址
	 */
	public static String LEXERANALYSIS_URL = "https://aip.baidubce.com/rpc/2.0/nlp/v1/lexer";
	public static void main(String[] args) throws Exception {
		//输出字符串内容
//		String result = getLexerAnalysisResult("百度是一家互联网公司", APIContants.NLP_TOKEN);
//		System.out.println(result);
		LexerAnalysisBean lexerAnalysisBean = getLexerAnalysisBean("百度是一家互联网公司", APIContants.NLP_TOKEN);
		List<Items> items = lexerAnalysisBean.getItems();
		for (int i = 0; i < items.size(); i++) {
			//输出Items对象中basic_words内容
			System.out.println(items.get(i).getBasic_words());
		}
	}
	/**
	 * 词法分析接口
	 * @param text
	 * @param accessToken
	 * @return data
	 * @throws Exception
	 */
	public static String getLexerAnalysisResult(String text,String accessToken) throws Exception {
		String url_param = "?access_token="+accessToken;
		String url = LEXERANALYSIS_URL+url_param;	
		String param = "{\"text\":\""+text+"\"}";
		String data = HttpUtil.postNLP(url, param);
		return data;
	}
	/**
	 * 词法分析接口
	 * @param text
	 * @param accessToken
	 * @return data
	 * @throws Exception
	 */
	public static LexerAnalysisBean getLexerAnalysisBean(String text,String accessToken) throws Exception {
		String url_param = "?access_token="+accessToken;
		String url = LEXERANALYSIS_URL+url_param;	
		String param = "{\"text\":\""+text+"\"}";
		String data = HttpUtil.postNLP(url, param);
		LexerAnalysisBean lexerAnalysisBean = JSON.parseObject(data,LexerAnalysisBean.class);
		return lexerAnalysisBean;
	}
}
