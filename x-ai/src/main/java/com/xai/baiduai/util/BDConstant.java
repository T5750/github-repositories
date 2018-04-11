package com.xai.baiduai.util;

import com.xai.baiduai.restapi.common.BDContants;

/**
 * ${evaluate}
 *
 * @author 小帅丶
 * @create 2018-02-02 13:08
 **/
public enum  BDConstant {
    BD_SUCCESS(0,"成功"),
    BD_NOFACE(1,"图中不包含人脸"),
    BD_ERROR(5,"失败");
    private  Integer code;
    private  String  msg;

    BDConstant(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
