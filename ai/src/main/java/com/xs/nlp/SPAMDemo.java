package com.xs.nlp;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.xs.pojo.nlp.SpamBean;
import com.xs.pojo.nlp.SpamLables;
import com.xs.util.baidu.HttpUtil;

/**
 * 文本审核-通用类文本反作弊接口示例代码-JavaAPI
 * @author 小帅丶
 * 2018年1月19日
 */
public class SPAMDemo {
	/**
	 * 通用类文本反作弊接口地址
	 */
	public static String SPAM_URL = "https://aip.baidubce.com/rest/2.0/antispam/v1/spam";
	public static void main(String[] args) throws Exception {
		String content = "我的QQ是123456789";
		//输出字符串内容
		String result = getSpamResult(content,"自己的token");
		System.out.println(result);
//		SpamBean SpamBean = getSpamBean(content, "自己的token");
//		int spam = SpamBean.getResult().getSpam();
//		//0表示非违禁，1表示违禁，2表示建议人工复审
//		if(spam==0){
//			System.out.println(content+"   文本内容审核通过");
//		}else if(spam==1){
//			System.out.println(content+"   文本内容未通过属于"+getLableMsg(SpamBean.getResult().getLabels().get(0)));
//		}else{
//			System.out.println(content+"   文本内容建议人工审核");
//		}

	}
	/**
	 * 通用类文本反作弊接口
	 * @param text
	 * @param accessToken
	 * @return data
	 * @throws Exception
	 */
	public static String getSpamResult(String content,String accessToken) throws Exception {
		String url = SPAM_URL;	
		//策略定制标识，可支持线下渠道定制，通用默认值为500071
		String param = "content="+URLEncoder.encode(content,"UTF-8")+"&command_no=500071";
		String data = HttpUtil.post(url, accessToken, param);
		return data;
	}
	/**
	 * 通用类文本反作弊接口
	 * @param text
	 * @param accessToken
	 * @return data
	 * @throws Exception
	 */
	public static SpamBean getSpamBean(String content,String accessToken) throws Exception {
		String url = SPAM_URL;	
		String param = "content="+URLEncoder.encode(content,"UTF-8")+"&command_no=500071";
		String data = HttpUtil.post(url, accessToken, param);
		SpamBean SpamBean = JSON.parseObject(data,SpamBean.class);
		return SpamBean;
	}
	/**
	 * 返回没审核过的文本违禁labels类型描述
	 * @param labels
	 * @return 
	 */
	public static String getLableMsg(Integer labels){
		String labelsmsg = "";
		if(labels==SpamLables.ONE.getLablesint()){
			labelsmsg = SpamLables.ONE.getLabelsmsg();
		}else if (labels==SpamLables.TWO.getLablesint()) {
			labelsmsg = SpamLables.TWO.getLabelsmsg();
		}else if (labels==SpamLables.THERE.getLablesint()) {
			labelsmsg = SpamLables.THERE.getLabelsmsg();
		}else if (labels==SpamLables.FOUR.getLablesint()) {
			labelsmsg = SpamLables.FOUR.getLabelsmsg();
		}else{
			labelsmsg = SpamLables.FIVE.getLabelsmsg();
		}
		return labelsmsg;
	}
}
