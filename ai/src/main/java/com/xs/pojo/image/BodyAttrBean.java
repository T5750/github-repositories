package com.xs.pojo.image;

import java.util.List;

/**
 * 人体属性识别
 * @author 小帅丶
 *
 */
public class BodyAttrBean {
	//人体数目
    private int person_num;
    //人体姿态信息
    private List<Person_info> person_info;
    //唯一的log id，用于问题定位
    private long log_id;
    
    public int getPerson_num() {
		return person_num;
	}
	public void setPerson_num(int person_num) {
		this.person_num = person_num;
	}
	public List<Person_info> getPerson_info() {
		return person_info;
	}
	public void setPerson_info(List<Person_info> person_info) {
		this.person_info = person_info;
	}
	public long getLog_id() {
		return log_id;
	}
	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}
	public static class Person_info{
    	  private Attributes attributes;
    	  private Location location;
		public Attributes getAttributes() {
			return attributes;
		}
		public void setAttributes(Attributes attributes) {
			this.attributes = attributes;
		}
		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
    	  
    }
    public static class Attributes{
    	private Gender gender;
    	private Age age;
    	private Action action;
    	private Upper_color upper_color;
    	private Lower_color lower_color;
    	private Hair_length hair_length;
    	private Smoke smoke;
    	private Cellphone cellphone;
        private Lower_wear lower_wear;
        private Upper_wear upper_wear;
        private Headwear headwear;
        private Glasses glasses;
		public Hair_length getHair_length() {
			return hair_length;
		}
		public void setHair_length(Hair_length hair_length) {
			this.hair_length = hair_length;
		}
		public Headwear getHeadwear() {
			return headwear;
		}
		public void setHeadwear(Headwear headwear) {
			this.headwear = headwear;
		}
		public Lower_color getLower_color() {
			return lower_color;
		}
		public void setLower_color(Lower_color lower_color) {
			this.lower_color = lower_color;
		}
		public Cellphone getCellphone() {
			return cellphone;
		}
		public void setCellphone(Cellphone cellphone) {
			this.cellphone = cellphone;
		}
		public Gender getGender() {
			return gender;
		}
		public void setGender(Gender gender) {
			this.gender = gender;
		}
		public Age getAge() {
			return age;
		}
		public void setAge(Age age) {
			this.age = age;
		}
		public Smoke getSmoke() {
			return smoke;
		}
		public void setSmoke(Smoke smoke) {
			this.smoke = smoke;
		}
		public Lower_wear getLower_wear() {
			return lower_wear;
		}
		public void setLower_wear(Lower_wear lower_wear) {
			this.lower_wear = lower_wear;
		}
		public Action getAction() {
			return action;
		}
		public void setAction(Action action) {
			this.action = action;
		}
		public Glasses getGlasses() {
			return glasses;
		}
		public void setGlasses(Glasses glasses) {
			this.glasses = glasses;
		}
		public Upper_wear getUpper_wear() {
			return upper_wear;
		}
		public void setUpper_wear(Upper_wear upper_wear) {
			this.upper_wear = upper_wear;
		}
		public Upper_color getUpper_color() {
			return upper_color;
		}
		public void setUpper_color(Upper_color upper_color) {
			this.upper_color = upper_color;
		}
        
  }
  public static class Cellphone{
	  private double score;
	  private String name;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
  }
  public static class Hair_length{
	  private double score;
	  private String name;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
  }
  public static class Headwear{
	  private double score;
	  private String name;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
  }
  public static class Gender{
	  private double score;
	  private String name;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
  }
  public static class Age{
	  private double score;
	  private String name;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
  }
  public static class Smoke{
	  private double score;
	  private String name;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
  }
  public static class Lower_wear{
	  private double score;
	  private String name;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
  }
  public static class Lower_color{
	  private double score;
	  private String name;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
  }
  public static class Action{
	  private double score;
	  private String name;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
  }
  public static class Glasses{
	  private double score;
	  private String name;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
  }
  public static class Upper_wear{
	  private double score;
	  private String name;
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
  }
  public static class Upper_color{
	  private double score;
	  private String name;
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	  
  }
   public static class Location{
    	private int width;
	    private int top;
	    private int height;
	    private int left;
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public int getTop() {
			return top;
		}
		public void setTop(int top) {
			this.top = top;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public int getLeft() {
			return left;
		}
		public void setLeft(int left) {
			this.left = left;
		}
	    
  }
}
