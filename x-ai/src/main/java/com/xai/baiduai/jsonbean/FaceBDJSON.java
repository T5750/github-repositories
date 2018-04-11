package com.xai.baiduai.jsonbean;

import java.util.List;

/**
 * 百度人脸检测Java对象。
 * 属性不包含72个关键点
 * @author 小帅丶
 * @create 2018-01-09 16:53
 **/
public class FaceBDJSON {
    //人脸数目
    private int result_num;
    //人脸属性对象的集合
    private List<Result> result;
    //日志id
    private long log_id;
    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }
    public int getResult_num() {
        return result_num;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
    public List<Result> getResult() {
        return result;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }
    public long getLog_id() {
        return log_id;
    }
    //人脸属性对象的集合
    public static class Result {
        //人脸在图片中的位置
        private Location location;
        //人脸置信度，范围0-1
        private double face_probability;
        //人脸框相对于竖直方向的顺时针旋转角，[-180,180]
        private int rotation_angle;
        //三维旋转之左右旋转角[-90(左), 90(右)]
        private double yaw;
        //三维旋转之俯仰角度[-90(上), 90(下)]
        private double pitch;
        //平面内旋转角[-180(逆时针), 180(顺时针)]
        private double roll;
        //年龄。face_fields包含age时返回
        private double age;
        //美丑打分，范围0-100，越大表示越美。face_fields包含beauty时返回
        private double beauty;
        //表情，0，不笑；1，微笑；2，大笑。face_fields包含expression时返回
        private int expression;
        //表情置信度，范围0~1。face_fields包含expression时返回
        private double expression_probability;
        //脸型置信度。face_fields包含faceshape时返回
        private List<Faceshape> faceshape;
        //male、female。face_fields包含gender时返回
        private String gender;
        //性别置信度，范围[0~1]，face_fields包含gender时返回
        private double gender_probability;
        //是否带眼镜，0-无眼镜，1-普通眼镜，2-墨镜。face_fields包含glasses时返回
        private int glasses;
        //眼镜置信度，范围[0~1]，face_fields包含glasses时返回
        private double glasses_probability;
        //yellow、white、black、arabs。face_fields包含race时返回
        private String race;
        //人种置信度，范围[0~1]，face_fields包含race时返回
        private double race_probability;
        //人脸质量信息。face_fields包含qualities时返回
        private Qualities qualities;

        public void setLocation(Location location) {
            this.location = location;
        }

        public Location getLocation() {
            return location;
        }

        public void setFace_probability(double face_probability) {
            this.face_probability = face_probability;
        }

        public double getFace_probability() {
            return face_probability;
        }

        public void setRotation_angle(int rotation_angle) {
            this.rotation_angle = rotation_angle;
        }

        public int getRotation_angle() {
            return rotation_angle;
        }

        public void setYaw(double yaw) {
            this.yaw = yaw;
        }

        public double getYaw() {
            return yaw;
        }

        public void setPitch(double pitch) {
            this.pitch = pitch;
        }

        public double getPitch() {
            return pitch;
        }

        public void setRoll(double roll) {
            this.roll = roll;
        }

        public double getRoll() {
            return roll;
        }

        public void setAge(double age) {
            this.age = age;
        }

        public double getAge() {
            return age;
        }

        public void setBeauty(double beauty) {
            this.beauty = beauty;
        }

        public double getBeauty() {
            return beauty;
        }

        public void setExpression(int expression) {
            this.expression = expression;
        }

        public int getExpression() {
            return expression;
        }
        
        public double getExpression_probability() {
			return expression_probability;
		}

		public void setExpression_probability(double expression_probability) {
			this.expression_probability = expression_probability;
		}

		public void setFaceshape(List<Faceshape> faceshape) {
            this.faceshape = faceshape;
        }

        public List<Faceshape> getFaceshape() {
            return faceshape;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getGender() {
            return gender;
        }

        public void setGender_probability(double gender_probability) {
            this.gender_probability = gender_probability;
        }

        public double getGender_probability() {
            return gender_probability;
        }

        public void setGlasses(int glasses) {
            this.glasses = glasses;
        }

        public int getGlasses() {
            return glasses;
        }

        public void setGlasses_probability(double glasses_probability) {
            this.glasses_probability = glasses_probability;
        }

        public double getGlasses_probability() {
            return glasses_probability;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public String getRace() {
            return race;
        }

        public void setRace_probability(double race_probability) {
            this.race_probability = race_probability;
        }

        public double getRace_probability() {
            return race_probability;
        }

        public void setQualities(Qualities qualities) {
            this.qualities = qualities;
        }

        public Qualities getQualities() {
            return qualities;
        }

        /**
         * 人脸在图片中的位置
         */
        public static class Location {
            //人脸区域离左边界的距离
            private int left;
            //人脸区域离上边界的距离
            private int top;
            //人脸区域的宽度
            private int width;
            //人脸区域的高度
            private int height;
            public void setLeft(int left) {
                this.left = left;
            }
            public int getLeft() {
                return left;
            }

            public void setTop(int top) {
                this.top = top;
            }
            public int getTop() {
                return top;
            }

            public void setWidth(int width) {
                this.width = width;
            }
            public int getWidth() {
                return width;
            }

            public void setHeight(int height) {
                this.height = height;
            }
            public int getHeight() {
                return height;
            }

        }
        /**
         * 脸型置信度
         */
        public static class Faceshape {
            //脸型：square/triangle/oval/heart/round
            private String type;
            //置信度：0~1
            private double probability;
            public void setType(String type) {
                this.type = type;
            }
            public String getType() {
                return type;
            }

            public void setProbability(double probability) {
                this.probability = probability;
            }
            public double getProbability() {
                return probability;
            }

        }

        /**
         * 人脸质量信息
         */
        public static class Qualities {
            //人脸各部分遮挡的概率，范围[0~1]，0表示完整，1表示不完整
            private Occlusion occlusion;
            //人脸模糊程度，范围[0~1]，0表示清晰，1表示模糊
            private double blur;
            //	取值范围在[0~255],表示脸部区域的光照程度
            private int illumination;
            //人脸完整度，0或1, 0为人脸溢出图像边界，1为人脸都在图像边界内
            private int completeness;
            //真实人脸/卡通人脸置信度
            private Type type;
            public void setOcclusion(Occlusion occlusion) {
                this.occlusion = occlusion;
            }
            public Occlusion getOcclusion() {
                return occlusion;
            }

            public void setBlur(double blur) {
                this.blur = blur;
            }
            public double getBlur() {
                return blur;
            }

            public void setIllumination(int illumination) {
                this.illumination = illumination;
            }
            public int getIllumination() {
                return illumination;
            }

            public void setCompleteness(int completeness) {
                this.completeness = completeness;
            }
            public int getCompleteness() {
                return completeness;
            }

            public void setType(Type type) {
                this.type = type;
            }
            public Type getType() {
                return type;
            }

            /**
             * 真实人脸/卡通人脸置信度
             */
            public static class Type {
                //真实人脸置信度，[0~1]，大于0.5可以判断为人脸
                private double human;
                //卡通人脸置信度，[0~1]
                private double cartoon;
                public void setHuman(double human) {
                    this.human = human;
                }
                public double getHuman() {
                    return human;
                }

                public void setCartoon(double cartoon) {
                    this.cartoon = cartoon;
                }
                public double getCartoon() {
                    return cartoon;
                }

            }
            /**
             * 人脸各部分遮挡的概率
             */
            public static class Occlusion {
                //左眼遮挡比例
                private double left_eye;
                //右眼遮挡比例
                private double right_eye;
                //鼻子遮挡比例
                private double nose;
                //嘴巴遮挡比例
                private double mouth;
                //左脸颊遮挡比例
                private double left_cheek;
                //右脸颊遮挡比例
                private double right_cheek;
                //下巴遮挡比例
                private double chin;
				public double getLeft_eye() {
					return left_eye;
				}
				public void setLeft_eye(double left_eye) {
					this.left_eye = left_eye;
				}
				public double getRight_eye() {
					return right_eye;
				}
				public void setRight_eye(double right_eye) {
					this.right_eye = right_eye;
				}
				public double getNose() {
					return nose;
				}
				public void setNose(double nose) {
					this.nose = nose;
				}
				public double getMouth() {
					return mouth;
				}
				public void setMouth(double mouth) {
					this.mouth = mouth;
				}
				public double getLeft_cheek() {
					return left_cheek;
				}
				public void setLeft_cheek(double left_cheek) {
					this.left_cheek = left_cheek;
				}
				public double getRight_cheek() {
					return right_cheek;
				}
				public void setRight_cheek(double right_cheek) {
					this.right_cheek = right_cheek;
				}
				public double getChin() {
					return chin;
				}
				public void setChin(double chin) {
					this.chin = chin;
				}
            }
        }
    }
    }
