package top.ibase4j.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import top.ibase4j.model.TaskFireLog;

public interface TaskFireLogMapper extends BaseMapper<TaskFireLog> {
    List<Long> selectIdByMap(RowBounds rowBounds, @Param("cm") Map<String, Object> params);
}
