package com.xs.pojo.image;

import java.util.List;

/**
 * 百度LOGO返回的json转java对象实体
 * @author 小帅丶
 *
 */
public class Logo {
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
		private Integer type;
        private String name;
        private double probability;
        private LocationBD location;
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
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
		public LocationBD getLocation() {
			return location;
		}
		public void setLocation(LocationBD location) {
			this.location = location;
		}
		
    }
	public static class LocationBD {
		private Integer width;
		private Integer top;
		private Integer left;
		private Integer height;
		public Integer getWidth() {
			return width;
		}
		public void setWidth(Integer width) {
			this.width = width;
		}
		public Integer getTop() {
			return top;
		}
		public void setTop(Integer top) {
			this.top = top;
		}
		public Integer getLeft() {
			return left;
		}
		public void setLeft(Integer left) {
			this.left = left;
		}
		public Integer getHeight() {
			return height;
		}
		public void setHeight(Integer height) {
			this.height = height;
		}
		
	}
}
