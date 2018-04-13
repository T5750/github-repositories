package com.xs.pojo.face;

import java.util.List;

/**
 * 人脸检测Javabean对象
 * 直接得到接口返回数据转成Java对象
 * @author 小帅丶
 */
public class FaceDetectBean {

    /**
     * result_num : 1
     * result : [{"location":{"left":139,"top":143,"width":96,"height":95},"face_probability":0.62063229084015,"rotation_angle":-2,"yaw":3.8685443401337,"pitch":4.7192459106445,"roll":-2.6061036586761,"landmark":[{"x":160,"y":160},{"x":206,"y":157},{"x":183,"y":185},{"x":186,"y":207}],"landmark72":[{"x":140,"y":165},{"x":142,"y":179},{"x":146,"y":192},{"x":152,"y":206},{"x":162,"y":219},{"x":174,"y":231},{"x":187,"y":236},{"x":203,"y":232},{"x":218,"y":219},{"x":228,"y":204},{"x":233,"y":189},{"x":236,"y":174},{"x":237,"y":158},{"x":151,"y":161},{"x":155,"y":158},{"x":160,"y":157},{"x":165,"y":158},{"x":169,"y":161},{"x":165,"y":162},{"x":160,"y":163},{"x":155,"y":162},{"x":160,"y":160},{"x":144,"y":149},{"x":149,"y":143},{"x":156,"y":142},{"x":164,"y":143},{"x":171,"y":148},{"x":163,"y":148},{"x":156,"y":148},{"x":150,"y":148},{"x":197,"y":160},{"x":201,"y":156},{"x":206,"y":155},{"x":211,"y":155},{"x":216,"y":158},{"x":212,"y":160},{"x":207,"y":161},{"x":201,"y":160},{"x":206,"y":157},{"x":191,"y":146},{"x":199,"y":141},{"x":207,"y":139},{"x":216,"y":140},{"x":223,"y":146},{"x":215,"y":145},{"x":207,"y":146},{"x":200,"y":146},{"x":176,"y":161},{"x":175,"y":171},{"x":174,"y":180},{"x":171,"y":190},{"x":178,"y":191},{"x":190,"y":190},{"x":196,"y":189},{"x":193,"y":179},{"x":191,"y":170},{"x":189,"y":161},{"x":183,"y":185},{"x":170,"y":206},{"x":177,"y":202},{"x":185,"y":200},{"x":194,"y":201},{"x":202,"y":205},{"x":196,"y":213},{"x":185,"y":216},{"x":176,"y":213},{"x":178,"y":205},{"x":185,"y":204},{"x":194,"y":204},{"x":194,"y":208},{"x":185,"y":209},{"x":178,"y":208}],"age":31.771087646484,"beauty":48.814964294434,"expression":0,"expression_probablity":0.99916577339172,"faceshape":[{"type":"square","probability":0.049275912344456},{"type":"triangle","probability":0.0012899816501886},{"type":"oval","probability":0.35565102100372},{"type":"heart","probability":0.4685298204422},{"type":"round","probability":0.12525326013565}],"gender":"male","gender_probability":0.99935287237167,"glasses":1,"glasses_probability":0.99944049119949,"race":"yellow","race_probability":0.9999885559082,"qualities":{"occlusion":{"left_eye":0,"right_eye":0,"nose":0,"mouth":0,"left_cheek":0,"right_cheek":0,"chin":0},"blur":0,"illumination":0,"completeness":0,"type":{"human":0.95982873439789,"cartoon":0.040171254426241}}}]
     * log_id : 1310282989
     */

    private int result_num;
    private int log_id;
    private List<ResultBean> result;

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * location : {"left":139,"top":143,"width":96,"height":95}
         * face_probability : 0.62063229084015
         * rotation_angle : -2
         * yaw : 3.8685443401337
         * pitch : 4.7192459106445
         * roll : -2.6061036586761
         * landmark : [{"x":160,"y":160},{"x":206,"y":157},{"x":183,"y":185},{"x":186,"y":207}]
         * landmark72 : [{"x":140,"y":165},{"x":142,"y":179},{"x":146,"y":192},{"x":152,"y":206},{"x":162,"y":219},{"x":174,"y":231},{"x":187,"y":236},{"x":203,"y":232},{"x":218,"y":219},{"x":228,"y":204},{"x":233,"y":189},{"x":236,"y":174},{"x":237,"y":158},{"x":151,"y":161},{"x":155,"y":158},{"x":160,"y":157},{"x":165,"y":158},{"x":169,"y":161},{"x":165,"y":162},{"x":160,"y":163},{"x":155,"y":162},{"x":160,"y":160},{"x":144,"y":149},{"x":149,"y":143},{"x":156,"y":142},{"x":164,"y":143},{"x":171,"y":148},{"x":163,"y":148},{"x":156,"y":148},{"x":150,"y":148},{"x":197,"y":160},{"x":201,"y":156},{"x":206,"y":155},{"x":211,"y":155},{"x":216,"y":158},{"x":212,"y":160},{"x":207,"y":161},{"x":201,"y":160},{"x":206,"y":157},{"x":191,"y":146},{"x":199,"y":141},{"x":207,"y":139},{"x":216,"y":140},{"x":223,"y":146},{"x":215,"y":145},{"x":207,"y":146},{"x":200,"y":146},{"x":176,"y":161},{"x":175,"y":171},{"x":174,"y":180},{"x":171,"y":190},{"x":178,"y":191},{"x":190,"y":190},{"x":196,"y":189},{"x":193,"y":179},{"x":191,"y":170},{"x":189,"y":161},{"x":183,"y":185},{"x":170,"y":206},{"x":177,"y":202},{"x":185,"y":200},{"x":194,"y":201},{"x":202,"y":205},{"x":196,"y":213},{"x":185,"y":216},{"x":176,"y":213},{"x":178,"y":205},{"x":185,"y":204},{"x":194,"y":204},{"x":194,"y":208},{"x":185,"y":209},{"x":178,"y":208}]
         * age : 31.771087646484
         * beauty : 48.814964294434
         * expression : 0
         * expression_probablity : 0.99916577339172
         * faceshape : [{"type":"square","probability":0.049275912344456},{"type":"triangle","probability":0.0012899816501886},{"type":"oval","probability":0.35565102100372},{"type":"heart","probability":0.4685298204422},{"type":"round","probability":0.12525326013565}]
         * gender : male
         * gender_probability : 0.99935287237167
         * glasses : 1
         * glasses_probability : 0.99944049119949
         * race : yellow
         * race_probability : 0.9999885559082
         * qualities : {"occlusion":{"left_eye":0,"right_eye":0,"nose":0,"mouth":0,"left_cheek":0,"right_cheek":0,"chin":0},"blur":0,"illumination":0,"completeness":0,"type":{"human":0.95982873439789,"cartoon":0.040171254426241}}
         */

        private LocationBean location;
        private double face_probability;
        private int rotation_angle;
        private double yaw;
        private double pitch;
        private double roll;
        private double age;
        private double beauty;
        private int expression;
        private double expression_probablity;
        private String gender;
        private double gender_probability;
        private int glasses;
        private double glasses_probability;
        private String race;
        private double race_probability;
        private QualitiesBean qualities;
        private List<LandmarkBean> landmark;
        private List<Landmark72Bean> landmark72;
        private List<FaceshapeBean> faceshape;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public double getFace_probability() {
            return face_probability;
        }

        public void setFace_probability(double face_probability) {
            this.face_probability = face_probability;
        }

        public int getRotation_angle() {
            return rotation_angle;
        }

        public void setRotation_angle(int rotation_angle) {
            this.rotation_angle = rotation_angle;
        }

        public double getYaw() {
            return yaw;
        }

        public void setYaw(double yaw) {
            this.yaw = yaw;
        }

        public double getPitch() {
            return pitch;
        }

        public void setPitch(double pitch) {
            this.pitch = pitch;
        }

        public double getRoll() {
            return roll;
        }

        public void setRoll(double roll) {
            this.roll = roll;
        }

        public double getAge() {
            return age;
        }

        public void setAge(double age) {
            this.age = age;
        }

        public double getBeauty() {
            return beauty;
        }

        public void setBeauty(double beauty) {
            this.beauty = beauty;
        }

        public int getExpression() {
            return expression;
        }

        public void setExpression(int expression) {
            this.expression = expression;
        }

        public double getExpression_probablity() {
            return expression_probablity;
        }

        public void setExpression_probablity(double expression_probablity) {
            this.expression_probablity = expression_probablity;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public double getGender_probability() {
            return gender_probability;
        }

        public void setGender_probability(double gender_probability) {
            this.gender_probability = gender_probability;
        }

        public int getGlasses() {
            return glasses;
        }

        public void setGlasses(int glasses) {
            this.glasses = glasses;
        }

        public double getGlasses_probability() {
            return glasses_probability;
        }

        public void setGlasses_probability(double glasses_probability) {
            this.glasses_probability = glasses_probability;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public double getRace_probability() {
            return race_probability;
        }

        public void setRace_probability(double race_probability) {
            this.race_probability = race_probability;
        }

        public QualitiesBean getQualities() {
            return qualities;
        }

        public void setQualities(QualitiesBean qualities) {
            this.qualities = qualities;
        }

        public List<LandmarkBean> getLandmark() {
            return landmark;
        }

        public void setLandmark(List<LandmarkBean> landmark) {
            this.landmark = landmark;
        }

        public List<Landmark72Bean> getLandmark72() {
            return landmark72;
        }

        public void setLandmark72(List<Landmark72Bean> landmark72) {
            this.landmark72 = landmark72;
        }

        public List<FaceshapeBean> getFaceshape() {
            return faceshape;
        }

        public void setFaceshape(List<FaceshapeBean> faceshape) {
            this.faceshape = faceshape;
        }

        public static class LocationBean {
            /**
             * left : 139
             * top : 143
             * width : 96
             * height : 95
             */

            private int left;
            private int top;
            private int width;
            private int height;

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
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

        public static class QualitiesBean {
            /**
             * occlusion : {"left_eye":0,"right_eye":0,"nose":0,"mouth":0,"left_cheek":0,"right_cheek":0,"chin":0}
             * blur : 0
             * illumination : 0
             * completeness : 0
             * type : {"human":0.95982873439789,"cartoon":0.040171254426241}
             */

            private OcclusionBean occlusion;
            private int blur;
            private int illumination;
            private int completeness;
            private TypeBean type;

            public OcclusionBean getOcclusion() {
                return occlusion;
            }

            public void setOcclusion(OcclusionBean occlusion) {
                this.occlusion = occlusion;
            }

            public int getBlur() {
                return blur;
            }

            public void setBlur(int blur) {
                this.blur = blur;
            }

            public int getIllumination() {
                return illumination;
            }

            public void setIllumination(int illumination) {
                this.illumination = illumination;
            }

            public int getCompleteness() {
                return completeness;
            }

            public void setCompleteness(int completeness) {
                this.completeness = completeness;
            }

            public TypeBean getType() {
                return type;
            }

            public void setType(TypeBean type) {
                this.type = type;
            }

            public static class OcclusionBean {
                /**
                 * left_eye : 0
                 * right_eye : 0
                 * nose : 0
                 * mouth : 0
                 * left_cheek : 0
                 * right_cheek : 0
                 * chin : 0
                 */

                private int left_eye;
                private int right_eye;
                private int nose;
                private int mouth;
                private int left_cheek;
                private int right_cheek;
                private int chin;

                public int getLeft_eye() {
                    return left_eye;
                }

                public void setLeft_eye(int left_eye) {
                    this.left_eye = left_eye;
                }

                public int getRight_eye() {
                    return right_eye;
                }

                public void setRight_eye(int right_eye) {
                    this.right_eye = right_eye;
                }

                public int getNose() {
                    return nose;
                }

                public void setNose(int nose) {
                    this.nose = nose;
                }

                public int getMouth() {
                    return mouth;
                }

                public void setMouth(int mouth) {
                    this.mouth = mouth;
                }

                public int getLeft_cheek() {
                    return left_cheek;
                }

                public void setLeft_cheek(int left_cheek) {
                    this.left_cheek = left_cheek;
                }

                public int getRight_cheek() {
                    return right_cheek;
                }

                public void setRight_cheek(int right_cheek) {
                    this.right_cheek = right_cheek;
                }

                public int getChin() {
                    return chin;
                }

                public void setChin(int chin) {
                    this.chin = chin;
                }
            }

            public static class TypeBean {
                /**
                 * human : 0.95982873439789
                 * cartoon : 0.040171254426241
                 */

                private double human;
                private double cartoon;

                public double getHuman() {
                    return human;
                }

                public void setHuman(double human) {
                    this.human = human;
                }

                public double getCartoon() {
                    return cartoon;
                }

                public void setCartoon(double cartoon) {
                    this.cartoon = cartoon;
                }
            }
        }

        public static class LandmarkBean {
            /**
             * x : 160
             * y : 160
             */

            private int x;
            private int y;

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
        }

        public static class Landmark72Bean {
            /**
             * x : 140
             * y : 165
             */

            private int x;
            private int y;

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
        }

        public static class FaceshapeBean {
            /**
             * type : square
             * probability : 0.049275912344456
             */

            private String type;
            private double probability;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public double getProbability() {
                return probability;
            }

            public void setProbability(double probability) {
                this.probability = probability;
            }
        }
    }
}
