package com.xs.pojo.ocr.idcard;


import com.alibaba.fastjson.annotation.JSONField;

/**
 * 身份证Bean
 * @author 小帅丶
 *
 */
public class IdCardBean {
	/**
	 * direction 图像方向，当detect_direction=true时存在。-1:未定义，- 0:正向，- 1: 逆时针90度，- 2:逆时针180度，- 3:逆时针270度
     * image_status	normal-识别正常 reversed_side-身份证正反面颠倒 non_idcard-上传的图片中不包含身份证 blurred-身份证模糊 other_type_card-其他类型证照 over_exposure-身份证关键字段反光或过曝 unknown-未知状态
	 * risk_type 输入参数 detect_risk = true 时，则返回该字段识别身份证类型: normal-正常身份证；copy-复印件；temporary-临时身份证；screen-翻拍；unknow-其他未知情况
	 * edit_tool 如果参数 detect_risk = true 时，则返回此字段。如果检测身份证被编辑过，该字段指定编辑软件名称，如:Adobe Photoshop CC 2014 (Macintosh),如果没有被编辑过则返回值无此参数
	 * log_id 唯一的log id，用于问题定位
	 * words_result定位和识别结果数组
	 * words_result_num	识别结果数，表示words_result的元素个数
	 * +location	位置数组（坐标0点为左上角）
	 * ++left	表示定位位置的长方形左上顶点的水平坐标
	 * ++top	表示定位位置的长方形左上顶点的垂直坐标
	 * ++width	表示定位位置的长方形的宽度
	 * ++height 表示定位位置的长方形的高度
	 * +words	识别结果字符串
	 */
    private Long log_id;
    private int words_result_num;
    private int direction;
    private String image_status;
    private WordsResult words_result;
    private String edit_tool;
    private String risk_type;
	public Long getLog_id() {
		return log_id;
	}
	public void setLog_id(Long log_id) {
		this.log_id = log_id;
	}

	public int getWords_result_num() {
		return words_result_num;
	}

	public void setWords_result_num(int words_result_num) {
		this.words_result_num = words_result_num;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getImage_status() {
		return image_status;
	}

	public void setImage_status(String image_status) {
		this.image_status = image_status;
	}
	public WordsResult getWords_result() {
		return words_result;
	}
	public void setWords_result(WordsResult words_result) {
		this.words_result = words_result;
	}

	public String getEdit_tool() {
		return edit_tool;
	}
	public void setEdit_tool(String edit_tool) {
		this.edit_tool = edit_tool;
	}
	public String getRisk_type() {
		return risk_type;
	}
	public void setRisk_type(String risk_type) {
		this.risk_type = risk_type;
	}
	//为了友好解析，使用了FastJson对源数据key中文的解析
	public static class WordsResult {
    	@JSONField(name="住址")
        private Address address;
    	@JSONField(name="出生")
        private Birth birth;
    	@JSONField(name="姓名")
        private Name name;
    	@JSONField(name="公民身份号码")
        private IdCardNum idCardNum;
    	@JSONField(name="性别")
        private Sex sex;
    	@JSONField(name="民族")
        private Nation nation;
    	@JSONField(name="签发日期")
        private IssueDate issueDate;
    	@JSONField(name="签发机关")
        private Authority authority;
    	@JSONField(name="失效日期")
    	private ExpiryDate expiryDate;
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public Birth getBirth() {
			return birth;
		}
		public void setBirth(Birth birth) {
			this.birth = birth;
		}
		public Name getName() {
			return name;
		}
		public void setName(Name name) {
			this.name = name;
		}
		public IdCardNum getIdCardNum() {
			return idCardNum;
		}
		public void setIdCardNum(IdCardNum idCardNum) {
			this.idCardNum = idCardNum;
		}
		public Sex getSex() {
			return sex;
		}
		public void setSex(Sex sex) {
			this.sex = sex;
		}
		public Nation getNation() {
			return nation;
		}
		public void setNation(Nation nation) {
			this.nation = nation;
		}
		public IssueDate getIssueDate() {
			return issueDate;
		}
		public void setIssueDate(IssueDate issueDate) {
			this.issueDate = issueDate;
		}
		public Authority getAuthority() {
			return authority;
		}
		public void setAuthority(Authority authority) {
			this.authority = authority;
		}
		public ExpiryDate getExpiryDate() {
			return expiryDate;
		}
		public void setExpiryDate(ExpiryDate expiryDate) {
			this.expiryDate = expiryDate;
		}
    }
    public static class Address {
        private Location location;
        private String words;
        public void setLocation(Location location) {
             this.location = location;
         }
         public Location getLocation() {
             return location;
         }

        public void setWords(String words) {
             this.words = words;
         }
         public String getWords() {
             return words;
         }
    }
    public static class Birth {
        private Location location;
        private String words;
        public void setLocation(Location location) {
             this.location = location;
         }
         public Location getLocation() {
             return location;
         }

        public void setWords(String words) {
             this.words = words;
         }
         public String getWords() {
             return words;
         }
    }
    public static class Nation {
        private Location location;
        private String words;
        public void setLocation(Location location) {
             this.location = location;
         }
         public Location getLocation() {
             return location;
         }
        public void setWords(String words) {
             this.words = words;
         }
         public String getWords() {
             return words;
         }
    }
    public static class Sex {
        private Location location;
        private String words;
        public void setLocation(Location location) {
             this.location = location;
         }
         public Location getLocation() {
             return location;
         }

        public void setWords(String words) {
             this.words = words;
         }
         public String getWords() {
             return words;
         }
    }
    public static class IdCardNum {
        private Location location;
        private String words;
        public void setLocation(Location location) {
             this.location = location;
         }
         public Location getLocation() {
             return location;
         }

        public void setWords(String words) {
             this.words = words;
         }
         public String getWords() {
             return words;
         }
    }
    public static class Name {
        private Location location;
        private String words;
        public void setLocation(Location location) {
             this.location = location;
         }
         public Location getLocation() {
             return location;
         }

        public void setWords(String words) {
             this.words = words;
         }
         public String getWords() {
             return words;
         }
    }
    public static class IssueDate {
        private Location location;
        private String words;
        public void setLocation(Location location) {
             this.location = location;
         }
         public Location getLocation() {
             return location;
         }

        public void setWords(String words) {
             this.words = words;
         }
         public String getWords() {
             return words;
         }
    }
    public static class Authority {
        private Location location;
        private String words;
        public void setLocation(Location location) {
             this.location = location;
         }
         public Location getLocation() {
             return location;
         }

        public void setWords(String words) {
             this.words = words;
         }
         public String getWords() {
             return words;
         }
    }
    public static class ExpiryDate {
        private Location location;
        private String words;
        public void setLocation(Location location) {
             this.location = location;
         }
         public Location getLocation() {
             return location;
         }

        public void setWords(String words) {
             this.words = words;
         }
         public String getWords() {
             return words;
         }
    }
    public static class Location {
        private int width;
        private int top;
        private int height;
        private int left;
        public void setWidth(int width) {
             this.width = width;
         }
         public int getWidth() {
             return width;
         }

        public void setTop(int top) {
             this.top = top;
         }
         public int getTop() {
             return top;
         }

        public void setHeight(int height) {
             this.height = height;
         }
         public int getHeight() {
             return height;
         }

        public void setLeft(int left) {
             this.left = left;
         }
         public int getLeft() {
             return left;
         }
    }
}
