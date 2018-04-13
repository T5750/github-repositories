package com.xs.tencent.pojo;

import java.util.List;

/**
 * 多人脸检测
 * @author 小帅丶
 *
 */
public class MultiFaceBean {
    private int ret;
    private String msg;
    private Data data;
    public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public static class Data{
    	 private List<Face_list> face_list;
    	 
    	 public List<Face_list> getFace_list() {
			return face_list;
		}

		public void setFace_list(List<Face_list> face_list) {
			this.face_list = face_list;
		}

		public static class Face_list{
		    private double x1;
		    private double y1;
		    private double x2;
		    private double y2;
			public double getX1() {
				return x1;
			}
			public void setX1(double x1) {
				this.x1 = x1;
			}
			public double getY1() {
				return y1;
			}
			public void setY1(double y1) {
				this.y1 = y1;
			}
			public double getX2() {
				return x2;
			}
			public void setX2(double x2) {
				this.x2 = x2;
			}
			public double getY2() {
				return y2;
			}
			public void setY2(double y2) {
				this.y2 = y2;
			}
		    
    	 }
    }
}
