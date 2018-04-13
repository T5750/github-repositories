package com.xs.util.baidu;

import com.xs.pojo.unit.UnitReturnMsg;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 临时测试转换JSON的方法
 * @author 小帅丶
 *
 */
public class ParseUnitJson {
	/**
	 * 转换最终内容
	 * @param result
	 * @return
	 */
	public static String returnMsg(String result) {
		UnitReturnMsg msg = null;
		JSONObject object = JSONObject.fromObject(result);
		JSONArray jsonArray = object.getJSONObject("result").getJSONArray("action_list");
		JSONObject jsonObject = object.getJSONObject("result");
		String session_id = jsonObject.get("session_id").toString();
		JSONObject data = JSONObject.fromObject(jsonArray.toString().substring(1, jsonArray.toString().length()-1));
		String action_id = data.get("action_id").toString();
		String say = data.getString("say");
		msg = new UnitReturnMsg();
		msg.setAction_id(action_id);
		msg.setSay(say);
		msg.setSession_id(session_id);
		JSONObject returnMsg = JSONObject.fromObject(msg);
		return returnMsg.toString();
	}
}
