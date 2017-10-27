package cn.ictgu.service.mapper;

import cn.ictgu.service.model.Tip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 打赏
 */
@Mapper
public interface TipMapper {

    @Select("SELECT * FROM any_tip ORDER BY id DESC")
    List<Tip> list();

}
