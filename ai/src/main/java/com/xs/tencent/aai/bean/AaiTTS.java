package com.xs.tencent.aai.bean;
/**
 * 语音合成返回数据对象
 * @author 小帅丶
 *
 */
public class AaiTTS {
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
		public Integer format;
		public String speech;
		public String md5sum;
		public Integer getFormat() {
			return format;
		}
		public void setFormat(Integer format) {
			this.format = format;
		}
		public String getSpeech() {
			return speech;
		}
		public void setSpeech(String speech) {
			this.speech = speech;
		}
		public String getMd5sum() {
			return md5sum;
		}
		public void setMd5sum(String md5sum) {
			this.md5sum = md5sum;
		}
	}
}
