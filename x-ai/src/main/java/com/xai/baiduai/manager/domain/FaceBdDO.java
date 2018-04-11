package com.xai.baiduai.manager.domain;

/**
 * 百度人脸检测对象
 *
 * @author 小帅丶
 * @create 2018-01-09 16:42
 **/
public class FaceBdDO {
    //主键ID
    public int faceId;
    //日志id
    private long logId;
    //人脸数目
    private int resultNum;
    //年龄
    private String age;
    //美丑打分
    private String beauty;
    //人脸区域离左边界的距离
    private String left;
    //人脸区域离上边界的距离
    private String top;
    //人脸区域的宽度
    private String width;
    //人脸区域的高度
    private String height;
    //人脸置信度，范围0-1
    private String faceProbability;
    //人脸框相对于竖直方向的顺时针旋转角，[-180,180]
    private String rotationAngle;
    //三维旋转之左右旋转角[-90(左), 90(右)]
    private String yaw;
    //三维旋转之俯仰角度[-90(上), 90(下)]
    private String pitch;
    //平面内旋转角[-180(逆时针), 180(顺时针)]
    private String roll;
    //表情，0，不笑；1，微笑；2，大笑
    private String expression;
    //表情置信度
    private String expressionProbability;
    //脸型 只存最大值
    private String type;
    //置信度：0~1 只存最大值
    private String probability;
    //male、female
    private String gender;
    //性别置信度，范围[0~1]
    private String genderProbability;
    //是否带眼镜，0-无眼镜，1-普通眼镜，2-墨镜
    private String glasses;
    //眼镜置信度，范围[0~1]
    private String glassesProbability;
    //yellow、white、black、arabs
    private String race;
    //人种置信度，范围[0~1]
    private String raceProbability;
    //左眼遮挡比例
    private String leftEye;
    //右眼遮挡比例
    private String rightEye;
    //鼻子遮挡比例
    private String nose;
    //嘴巴遮挡比例
    private String mouth;
    //左脸颊遮挡比例
    private String leftCheek;
    //右脸颊遮挡比例
    private String rightCheek;
    //下巴遮挡比例
    private String chin;
    //人脸模糊程度，范围[0~1]，0表示清晰，1表示模糊
    private String blur;
    //	取值范围在[0~255],表示脸部区域的光照程度
    private String illumination;
    //人脸完整度，0或1, 0为人脸溢出图像边界，1为人脸都在图像边界内
    private String completeness;
    //真实人脸置信度，[0~1]，大于0.5可以判断为人脸
    private String human;
    //卡通人脸置信度，[0~1]
    private String cartoon;
    //图片的base64数据
    private String imageBase64;

    public int getFaceId() {
        return faceId;
    }

    public void setFaceId(int faceId) {
        this.faceId = faceId;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public int getResultNum() {
        return resultNum;
    }

    public void setResultNum(int resultNum) {
        this.resultNum = resultNum;
    }
    public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBeauty() {
		return beauty;
	}

	public void setBeauty(String beauty) {
		this.beauty = beauty;
	}

	public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFaceProbability() {
        return faceProbability;
    }

    public void setFaceProbability(String faceProbability) {
        this.faceProbability = faceProbability;
    }

    public String getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(String rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public String getYaw() {
        return yaw;
    }

    public void setYaw(String yaw) {
        this.yaw = yaw;
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpressionProbability() {
		return expressionProbability;
	}

	public void setExpressionProbability(String expressionProbability) {
		this.expressionProbability = expressionProbability;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGenderProbability() {
        return genderProbability;
    }

    public void setGenderProbability(String genderProbability) {
        this.genderProbability = genderProbability;
    }

    public String getGlasses() {
        return glasses;
    }

    public void setGlasses(String glasses) {
        this.glasses = glasses;
    }

    public String getGlassesProbability() {
        return glassesProbability;
    }

    public void setGlassesProbability(String glassesProbability) {
        this.glassesProbability = glassesProbability;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getRaceProbability() {
        return raceProbability;
    }

    public void setRaceProbability(String raceProbability) {
        this.raceProbability = raceProbability;
    }

    public String getLeftEye() {
        return leftEye;
    }

    public void setLeftEye(String leftEye) {
        this.leftEye = leftEye;
    }

    public String getRightEye() {
        return rightEye;
    }

    public void setRightEye(String rightEye) {
        this.rightEye = rightEye;
    }

    public String getNose() {
        return nose;
    }

    public void setNose(String nose) {
        this.nose = nose;
    }

    public String getMouth() {
        return mouth;
    }

    public void setMouth(String mouth) {
        this.mouth = mouth;
    }

    public String getLeftCheek() {
        return leftCheek;
    }

    public void setLeftCheek(String leftCheek) {
        this.leftCheek = leftCheek;
    }

    public String getRightCheek() {
        return rightCheek;
    }

    public void setRightCheek(String rightCheek) {
        this.rightCheek = rightCheek;
    }

    public String getChin() {
        return chin;
    }

    public void setChin(String chin) {
        this.chin = chin;
    }

    public String getBlur() {
        return blur;
    }

    public void setBlur(String blur) {
        this.blur = blur;
    }

    public String getIllumination() {
        return illumination;
    }

    public void setIllumination(String illumination) {
        this.illumination = illumination;
    }

    public String getCompleteness() {
        return completeness;
    }

    public void setCompleteness(String completeness) {
        this.completeness = completeness;
    }

    public String getHuman() {
        return human;
    }

    public void setHuman(String human) {
        this.human = human;
    }

    public String getCartoon() {
        return cartoon;
    }

    public void setCartoon(String cartoon) {
        this.cartoon = cartoon;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
    
}
