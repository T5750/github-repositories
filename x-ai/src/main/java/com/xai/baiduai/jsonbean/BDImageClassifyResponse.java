package com.xai.baiduai.jsonbean;

/**
 * 图像识别返回页面的对象
 *
 * @author 小帅丶
 * @create 2018-02-08 11:09
 **/
public class BDImageClassifyResponse extends BdResponse{
    private String name;
    private String score;
    private String probability;
    private String calorie;
    private String year;
    private String colorResult;

    public String getColorResult() {
        return colorResult;
    }

    public void setColorResult(String colorResult) {
        this.colorResult = colorResult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
