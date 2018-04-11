package com.xai.baiduai.manager.domain;

/**
 * 图像识别数据库对象
 *
 * @author 小帅丶
 * @create 2018-02-07 15:27
 **/
public class ImageClassifyBdDO {
    //主键id
    private int icrId;
    //唯一的log id，用于问题定位
    private long logId;
    //返回结果数目，及result数组中的元素个数
    private int resultNum;
    //名称
    private String name;
    //卡路里
    private String calorie;
    //识别结果中每一行的置信度值，0-1
    private String probability;
    //置信度
    private String score;
    //坐起像素位置
    private String left;
    //上起像素位置
    private String top;
    //像素宽
    private String width;
    //像素高
    private String height;
    //
    private String type;
    //接口类型
    private String apiType;
    //图片路径
    private String imagePath;
    //颜色
    private String colorResult;
    //年份
    private String year;
    public int getIcrId() {
        return icrId;
    }

    public void setIcrId(int icrId) {
        this.icrId = icrId;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public int getResultNum() {
        return resultNum;
    }

    public void setResultNum(int resultNum) {
        this.resultNum = resultNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getColorResult() {
        return colorResult;
    }

    public void setColorResult(String colorResult) {
        this.colorResult = colorResult;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
