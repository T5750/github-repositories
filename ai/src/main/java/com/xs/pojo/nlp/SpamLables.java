package com.xs.pojo.nlp;
/**
 * 违禁labels类型（新版接口）
 * @author 小帅丶
 */
public enum SpamLables {
	ONE(1,"暴恐违禁"),
	TWO(2,"文本色情"),
	THERE(3,"政治敏感"),
	FOUR(4,"恶意推广"),
	FIVE(5,"低俗辱骂");
	private Integer lablesint;
	private String labelsmsg;

	private SpamLables(Integer lablesint, String labelsmsg) {
		this.lablesint = lablesint;
		this.labelsmsg = labelsmsg;
	}

	public Integer getLablesint() {
		return lablesint;
	}

	public void setLablesint(Integer lablesint) {
		this.lablesint = lablesint;
	}

	public String getLabelsmsg() {
		return labelsmsg;
	}

	public void setLabelsmsg(String labelsmsg) {
		this.labelsmsg = labelsmsg;
	}
	
}
