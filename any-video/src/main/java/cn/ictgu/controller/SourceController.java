package cn.ictgu.controller;

import cn.ictgu.bean.TagBean;
import cn.ictgu.bean.response.Article;
import cn.ictgu.bean.response.Video;
import cn.ictgu.parse.ParserManager;
import cn.ictgu.tools.TagUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@AllArgsConstructor
public class SourceController {

    private static final Integer TITLE_SCORE = 5;

    private static final Integer CONTENT_SCORE = 1;

    private final ParserManager parseManager;

    private final TagUtils tagUtils;

    @GetMapping("/view")
    public String play(HttpServletRequest request, Model model) {
        String url = request.getParameter("u");
        Object source = parseManager.parse(url);
        model.addAttribute("source", source);
        if (source instanceof Video) {
            return "video";
        }
        Map<String, TagBean> titleTagMap = tagUtils.matchTags(((Article) source).getTitle(), TITLE_SCORE);
        Map<String, TagBean> contentTagMap = tagUtils.matchTags(((Article) source).getContent(), CONTENT_SCORE);
        List<TagBean> tagList = mergeTag(titleTagMap, contentTagMap);
        ((Article) source).setTags(tagList);
        return "article";
    }

    /**
     * 合并标题和内容的TAG
     */
    private List<TagBean> mergeTag(Map<String, TagBean> titleTags, Map<String, TagBean> contentTag) {
        Set<String> titleKeys = titleTags.keySet();
        for (String key : titleKeys) {
            TagBean tagBean = contentTag.get(key);
            if (tagBean == null){
                contentTag.put(key, new TagBean(key, TITLE_SCORE));
            }else {
                tagBean.addScore(TITLE_SCORE);
            }
        }
        return filter(contentTag);
    }

    /**
     * 过滤相关度不高的 TAG
     */
    private List<TagBean> filter(Map<String, TagBean> tagMap) {
        List<TagBean> tagList = new ArrayList<>();
        Set<String> tagSet = tagMap.keySet();
        if (tagSet.isEmpty()) {
            return tagList;
        }
        for (String tag : tagSet) {
            TagBean tagBean = tagMap.get(tag);
            if (tagBean.getScore() >= TITLE_SCORE) {
                tagList.add(tagBean);
            }
        }
        Collections.sort(tagList);
        return tagList;
    }

}
