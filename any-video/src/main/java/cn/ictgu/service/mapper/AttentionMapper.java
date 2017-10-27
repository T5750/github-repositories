package cn.ictgu.service.mapper;

import cn.ictgu.service.model.Attention;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AttentionMapper {

    @Insert("insert into any_attention(user_id, other_id) values(#{userId}, #{otherId})")
    int insert(Attention attention);

    @Delete("delete from any_attention where user_id = #{userId} and other_id = #{otherId}")
    int delete(@Param("userId") Long userId, @Param("otherId") Long otherId);

    @Select("select * from any_attention where user_id = #{userId} and other_id = #{otherId}")
    Attention select(@Param("userId") Long userId, @Param("otherId") Long otherId);

}
