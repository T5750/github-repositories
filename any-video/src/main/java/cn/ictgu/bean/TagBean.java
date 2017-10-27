package cn.ictgu.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagBean implements Comparable<TagBean>{

    // 标签名
    private String name;

    // 分数
    @JsonIgnore
    private Integer score;

    // 增加分数
    public void addScore(Integer num){
        this.score = this.score + num;
    }

    @Override
    public int compareTo(TagBean o) {
        return o.getScore().compareTo(this.score);
    }
}
