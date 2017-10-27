package cn.ictgu.parse.video;

import cn.ictgu.bean.response.Episode;
import cn.ictgu.bean.response.Video;
import cn.ictgu.constant.ExceptionEnum;
import cn.ictgu.exception.AnyException;
import cn.ictgu.parse.Parser;
import cn.ictgu.tools.JsoupUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.math.RandomUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class Tencent implements Parser<Video> {
    private final static String VIDEO_API = "http://h5vv.video.qq.com/getinfo";
    private final static String KEY_API = "http://h5vv.video.qq.com/getkey";
    private final static String COOKIE = "pgv_pvi=8764138496; RK=vQGLRHUKYP; tvfe_boss_uuid=ad92b8f6187d6ab5; eas_sid=A1z5v0A3O2g0B0J2D5Z3L6q438; LW_uid=01V5m0Q3u4y5u3p9J231a948i8; ue_uid=ab7040ed4b966b7d3a5373441f04df91; ue_ts=1503500816; ue_uk=62b40f67948a56967557d9b652e8fb9f; ue_skey=fcad5c2f589f34abb84fb72ed5cd863f; LW_TS=1503500817; LW_PsKey=cb7f1de4c8bd86cdda5bbc44d16c5b3c; LW_pid=ae8a016e9a78dfd394a80346465acb94; mobileUV=1_15e0fc5b713_e36c3; LW_sid=01D560v3X9i2E7H1q8N4u2p0p1; cm_exchange_cookie=2&1708181146366566&1504279510; ufc=r49_6_1507386051_1507303629; cm_cookie=V1,10016&G1LIOs21cjIy&AQEBx3PELWLid733p80QC_v6-BSettR7Xne5&170918&170918,110117&BKPgYpYbLlbp4hRy3EPewKD9VS4L&AQEB6mokMUjaR_EEVXYLucPaVGW5z8OjmLgX&170919&170919,10038&CAESELhDkDWvMpkRMXSuOD9S0os&AQEB6mokMUjaR_Hoza6zOOwcM4w-ucA8t5l2&170923&170923,10008&2E229695044E41EF97F01BA3CBDD89BC-&AQEB6mokMUjaR_HP93UJ5NQqbTUePt6qDPyX&170926&170926,110064&jBYid033GJM7&AQEB6mokMUjaR_H38GiOJU2rPkCB7yrmvuB_&170929&170929,110061&0abd295d2ba8dcb&fXIuM2V9wVLJEDnfKeqP8L1eWDnKveB07Sq3soaB6cEv2EP9BGQ0i5Wu-fm5Meiy&170930&170930,110066&m8aid0u8aB60&AQEB6mokMUjaR_HwJOqrDsrq59yM2RU2e2iO&170919&171006,110065&6aW0cH8h1G&AQEB6mokMUjaR_EQdYZEOEVWWA5xKE7kfMHJ&170919&171006,110080&62AB4170070A5D59967FA0&AQEB6mokMUjaR_ECp6B1YWi0UhFsrSbwFvmt&170919&171006,10045&0&AQEB6mokMUjaR_HJa26DCO57ltsTAhz926LH&170924&171008,110055&s0e88a473ae43ae79ca&AQEB6mokMUjaR_G4SD1YS9aQp77nug8t1FKD&170918&171009; _ga=GA1.2.1683665787.1507542171; gid=b81b8a3c-30b1-4c18-8a7c-8d0a242083d9; ptui_loginuin=545544032; pgv_si=s5331946496; pt2gguin=o0545544032; uin=o0545544032; skey=@JLKGEHKcu; ptisp=cnc; luin=o0545544032; lskey=0001000018d3da03b4a9ba2fcdc1e08153fe68558b0586f977cc1890fa39e4be1d926ab1d757407497e5b5f4; ptcz=dc0405a72e903fd13ee3dedc75b9dd35988e50a3d71c91b062bfd12689ee2ea2; lv_play_indexl.=34; psessionid=75830786_1507786273_545544032_69111; psessiontime=1507786286; o_cookie=545544032; pgv_info=ssid=s4670081150; pgv_pvid=1685846680; LPPBturn=352; LKBturn=448; LPVLturn=93; Lturn=131; adid=545544032; appuser=5206515E33D5C3E8; o_minduid=lxQJ_HjBSooxew4FLDsAcSbEYMqn9CiW";
    private final static String GUID = "fb74ffcc7b14377db9cb5308e598d6e5";
    private final static String SDTFROM = "v1010";
    private final static String PLATFORM = "10901";

    @Override
    public Video parse(String url) {
        Video video = new Video();
        video.setValue(url);
        String vid = getVid(url);
        JSONObject json = JSONObject.parseObject(videoInfo(vid));
        initVideo(video, json);
        return video;
    }

    @Override
    public List<Episode> parseEpisodes(String url) {
        List<Episode> episodes = new ArrayList<>();
        Document document = JsoupUtils.getDocWithPhone(url);
        Elements elements = document.select("div[data-tpl='episode'] span a");
        for (Element element : elements) {
            Episode episode = new Episode();
            String value = "http://v.qq.com" + element.attr("href");
            String index = element.text();
            episode.setValue(value);
            episode.setIndex(index);
            episodes.add(episode);
        }
        if (episodes.size() < 1) {
            elements = document.select("a.U_color_b");
            for (Element element : elements) {
                Episode episode = new Episode();
                String value = "http://m.v.qq.com" + element.attr("href");
                String index = element.text().replace("会员", "-V");
                episode.setValue(value);
                episode.setIndex(index);
                if (!index.equals("登录")) {
                    episodes.add(episode);
                }
            }
        }
        return episodes;
    }

    /**
     * 解析腾讯视频片段
     */
    public Episode parsePart(String fileName, Integer index) {
        Episode episode = new Episode();
        String[] params = fileName.split("\\.");
        String file = fileName.replace("1.mp4", index + ".mp4");
        String vid = params[0];
        String format = params[1].replace("p", "10");
        String key = videoKey(vid, file, format);
        episode.setIndex(String.valueOf(index));
        episode.setValue(playUrl("/", file, key));
        return episode;
    }

    /**
     * 获取 vid
     */
    private String getVid(String url) {
        Document document = JsoupUtils.getDocWithPhone(url);
        Matcher matcher = Pattern.compile("\"vid\":\"(.*?)\"").matcher(document.html());
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new AnyException(ExceptionEnum.VID_CANNOT_MATCH);
    }

    /**
     * 调用腾讯接口，获取视频信息
     */
    private String videoInfo(String vid) {
        try {
            Document document = Jsoup.connect(VIDEO_API).header("Cookie", COOKIE)
                    .data("vids", vid).data("platform", PLATFORM)
                    .data("sdtfrom", SDTFROM)
                    .data("format", "10209")
                    .data("otype", "json").data("defn", "fhd")
                    .data("defaultfmt", "fhd").data("guid", GUID).ignoreContentType(true).get();
            String result = document.text().replace("QZOutputJson=", "");
            return result.substring(0, result.length() - 1);
        } catch (IOException e) {
            log.info("request tencent api error, vid : " + vid);
            throw new AnyException("request tencent api error, vid : " + vid);
        }
    }

    /**
     * 初始化视频信息
     */
    private void initVideo(Video video, JSONObject json) {
        JSONObject videoJson = json.getJSONObject("vl").getJSONArray("vi").getJSONObject(0);
        int random = RandomUtils.nextInt(3);
        String url = videoJson.getJSONObject("ul").getJSONArray("ui").getJSONObject(random).getString("url");
        String vkey = videoJson.getString("fvkey");
        String fn = videoJson.getString("fn");
        String file = fn.replace("mp4", "1.mp4");
        String title = videoJson.getString("ti");
        String firstPlayUrl = playUrl(url, file, vkey);
        String size = videoJson.getJSONObject("cl").getString("fc");
        video.setPlayUrl(firstPlayUrl);
        video.setImage("");
        video.setTitle(title);
        video.setType("qq");
        video.setOther(size);
    }

    /**
     * 片段播放地址
     */
    private String playUrl(String url, String part, String vkey) {
        return url + part + "?sdtfrom=" + "v1010" + "&guid=" + GUID + "&vkey=" + vkey;
    }

    /**
     * 获取片段播放的 key
     */
    private String videoKey(String vid, String filename, String format) {
        try {
            Document document = Jsoup.connect(KEY_API).header("Cookie", COOKIE)
                    .data("vid", vid).data("platform", PLATFORM)
                    .data("otype", "json")
                    .data("filename", filename).data("sdtfrom", SDTFROM)
                    .data("format", format).data("guid", GUID).ignoreContentType(true).get();
            String result = document.text().replace("QZOutputJson=", "");
            System.out.println(result);
            result = result.substring(0, result.length() - 1);
            return JSONObject.parseObject(result).getString("key");
        } catch (IOException e) {
            log.info("request tencent video part api error, vid : " + vid);
            throw new AnyException("request tencent api error, vid : " + vid);
        }
    }
}
