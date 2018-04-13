package com.xs.unit;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xs.common.APIContants;
import com.xs.pojo.unit.UnitUtterance;
import com.xs.util.baidu.ParseUnitJson;
import com.xs.util.baidu.UnitHttpUtil;
/**
 * Unit对话接口JavaDemo示例
 * @author 小帅丶
 *
 */
public class UnitUtteranceSample {
	/**
	 * UNIT对话接口地址
	 */
	public static String UNITUTTERANCE_URL = "https://aip.baidubce.com/rpc/2.0/solution/v1/unit_utterance";
	public static void main(String[] args) throws Exception {
		UnitUtteranceSample sample = new UnitUtteranceSample();
		String result =sample.getUnitUtterance(5687, "成龙","5381c656abb14016ad5ac7199aa6ece10");
		System.out.println(ParseUnitJson.returnMsg(result));
	}
	/**
	 * Unit对话接口
	 * @param scene_id 场景ID
	 * @param query 当前轮用户的query
	 * @param session_id 第一次可传空，平台会返回session_id,如果想继续保持此次会话，则下次请求时传入此字段
	 * @return
	 * @throws Exception 
	 */
	public String getUnitUtterance(Integer scene_id,String query,String session_id) throws Exception{
		UnitUtterance unitUtterance = new UnitUtterance();
		unitUtterance.setScene_id(scene_id);
		unitUtterance.setQuery(query);
		unitUtterance.setSession_id(session_id);
		JSONObject params = JSONObject.fromObject(unitUtterance);
		String result = UnitHttpUtil.post(UNITUTTERANCE_URL, APIContants.UNIT_TOKEN, params.toString());
		return result;
		
	}
	/**
	 * Unit对话接口
	 * @param scene_id 场景ID
	 * @param query 当前轮用户的query
	 * @return
	 * @throws Exception 
	 */
	public String getUnitUtterance(Integer scene_id,String query) throws Exception{
		UnitUtterance unitUtterance = new UnitUtterance();
		unitUtterance.setScene_id(scene_id);
		unitUtterance.setQuery(query);
		JSONObject params = JSONObject.fromObject(unitUtterance);
		String result = UnitHttpUtil.post(UNITUTTERANCE_URL, APIContants.UNIT_TOKEN, params.toString());
		return result;
	}
	
	
//	public static void main(String[] args) throws Exception {
//		//你自己的场景ID
//		Integer scene_id = 5687;
//		String query = "有没有好看的电影";
//		String session_id = "";
////		UnitHttpUtil httpUtil = new UnitHttpUtil();
//		HttpUtil httpUtil = new HttpUtil();
//		com.xs.pojo.unit.UnitUtterance unitUtterance = new com.xs.pojo.unit.UnitUtterance();
//		unitUtterance.setScene_id(scene_id);
//		unitUtterance.setSession_id("");
//		unitUtterance.setQuery(query);
//		JSONObject paramsObject = JSONObject.fromObject(unitUtterance);
//		System.out.println(paramsObject.toString());
//		String data = httpUtil.postUnit(UNITUTTERANCE_URL,APIContants.UNIT_TOKEN,paramsObject.toString());
////		String data = httpUtil.post(UNITUTTERANCE_URL,APIContants.UNIT_TOKEN, paramsObject.toString());
//		System.out.println(URLDecoder.decode(data, "UTF-8"));
////		HttpUtils httpUtils = new HttpUtils();
////		String data2 = httpUtils.sendPostUrl(UNITUTTERANCE_URL, params,"UTF-8");
////		System.out.println(data2);
//		
//	}
}
