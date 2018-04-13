package com.xs.pojo.image;

import java.util.List;
/**
 * 食材识别对象
 * @author 小帅丶
 *
 */
public class Ingredient {

    private Long log_id;
    private int result_num;
    private List<Result> result;
	public Long getLog_id() {
		return log_id;
	}
	public void setLog_id(Long log_id) {
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
	public static class Result {
	    private String name;
	    private double score;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
	}

}
