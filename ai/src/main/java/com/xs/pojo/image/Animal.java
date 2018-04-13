package com.xs.pojo.image;

import java.util.List;

/**
 * 动物识别返回的对象
 * @author 小帅丶 or 7叔 
 * 具体含义:@see http://ai.baidu.com/docs#/ImageClassify-API/top
 */
public class Animal {
	private Long log_id;
	private List<Result> result;
	
	public Long getLog_id() {
		return log_id;
	}

	public void setLog_id(Long log_id) {
		this.log_id = log_id;
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public static class Result {
		private String score;
		private String name;
		public String getScore() {
			return score;
		}
		public void setScore(String score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
