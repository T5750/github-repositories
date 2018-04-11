package com.xai.baiduai.jsonbean;

import java.util.List;

/**
 * 图像识别接口返回的JSON对象。统一封装一个
 *
 * @author 小帅丶
 * @create 2018-02-07 17:24
 **/
public class ImageClassifyBDJSON {
    //唯一的log id，用于问题定位
    private long log_id;
    //识别结果数，标识返回结果数目
    private int result_num;
    //返回结果数组
    private List<Result> result;
    //颜色
    private String color_result;
    private LocationResult location_result;

    public LocationResult getLocation_result() {
        return location_result;
    }

    public void setLocation_result(LocationResult location_result) {
        this.location_result = location_result;
    }

    public String getColor_result() {
        return color_result;
    }

    public void setColor_result(String color_result) {
        this.color_result = color_result;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public static class Result{
        //识别的名称
        private String name;
        //卡路里-菜品识别返回
        private double calorie;
        //置信度
        private double probability;
        //置信度
        private double score;
        //年份-车型识别返回
        private String year;
        //类型-品牌识别返回
        private int type;
        //位置信息-品牌识别返回
        private Location location;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getCalorie() {
            return calorie;
        }

        public void setCalorie(double calorie) {
            this.calorie = calorie;
        }

        public double getProbability() {
            return probability;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
    }
    public static class Location{
        //坐起像素位置
        private int left;
        //上起像素位置
        private int top;
        //像素宽
        private int width;
        //像素高
        private int height;
        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
    public static class LocationResult{
        private int left;
        private int top;
        private int width;
        private int height;
        public int getLeft() {
            return left;
        }
        public void setLeft(int left) {
            this.left = left;
        }
        public int getTop() {
            return top;
        }
        public void setTop(int top) {
            this.top = top;
        }
        public int getWidth() {
            return width;
        }
        public void setWidth(int width) {
            this.width = width;
        }
        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }

    }
}
