package cn.ictgu.parse.video;

import cn.ictgu.bean.response.Video;
import cn.ictgu.constant.ExceptionEnum;
import cn.ictgu.exception.AnyException;
import cn.ictgu.parse.Parser;
import cn.ictgu.bean.response.Episode;
import cn.ictgu.tools.JsoupUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 乐视视频解析
 * Created by Silence on 2017/1/7.
 */
public class Letv implements Parser<Video> {

    private final static String ROUTE = "http://player-pc.le.com/mms/out/video/playJson.json?platid=3&splatid=304&tss=no&id=%s&detect=1&dvtype=1300&accessyx=1&domain=www.le.com&tkey=%s&devid=b452a9ce08e7d08370dcdf12aba639c1&source=1001&lang=cn&region=cn&isHttps=0";
    private final static String VIP_LOCATION = "%s%s&token=%s&uid=67945963&format=1&jsonp=vjs_149067353337651&expect=3&p1=0&p2=06&termid=2&ostype=macos&hwtype=un&uuid=1891087902108532_1&vid=%s&";
    private static final String LETV_VIDEOS = "http://d.api.m.le.com/apipccard/dynamic?cid=2&vid=%s&platform=pc&isvip=1&type=episode,Cotherlist";
    private final static String VID_REGEX = "([0-9]+)\\.html";
    private final static String[] DIS_LIST = {"1300", "1000", "350"};

    @Override
    public Video parse(String url) {
        Video video = new Video();
        video.setValue(url);
        String vid = this.matchVid(url);
        String routeUrl = String.format(ROUTE, vid, getTkey());
        Document document = JsoupUtils.getDocWithPC(routeUrl);
        JSONObject object = JSONObject.parseObject(document.text());
        JSONObject playurl = object.getJSONObject("msgs").getJSONObject("playurl");
        String title = playurl.getString("title");
        video.setTitle(title);
        String image = playurl.getString("pic").replace("120_90", "360_180");
        image = image.replace("http:", "");
        video.setImage(image);
        String domain = playurl.getJSONArray("domain").getString(0);
        String dispatch = getDispatch(playurl.getJSONObject("dispatch"));
        JSONObject yuanxian = object.getJSONObject("msgs").getJSONObject("yuanxian");
        String locationUrl;
        if (yuanxian != null) {
            String token = yuanxian.getString("token");
            locationUrl = String.format(VIP_LOCATION, domain, dispatch, token, vid);
        } else {
            locationUrl = String.format(VIP_LOCATION, domain, dispatch, "", vid);
        }
        Document result = JsoupUtils.getDocWithPhone(locationUrl);
        String text = StringEscapeUtils.unescapeJava(result.text());
        text = text.replace("vjs_149067353337651(", "");
        text = text.replace(");", "");
        JSONObject videoJson = JSONObject.parseObject(text);
        video.setPlayUrl(videoJson.getJSONArray("nodelist").getJSONObject(0).getString("location"));
        return video;
    }

    @Override
    public List<Episode> parseEpisodes(String videoUrl) {
        List<Episode> episodes = new ArrayList<>();
        Document document = JsoupUtils.getDocWithPhone(videoUrl);
        Matcher matcher = Pattern.compile("([0-9]{5,})\\.html").matcher(document.html());
        if (matcher.find()) {
            String vid = matcher.group(1);
            String videosAPI = String.format(LETV_VIDEOS, vid);
            String data = JsoupUtils.getDocWithPhone(videosAPI).body().text();
            JSONObject jsonObject = JSONObject.parseObject(data);
            JSONArray array = jsonObject.getJSONObject("data").getJSONObject("episode").getJSONArray("videolist");
            if (array.size() > 1) {
                for (int i = array.size() - 1; i >= 0; i--) {
                    JSONObject object = array.getJSONObject(i);
                    Episode episode = new Episode();
                    Integer index = object.getInteger("episode");
                    if (index < 10) {
                        episode.setIndex("0" + index);
                    } else {
                        episode.setIndex("" + index);
                    }
                    episode.setValue(object.getString("url"));
                    episodes.add(episode);
                }
            }
        }
        return episodes;
    }

    /**
     * 从 URL 中匹配 VID
     */
    private String matchVid(String videoUrl) {
        Matcher matcher = Pattern.compile(VID_REGEX).matcher(videoUrl);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            Document realDocument = JsoupUtils.getDocWithPC(videoUrl);
            matcher = Pattern.compile("vid:\"(.*?)\"").matcher(realDocument.html());
            if (matcher.find())
                return matcher.group(1);
            throw new AnyException(ExceptionEnum.VID_CANNOT_MATCH);
        }
    }

    /**
     * 获取最清晰的视频线路
     */
    private String getDispatch(JSONObject dispatch) {
        for (String dis : DIS_LIST) {
            if (dispatch.containsKey(dis)) {
                return dispatch.getJSONArray(dis).getString(0);
            }
        }
        throw new AnyException(ExceptionEnum.NO_VIDEO);
    }

    /**
     *  乐视tkey算法
     */
    private static String getTkey() {
        int a = (int) (new Date().getTime() / 1000);
        for (int i = 0; i < 8; i++) {
            int b = a >> 1;
            int c = (0x1 & a) << 31;
            a = b + c;
        }
        int result = 0xB074319 ^ a;
        return "" + result;
    }

}
