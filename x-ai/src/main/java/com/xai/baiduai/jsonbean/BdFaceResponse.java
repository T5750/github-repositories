package com.xai.baiduai.jsonbean;

/**
 * ${evaluate}
 *
 * @author 小帅丶
 * @create 2018-02-02 11:54
 **/
public class BdFaceResponse extends BdResponse{
    //年龄
    private String age;
    //美丑
    private String beauty;
    //是否带眼睛
    private String glasses;
    //表情
    private String expression;
    //卡通
    private String cartoon;
    //性别
    private String gender;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBeauty() {
        return beauty;
    }

    public void setBeauty(String beauty) {
        this.beauty = beauty;
    }

    public String getGlasses() {
        return glasses;
    }

    public void setGlasses(String glasses) {
        this.glasses = glasses;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getCartoon() {
        return cartoon;
    }

    public void setCartoon(String cartoon) {
        this.cartoon = cartoon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
