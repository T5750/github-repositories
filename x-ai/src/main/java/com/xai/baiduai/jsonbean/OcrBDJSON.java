package com.xai.baiduai.jsonbean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 接口返回的OCR对象
 *
 * @author 小帅丶
 * @create 2018-03-26 17:00
 **/
public class OcrBDJSON {
    //唯一的log id，用于问题定位
    private Long log_id;
    //识别结果数
    private int words_result_num;
    //图像方向
    private int direction;
    /**
     * normal-识别正常
     * reversed_side-身份证正反面颠倒
     * non_idcard-上传的图片中不包含身份证
     * blurred-身份证模糊
     * other_type_card-其他类型证照
     * over_exposure-身份证关键字段反光或过曝
     * unknown-未知状态
     */
    private String image_status;
    //定位和识别结果数组
    private List<WordsResult> words_result;
    private Result result;
    //检测身份证是否被编辑过
    private String edit_tool;
    //识别身份证类型: normal-正常身份证；copy-复印件；temporary-临时身份证；screen-翻拍；unknow-其他未知情况
    private String risk_type;
    //为了友好解析，使用了FastJson对源数据key中文的解析

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

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

    public List<WordsResult> getWords_result() {
        return words_result;
    }

    public void setWords_result(List<WordsResult> words_result) {
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
        @JSONField(name = "证号")
        private CardNum cardNum;
        @JSONField(name = "有效期限")
        private ExpiraDate expiraDate;
        @JSONField(name = "准驾车型")
        private DriverModel driverModel;
        @JSONField(name = "有效起始日期")
        private ExpiraBeginDate expiraBeginDate;
        @JSONField(name = "国籍")
        private Nationality nationality;
        @JSONField(name = "出生日期")
        private BirthDate birthDate;
        @JSONField(name = "初次领证日期")
        private FirstIssueDate firstIssueDate;
        @JSONField(name = "品牌型号")
        private BrandModel brandModel;
        @JSONField(name = "发证日期")
        private DateIssue dateIssue;
        @JSONField(name = "使用性质")
        private UseProperty useProperty;
        @JSONField(name = "发动机号码")
        private EngineNum engineNum;
        @JSONField(name = "号牌号码")
        private NumPlate numPlate;
        @JSONField(name = "所有人")
        private PosseMan posseMan;
        @JSONField(name = "注册日期")
        private CreateDate createDate;
        @JSONField(name = "车辆识别代号")
        private VIN vin;
        @JSONField(name = "车辆类型")
        private VehicleType vehicleType;
        @JSONField(name = "单位名称")
        private UnitName unitName;
        @JSONField(name = "法人")
        private LegalPerson legalPerson;
        @JSONField(name = "有效期")
        private TermValidity termValidity;
        @JSONField(name = "证件编号")
        private IdNum idNum;
        @JSONField(name = "社会信用代码")
        private SocialCreditCode socialCreditCode;
        public String words;
        private Location location;

        public UnitName getUnitName() {
            return unitName;
        }

        public void setUnitName(UnitName unitName) {
            this.unitName = unitName;
        }

        public LegalPerson getLegalPerson() {
            return legalPerson;
        }

        public void setLegalPerson(LegalPerson legalPerson) {
            this.legalPerson = legalPerson;
        }

        public TermValidity getTermValidity() {
            return termValidity;
        }

        public void setTermValidity(TermValidity termValidity) {
            this.termValidity = termValidity;
        }

        public IdNum getIdNum() {
            return idNum;
        }

        public void setIdNum(IdNum idNum) {
            this.idNum = idNum;
        }

        public SocialCreditCode getSocialCreditCode() {
            return socialCreditCode;
        }

        public void setSocialCreditCode(SocialCreditCode socialCreditCode) {
            this.socialCreditCode = socialCreditCode;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public static class UnitName {
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
        public static class LegalPerson {
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
        public static class TermValidity {
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
        public static class IdNum {
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
        public static class SocialCreditCode {
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
        public BrandModel getBrandModel() {
            return brandModel;
        }

        public void setBrandModel(BrandModel brandModel) {
            this.brandModel = brandModel;
        }

        public DateIssue getDateIssue() {
            return dateIssue;
        }

        public void setDateIssue(DateIssue dateIssue) {
            this.dateIssue = dateIssue;
        }

        public UseProperty getUseProperty() {
            return useProperty;
        }

        public void setUseProperty(UseProperty useProperty) {
            this.useProperty = useProperty;
        }

        public EngineNum getEngineNum() {
            return engineNum;
        }

        public void setEngineNum(EngineNum engineNum) {
            this.engineNum = engineNum;
        }

        public NumPlate getNumPlate() {
            return numPlate;
        }

        public void setNumPlate(NumPlate numPlate) {
            this.numPlate = numPlate;
        }

        public PosseMan getPosseMan() {
            return posseMan;
        }

        public void setPosseMan(PosseMan posseMan) {
            this.posseMan = posseMan;
        }

        public CreateDate getCreateDate() {
            return createDate;
        }

        public void setCreateDate(CreateDate createDate) {
            this.createDate = createDate;
        }

        public VIN getVin() {
            return vin;
        }

        public void setVin(VIN vin) {
            this.vin = vin;
        }

        public VehicleType getVehicleType() {
            return vehicleType;
        }

        public void setVehicleType(VehicleType vehicleType) {
            this.vehicleType = vehicleType;
        }

        public ExpiraDate getExpiraDate() {
            return expiraDate;
        }

        public void setExpiraDate(ExpiraDate expiraDate) {
            this.expiraDate = expiraDate;
        }

        public ExpiraBeginDate getExpiraBeginDate() {
            return expiraBeginDate;
        }

        public void setExpiraBeginDate(ExpiraBeginDate expiraBeginDate) {
            this.expiraBeginDate = expiraBeginDate;
        }

        public CardNum getCardNum() {
            return cardNum;
        }

        public void setCardNum(CardNum cardNum) {
            this.cardNum = cardNum;
        }

        public DriverModel getDriverModel() {
            return driverModel;
        }

        public void setDriverModel(DriverModel driverModel) {
            this.driverModel = driverModel;
        }

        public Nationality getNationality() {
            return nationality;
        }

        public void setNationality(Nationality nationality) {
            this.nationality = nationality;
        }

        public BirthDate getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(BirthDate birthDate) {
            this.birthDate = birthDate;
        }

        public FirstIssueDate getFirstIssueDate() {
            return firstIssueDate;
        }

        public void setFirstIssueDate(FirstIssueDate firstIssueDate) {
            this.firstIssueDate = firstIssueDate;
        }

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

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }
    }
    public static class BrandModel {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class DateIssue {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class UseProperty {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class EngineNum {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class NumPlate {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class PosseMan {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class CreateDate {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class VIN {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class VehicleType {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }


    public static class CardNum {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class ExpiraDate {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class DriverModel {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class ExpiraBeginDate {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class Nationality {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class BirthDate {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
        }
    }
    public static class FirstIssueDate {
        private String words;
        public void setWords(String words) {
            this.words = words;
        }
        public String getWords() {
            return words;
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
    public static class Result{
        private String bank_card_number;
        private String bank_name;
        private Integer bank_card_type;

        public String getBank_card_number() {
            return bank_card_number;
        }

        public void setBank_card_number(String bank_card_number) {
            this.bank_card_number = bank_card_number;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public Integer getBank_card_type() {
            return bank_card_type;
        }

        public void setBank_card_type(Integer bank_card_type) {
            this.bank_card_type = bank_card_type;
        }
    }
}
