package cn.ictgu.tools.tag;

import java.util.TreeSet;

/**
 * 标签节点
 */
public class TagNode {

    /**
     * 头两个字符的mix，mix相同，两个字符相同
     */
    public final int headTwoCharMix;

    /**
     * 所有以这两个字符开头的词表
     */
    public final TreeSet<StringPointer> words = new TreeSet<>();

    /**
     * 下一个节点
     */
    public TagNode next;

    public TagNode(int headTwoCharMix){
        this.headTwoCharMix = headTwoCharMix;
    }

    public TagNode(int headTwoCharMix, TagNode parent){
        this.headTwoCharMix = headTwoCharMix;
        parent.next = this;
    }



}
