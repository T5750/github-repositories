package com.xs.tencent.aai.bean;
/**
 * 语音合成返回数据对象-优图
 * @author 小帅丶
 *
 */
public class AaiTTA {
	public Integer ret;
	public String msg;
	public Data data;
	public Integer getRet() {
		return ret;
	}

	public void setRet(Integer ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public static class Data{
		public String voice;

		public String getVoice() {
			return voice;
		}

		public void setVoice(String voice) {
			this.voice = voice;
		}
		
	}
}
