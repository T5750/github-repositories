package com.xs.pojo.nlp;

import java.util.List;
/**
 * 通用类文本反作弊 java对象
 * @author 小帅丶
 */
public class SpamBean {
	//服务内部计算返回标识
	private int errno;
	//数据集
    private Result result;
    //调用生成的唯一标识码，用于问题定位
    private long logid;
    
    public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public long getLogid() {
		return logid;
	}

	public void setLogid(long logid) {
		this.logid = logid;
	}

	public static class Result {
    	//请求中是否包含违禁，0表示非违禁，1表示违禁，2表示建议人工复审
        private int spam;
        //请求中的违禁类型集合，一个或多个，审核通过则为空
        private List<Integer> labels;
        //命中的违禁词集合，可能为空
        private List<String> hit;
		public int getSpam() {
			return spam;
		}
		public void setSpam(int spam) {
			this.spam = spam;
		}
		public List<Integer> getLabels() {
			return labels;
		}
		public void setLabels(List<Integer> labels) {
			this.labels = labels;
		}
		public List<String> getHit() {
			return hit;
		}
		public void setHit(List<String> hit) {
			this.hit = hit;
		}
        
    }
}
