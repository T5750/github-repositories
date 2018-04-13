package com.xs.tencent.aai.bean;
/**
 * @author 小帅丶
 * @类名称  TXNotify
 * @remark 给接口响应接收数据的对象
 * @date  2018-1-15
 */
public class TXResponse {
	//0表示成功，非0表示出错
	public int ret;
	//返回信息；ret非0时表示出错时错误原因
	public String msg;
	/**
	 * @return the ret
	 */
	public int getRet() {
		return ret;
	}
	/**
	 * @param ret 
	 * ret
	 */
	public void setRet(int ret) {
		this.ret = ret;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg 
	 * msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
