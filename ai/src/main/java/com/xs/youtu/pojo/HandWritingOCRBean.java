package com.xs.youtu.pojo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 手写识别对应的JavaBean
 * @author 小帅丶
 *
 */
public class HandWritingOCRBean {
    private int errorcode;
    private String errormsg;
    private List<Items> items;
    private String session_id;
    @JSONField(name="class")
    private List<String> Classes;
    
    public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public List<String> getClasses() {
		return Classes;
	}
	public void setClasses(List<String> classes) {
		Classes = classes;
	}

	public static class Items{
        private Itemcoord itemcoord;
        private double itemconf;
        private String itemstring;
        private List<String> coords;
        private List<Words> words;
        private List<String> candword;
		public Itemcoord getItemcoord() {
			return itemcoord;
		}
		public void setItemcoord(Itemcoord itemcoord) {
			this.itemcoord = itemcoord;
		}
		public double getItemconf() {
			return itemconf;
		}
		public void setItemconf(double itemconf) {
			this.itemconf = itemconf;
		}
		public String getItemstring() {
			return itemstring;
		}
		public void setItemstring(String itemstring) {
			this.itemstring = itemstring;
		}
		public List<String> getCoords() {
			return coords;
		}
		public void setCoords(List<String> coords) {
			this.coords = coords;
		}
		public List<Words> getWords() {
			return words;
		}
		public void setWords(List<Words> words) {
			this.words = words;
		}
		public List<String> getCandword() {
			return candword;
		}
		public void setCandword(List<String> candword) {
			this.candword = candword;
		}
        
    }
    public static class Itemcoord{
        private int x;
        private int y;
        private int width;
        private int height;
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
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
    public static class Words{
        private String character;
        private double confidence;
		public String getCharacter() {
			return character;
		}
		public void setCharacter(String character) {
			this.character = character;
		}
		public double getConfidence() {
			return confidence;
		}
		public void setConfidence(double confidence) {
			this.confidence = confidence;
		}
        
    }
}
