package com.xai.baiduai.jsonbean;

/**
 * 百度响应基类
 *
 * @author 小帅丶
 * @create 2018-02-02 11:51
 **/
public class BdResponse {
    private  String code;
    private  String msg;
    private  String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
