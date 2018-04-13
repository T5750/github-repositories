package com.xs.tencent.aai.bean;
/**
 * @author 小帅丶
 * @类名称  TXNotify
 * @remark 回调返回的数据对象
 * @date  2018-1-15
 */
public class TXNotify {
	public Data data;
	//0表示成功，非0表示出错
	public int ret;
	//返回信息；ret非0时表示出错时错误原因
	public String msg;
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public static class Data{
		public String task_id;
		public String text;
		public String getTask_id() {
			return task_id;
		}
		public void setTask_id(String task_id) {
			this.task_id = task_id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}
}
