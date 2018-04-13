package com.xs.pojo.imagecensor;

import java.util.List;
/**
 * 图像审核Java对象
 * @author 小帅丶
 *
 */
public class UserDefinedBean {
		//审核结果，成功才返回，失败不返回。可取值1.合规,2.疑似，3.不合规
	 	private String conclusion;
	 	//请求唯一id 
	    private long log_id;
	    //错误提示信息，失败才返回，成功不返回
	    private String error_msg;
	    //错误提示码，失败才返回，成功不返回
	    private long error_code;
	 	//审核项详细信息，响应成功并且conclusion为疑似或不合规时才返回，响应失败或conclusion为合规是不返回。
	 	private List<Data> data;
	    
	    public String getError_msg() {
			return error_msg;
		}
		public void setError_msg(String error_msg) {
			this.error_msg = error_msg;
		}
		public long getError_code() {
			return error_code;
		}
		public void setError_code(long error_code) {
			this.error_code = error_code;
		}
		public String getConclusion() {
			return conclusion;
		}
		public void setConclusion(String conclusion) {
			this.conclusion = conclusion;
		}
		public long getLog_id() {
			return log_id;
		}
		public void setLog_id(long log_id) {
			this.log_id = log_id;
		}
		public List<Data> getData() {
			return data;
		}
		public void setData(List<Data> data) {
			this.data = data;
		}
	    public static class Data{
	    	//内层错误提示信息，底层服务失败才返回，成功不返回
		    private String error_msg;
		    //内层错误提示码，底层服务失败才返回，成功不返回
		    private long error_code;
	    	//不合规项描述信息
	    	private String msg;
	    	//不合规项置信度
	    	private double probability;
	    	//审核类型，1：色情、2：性感、3：暴恐、4:恶心、5：水印码、6：二维码、7：条形码、8：政治人物、9：敏感词、10：自定义敏感词
	    	private int type;
	    	private List<Stars> stars;
			public List<Stars> getStars() {
				return stars;
			}
			public void setStars(List<Stars> stars) {
				this.stars = stars;
			}
			public String getError_msg() {
				return error_msg;
			}
			public void setError_msg(String error_msg) {
				this.error_msg = error_msg;
			}
			public long getError_code() {
				return error_code;
			}
			public void setError_code(long error_code) {
				this.error_code = error_code;
			}
			public String getMsg() {
				return msg;
			}
			public void setMsg(String msg) {
				this.msg = msg;
			}
			public double getProbability() {
				return probability;
			}
			public void setProbability(double probability) {
				this.probability = probability;
			}
			public int getType() {
				return type;
			}
			public void setType(int type) {
				this.type = type;
			}
			public static class Stars{
				//置信度
				private double probability;
				//政治人物名称
				private String name;
				public double getProbability() {
					return probability;
				}
				public void setProbability(double probability) {
					this.probability = probability;
				}
				public String getName() {
					return name;
				}
				public void setName(String name) {
					this.name = name;
				}
			}
	    }
}
