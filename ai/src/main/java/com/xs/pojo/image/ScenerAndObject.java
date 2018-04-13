package com.xs.pojo.image;

import java.util.List;

/**
 * 通用物体&场景识别对象
 * @author 小帅丶
 *
 */
public class ScenerAndObject {
	private Long log_id;
	private int result_num;
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
	public int getResult_num() {
		return result_num;
	}

	public void setResult_num(int result_num) {
		this.result_num = result_num;
	}

	public static class Result {
		private double score;
		private String keyword;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
	}
}
