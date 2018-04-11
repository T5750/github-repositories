package com.xai.baiduai.manager.dao;

import com.xai.baiduai.manager.domain.FaceBdDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 百度人脸检测
 *
 * @author 小帅丶
 * @create 2018-01-09 18:09
 **/
@Mapper
public interface FaceBDDao {
    List<FaceBdDO> list(Map<String, Object> map);
    int save(FaceBdDO faceBdDO);
    int remove(Long faceId);
    int batchRemove(Long[] faceIds);
}
