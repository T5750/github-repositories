package cn.ictgu.tools;

import cn.ictgu.bean.TagBean;
import cn.ictgu.tools.tag.StringPointer;
import cn.ictgu.tools.tag.TagNode;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;

/**
 * 标签工具
 */
@Component
@Log4j2
public class TagUtils implements InitializingBean {

    // 数组大小
    private static final int DEFAULT_SIZE = 2048;

    // 标签数组
    private TagNode[] nodes = new TagNode[DEFAULT_SIZE];

    @Override
    public void afterPropertiesSet() throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        BufferedReader br = new BufferedReader(new InputStreamReader(resolver.getResource("tags.txt").getInputStream()));
        String tag = br.readLine();
        while (tag != null) {
            if (!put(tag)) {
                log.info("添加标签失败：" + tag);
            }
            tag = br.readLine();
        }
        br.close();
    }

    /**
     * 新增标签节点
     */
    private boolean put(String word) {
        if (word == null || word.trim().length() < 2) {
            return false;
        }
        StringPointer sp = new StringPointer(word.trim());
        int hash = sp.nextTwoCharHash(0);
        int mix = sp.nextTwoCharMix(0);
        int index = hash & (nodes.length - 1);
        TagNode node = nodes[index];
        if (node == null) {
            node = new TagNode(mix);
            node.words.add(sp);
            nodes[index] = node;
        } else {
            while (node != null) {
                if (node.headTwoCharMix == mix) {
                    node.words.add(sp);
                    return true;
                }
                if (node.next == null) {
                    new TagNode(mix, node).words.add(sp);
                    return true;
                }
                node = node.next;
            }
        }
        return true;
    }


    /**
     * 匹配标签
     */
    public Map<String, TagBean> matchTags(String content, Integer score) {
        content = preHandle(content);
        Map<String, TagBean> tagMap = new HashMap<>();
        StringPointer sp = new StringPointer(content);
        int i = 0;
        while (i < sp.length - 1) {
            int step = 1;
            int hash = sp.nextTwoCharHash(i);
            TagNode node = nodes[hash & (nodes.length - 1)];
            if (node != null) {
                int mix = sp.nextTwoCharMix(i);
                outer:
                for (; node != null; node = node.next) {
                    if (node.headTwoCharMix == mix) {
                        NavigableSet<StringPointer> desSet = node.words.headSet(sp.substring(i), true);
                        for (StringPointer word : desSet.descendingSet()) {
                            if (sp.nextStartsWith(i, word)) {
                                String tagWord = word.toString();
                                TagBean tag = tagMap.get(tagWord);
                                if (tag == null) {
                                    tagMap.put(tagWord, new TagBean(tagWord, score));
                                } else {
                                    tag.addScore(score);
                                }
                                step = word.length;
                                break outer;
                            }
                        }
                    }
                }
            }
            i += step;
        }
        return tagMap;
    }

    /**
     * 文本预处理
     * 英文小写 -> 移除code -> 移除Html标签
     */
    private String preHandle(String content){
        content = content.toLowerCase();
        content = content.replaceAll(" ", "").replaceAll("<code[\\s\\S]*?</code>", "");
        return Jsoup.parse(content).text();
    }

}
