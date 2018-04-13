package com.xs.pojo.nlp;

import java.util.List;
/**
 * NLP对象
 * @author 小帅丶
 * 2017年12月20日
 */
public class LexerAnalysisBean {
	 	private long log_id;
	    private String text;
	    private List<Items> items;
	    
	    public long getLog_id() {
			return log_id;
		}
		public void setLog_id(long log_id) {
			this.log_id = log_id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public List<Items> getItems() {
			return items;
		}
		public void setItems(List<Items> items) {
			this.items = items;
		}

		public static class Items{
	    	private List<String> loc_details;
    	    private int byte_offset;
    	    private String uri;
    	    private String pos;
    	    private String ne;
    	    private String item;
    	    private List<String> basic_words;
    	    private int byte_length;
    	    private String formal;
			public List<String> getLoc_details() {
				return loc_details;
			}
			public void setLoc_details(List<String> loc_details) {
				this.loc_details = loc_details;
			}
			public int getByte_offset() {
				return byte_offset;
			}
			public void setByte_offset(int byte_offset) {
				this.byte_offset = byte_offset;
			}
			public String getUri() {
				return uri;
			}
			public void setUri(String uri) {
				this.uri = uri;
			}
			public String getPos() {
				return pos;
			}
			public void setPos(String pos) {
				this.pos = pos;
			}
			public String getNe() {
				return ne;
			}
			public void setNe(String ne) {
				this.ne = ne;
			}
			public String getItem() {
				return item;
			}
			public void setItem(String item) {
				this.item = item;
			}
			public List<String> getBasic_words() {
				return basic_words;
			}
			public void setBasic_words(List<String> basic_words) {
				this.basic_words = basic_words;
			}
			public int getByte_length() {
				return byte_length;
			}
			public void setByte_length(int byte_length) {
				this.byte_length = byte_length;
			}
			public String getFormal() {
				return formal;
			}
			public void setFormal(String formal) {
				this.formal = formal;
			}
	    }
}
