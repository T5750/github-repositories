package com.xs.heweather.bean;

import java.util.List;

/**
 *  和风天气返回对象
 * @author 小帅丶
 * @类名称  Weather
 * @remark 
 * @date  2017-8-8
 */
public class WeatherBean {
	public List<HeWeather5Bean> HeWeather5;
	public WeatherBean() {
		// TODO Auto-generated constructor stub
	}
    public List<HeWeather5Bean> getHeWeather5() {
		return HeWeather5;
	}

	public void setHeWeather5(List<HeWeather5Bean> HeWeather5) {
		this.HeWeather5 = HeWeather5;
	}

	//1
	public static class HeWeather5Bean {
        /**
         * aqi : {"city":{"aqi":"80","co":"1","no2":"28","o3":"177","pm10":"56","pm25":"28","qlty":"良","so2":"4"}}
         * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.90498734","lon":"116.40528870","update":{"loc":"2017-08-08 12:54","utc":"2017-08-08 04:54"}}
         * daily_forecast : [{"astro":{"mr":"19:40","ms":"05:33","sr":"05:21","ss":"19:20"},"cond":{"code_d":"101","code_n":"302","txt_d":"多云","txt_n":"雷阵雨"},"date":"2017-08-08","hum":"39","pcpn":"1.8","pop":"83","pres":"1000","tmp":{"max":"34","min":"23"},"uv":"8","vis":"19","wind":{"deg":"310","dir":"西北风","sc":"微风","spd":"6"}},{"astro":{"mr":"20:15","ms":"06:33","sr":"05:21","ss":"19:18"},"cond":{"code_d":"302","code_n":"104","txt_d":"雷阵雨","txt_n":"阴"},"date":"2017-08-09","hum":"48","pcpn":"0.6","pop":"52","pres":"997","tmp":{"max":"30","min":"23"},"uv":"7","vis":"18","wind":{"deg":"83","dir":"东风","sc":"微风","spd":"4"}},{"astro":{"mr":"20:49","ms":"07:35","sr":"05:22","ss":"19:17"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-08-10","hum":"48","pcpn":"2.7","pop":"94","pres":"999","tmp":{"max":"32","min":"24"},"uv":"8","vis":"19","wind":{"deg":"141","dir":"东南风","sc":"微风","spd":"4"}}]
         * hourly_forecast : [{"cond":{"code":"100","txt":"晴"},"date":"2017-08-08 13:00","hum":"55","pop":"6","pres":"998","tmp":"29","wind":{"deg":"72","dir":"东北风","sc":"微风","spd":"6"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-08-08 16:00","hum":"56","pop":"7","pres":"996","tmp":"32","wind":{"deg":"161","dir":"东南风","sc":"微风","spd":"10"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-08-08 19:00","hum":"74","pop":"7","pres":"997","tmp":"29","wind":{"deg":"126","dir":"东南风","sc":"微风","spd":"7"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2017-08-08 22:00","hum":"86","pop":"5","pres":"998","tmp":"28","wind":{"deg":"146","dir":"东南风","sc":"微风","spd":"4"}}]
         * now : {"cond":{"code":"101","txt":"多云"},"fl":"35","hum":"48","pcpn":"0","pres":"999","tmp":"33","vis":"7","wind":{"deg":"227","dir":"西南风","sc":"微风","spd":"8"}}
         * status : ok
         * suggestion : {"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},"comf":{"brf":"较不舒适","txt":"白天天气多云，同时会感到有些热，不很舒适。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"drsg":{"brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},"sport":{"brf":"较适宜","txt":"天气较好，较适宜进行各种运动，但因天气热，请适当减少运动时间，降低运动强度。"},"trav":{"brf":"较适宜","txt":"天气较好，温度较高，天气较热，但有微风相伴，还是比较适宜旅游的，不过外出时要注意防暑防晒哦！"},"uv":{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}}
         */

        private AqiBean aqi;
        private BasicBean basic;
        private NowBean now;
        private String status;
        private SuggestionBean suggestion;
        private List<DailyForecastBean> daily_forecast;
        private List<HourlyForecastBean> hourly_forecast;
        public HeWeather5Bean() {
			// TODO Auto-generated constructor stub
		}
        public AqiBean getAqi() {
            return aqi;
        }

        public void setAqi(AqiBean aqi) {
            this.aqi = aqi;
        }

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public SuggestionBean getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionBean suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyForecastBean> getHourly_forecast() {
            return hourly_forecast;
        }

        public void setHourly_forecast(List<HourlyForecastBean> hourly_forecast) {
            this.hourly_forecast = hourly_forecast;
        }
        //2
        public static class AqiBean {
            /**
             * city : {"aqi":"80","co":"1","no2":"28","o3":"177","pm10":"56","pm25":"28","qlty":"良","so2":"4"}
             */
        	public AqiBean() {
				// TODO Auto-generated constructor stub
			}
            private CityBean city;

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }
            //3
            public static class CityBean {
                /**
                 * aqi : 80
                 * co : 1
                 * no2 : 28
                 * o3 : 177
                 * pm10 : 56
                 * pm25 : 28
                 * qlty : 良
                 * so2 : 4
                 */

                private String aqi;
                private String co;
                private String no2;
                private String o3;
                private String pm10;
                private String pm25;
                private String qlty;
                private String so2;
                public CityBean() {
					// TODO Auto-generated constructor stub
				}
                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }
            }
        }
        //4
        public static class BasicBean {
            /**
             * city : 北京
             * cnty : 中国
             * id : CN101010100
             * lat : 39.90498734
             * lon : 116.40528870
             * update : {"loc":"2017-08-08 12:54","utc":"2017-08-08 04:54"}
             */

            private String city;
            private String cnty;
            private String id;
            private String lat;
            private String lon;
            private UpdateBean update;
            public BasicBean() {
				// TODO Auto-generated constructor stub
			}
            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }
            //5
            public static class UpdateBean {
                /**
                 * loc : 2017-08-08 12:54
                 * utc : 2017-08-08 04:54
                 */

                private String loc;
                private String utc;
                public UpdateBean() {
					// TODO Auto-generated constructor stub
				}
                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }
        //6
        public static class NowBean {
            /**
             * cond : {"code":"101","txt":"多云"}
             * fl : 35
             * hum : 48
             * pcpn : 0
             * pres : 999
             * tmp : 33
             * vis : 7
             * wind : {"deg":"227","dir":"西南风","sc":"微风","spd":"8"}
             */

            private CondBean cond;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            private WindBean wind;
            public NowBean() {
				// TODO Auto-generated constructor stub
			}
            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }
            //7
            public static class CondBean {
                /**
                 * code : 101
                 * txt : 多云
                 */

                private String code;
                private String txt;
                public CondBean() {
					// TODO Auto-generated constructor stub
				}
                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
            //8
            public static class WindBean {
                /**
                 * deg : 227
                 * dir : 西南风
                 * sc : 微风
                 * spd : 8
                 */

                private String deg;
                private String dir;
                private String sc;
                private String spd;
                public WindBean() {
					// TODO Auto-generated constructor stub
				}
                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
        //9
        public static class SuggestionBean {
            /**
             * air : {"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"}
             * comf : {"brf":"较不舒适","txt":"白天天气多云，同时会感到有些热，不很舒适。"}
             * cw : {"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
             * drsg : {"brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"}
             * flu : {"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"}
             * sport : {"brf":"较适宜","txt":"天气较好，较适宜进行各种运动，但因天气热，请适当减少运动时间，降低运动强度。"}
             * trav : {"brf":"较适宜","txt":"天气较好，温度较高，天气较热，但有微风相伴，还是比较适宜旅游的，不过外出时要注意防暑防晒哦！"}
             * uv : {"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}
             */

            private AirBean air;
            private ComfBean comf;
            private CwBean cw;
            private DrsgBean drsg;
            private FluBean flu;
            private SportBean sport;
            private TravBean trav;
            private UvBean uv;
            public SuggestionBean() {
				// TODO Auto-generated constructor stub
			}
            public AirBean getAir() {
                return air;
            }

            public void setAir(AirBean air) {
                this.air = air;
            }

            public ComfBean getComf() {
                return comf;
            }

            public void setComf(ComfBean comf) {
                this.comf = comf;
            }

            public CwBean getCw() {
                return cw;
            }

            public void setCw(CwBean cw) {
                this.cw = cw;
            }

            public DrsgBean getDrsg() {
                return drsg;
            }

            public void setDrsg(DrsgBean drsg) {
                this.drsg = drsg;
            }

            public FluBean getFlu() {
                return flu;
            }

            public void setFlu(FluBean flu) {
                this.flu = flu;
            }

            public SportBean getSport() {
                return sport;
            }

            public void setSport(SportBean sport) {
                this.sport = sport;
            }

            public TravBean getTrav() {
                return trav;
            }

            public void setTrav(TravBean trav) {
                this.trav = trav;
            }

            public UvBean getUv() {
                return uv;
            }

            public void setUv(UvBean uv) {
                this.uv = uv;
            }
            //10
            public static class AirBean {
                /**
                 * brf : 中
                 * txt : 气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。
                 */

                private String brf;
                private String txt;
                public AirBean() {
					// TODO Auto-generated constructor stub
				}
                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
            //11
            public static class ComfBean {
                /**
                 * brf : 较不舒适
                 * txt : 白天天气多云，同时会感到有些热，不很舒适。
                 */
            	public ComfBean() {
					// TODO Auto-generated constructor stub
				}
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
            //12
            public static class CwBean {
                /**
                 * brf : 不宜
                 * txt : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
                 */

                private String brf;
                private String txt;
                public CwBean() {
					// TODO Auto-generated constructor stub
				}
                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
            //13
            public static class DrsgBean {
                /**
                 * brf : 炎热
                 * txt : 天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。
                 */
            	
                private String brf;
                private String txt;
                public DrsgBean() {
					// TODO Auto-generated constructor stub
				}
                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
            //14
            public static class FluBean {
                /**
                 * brf : 少发
                 * txt : 各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。
                 */

                private String brf;
                private String txt;
                public FluBean() {
					// TODO Auto-generated constructor stub
				}
                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
            //15
            public static class SportBean {
                /**
                 * brf : 较适宜
                 * txt : 天气较好，较适宜进行各种运动，但因天气热，请适当减少运动时间，降低运动强度。
                 */

                private String brf;
                private String txt;
                public SportBean() {
					// TODO Auto-generated constructor stub
				}
                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
            //16
            public static class TravBean {
                /**
                 * brf : 较适宜
                 * txt : 天气较好，温度较高，天气较热，但有微风相伴，还是比较适宜旅游的，不过外出时要注意防暑防晒哦！
                 */

                private String brf;
                private String txt;
                public TravBean() {
					// TODO Auto-generated constructor stub
				}
                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
            //17
            public static class UvBean {
                /**
                 * brf : 中等
                 * txt : 属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。
                 */

                private String brf;
                private String txt;
                public UvBean() {
					// TODO Auto-generated constructor stub
				}
                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }
        //18
        public static class DailyForecastBean {
            /**
             * astro : {"mr":"19:40","ms":"05:33","sr":"05:21","ss":"19:20"}
             * cond : {"code_d":"101","code_n":"302","txt_d":"多云","txt_n":"雷阵雨"}
             * date : 2017-08-08
             * hum : 39
             * pcpn : 1.8
             * pop : 83
             * pres : 1000
             * tmp : {"max":"34","min":"23"}
             * uv : 8
             * vis : 19
             * wind : {"deg":"310","dir":"西北风","sc":"微风","spd":"6"}
             */

            private AstroBean astro;
            private CondBeanX cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            private TmpBean tmp;
            private String uv;
            private String vis;
            private WindBeanX wind;
            public DailyForecastBean() {
				// TODO Auto-generated constructor stub
			}
            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public CondBeanX getCond() {
                return cond;
            }

            public void setCond(CondBeanX cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBeanX getWind() {
                return wind;
            }

            public void setWind(WindBeanX wind) {
                this.wind = wind;
            }
            //19
            public static class AstroBean {
                /**
                 * mr : 19:40
                 * ms : 05:33
                 * sr : 05:21
                 * ss : 19:20
                 */

                private String mr;
                private String ms;
                private String sr;
                private String ss;
                public AstroBean() {
					// TODO Auto-generated constructor stub
				}
                public String getMr() {
                    return mr;
                }

                public void setMr(String mr) {
                    this.mr = mr;
                }

                public String getMs() {
                    return ms;
                }

                public void setMs(String ms) {
                    this.ms = ms;
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }
            //20
            public static class CondBeanX {
                /**
                 * code_d : 101
                 * code_n : 302
                 * txt_d : 多云
                 * txt_n : 雷阵雨
                 */

                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;
                public CondBeanX() {
					// TODO Auto-generated constructor stub
				}
                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }
            //21
            public static class TmpBean {
                /**
                 * max : 34
                 * min : 23
                 */

                private String max;
                private String min;
                public TmpBean() {
					// TODO Auto-generated constructor stub
				}
                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }
            //22
            public static class WindBeanX {
                /**
                 * deg : 310
                 * dir : 西北风
                 * sc : 微风
                 * spd : 6
                 */
            	public WindBeanX() {
					// TODO Auto-generated constructor stub
				}
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
        //23
        public static class HourlyForecastBean {
            /**
             * cond : {"code":"100","txt":"晴"}
             * date : 2017-08-08 13:00
             * hum : 55
             * pop : 6
             * pres : 998
             * tmp : 29
             * wind : {"deg":"72","dir":"东北风","sc":"微风","spd":"6"}
             */

            private CondBeanXX cond;
            private String date;
            private String hum;
            private String pop;
            private String pres;
            private String tmp;
            private WindBeanXX wind;
            public HourlyForecastBean() {
				// TODO Auto-generated constructor stub
			}
            public CondBeanXX getCond() {
                return cond;
            }

            public void setCond(CondBeanXX cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public WindBeanXX getWind() {
                return wind;
            }

            public void setWind(WindBeanXX wind) {
                this.wind = wind;
            }
            //24
            public static class CondBeanXX {
                /**
                 * code : 100
                 * txt : 晴
                 */

                private String code;
                private String txt;
                public CondBeanXX() {
					// TODO Auto-generated constructor stub
				}
                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
            //25
            public static class WindBeanXX {
                /**
                 * deg : 72
                 * dir : 东北风
                 * sc : 微风
                 * spd : 6
                 */

                private String deg;
                private String dir;
                private String sc;
                private String spd;
                public WindBeanXX() {
					// TODO Auto-generated constructor stub
				}
                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
