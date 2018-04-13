package com.xs.pojo.image;

import java.util.List;

public class Dish {

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

	    private int calorie;
	    private boolean has_calorie;
	    private String name;
	    private double probability;
		public int getCalorie() {
			return calorie;
		}
		public void setCalorie(int calorie) {
			this.calorie = calorie;
		}
		public boolean isHas_calorie() {
			return has_calorie;
		}
		public void setHas_calorie(boolean has_calorie) {
			this.has_calorie = has_calorie;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getProbability() {
			return probability;
		}
		public void setProbability(double probability) {
			this.probability = probability;
		}
	}

}
