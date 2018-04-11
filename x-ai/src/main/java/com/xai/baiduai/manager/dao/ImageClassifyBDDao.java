package com.xai.baiduai.manager.dao;

import com.xai.baiduai.manager.domain.ImageClassifyBdDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ${evaluate}
 *
 * @author 小帅丶
 * @create 2018-02-07 15:44
 **/
@Mapper
public interface ImageClassifyBDDao {
    List<ImageClassifyBdDO> list(Map<String, Object> map);
    int save(ImageClassifyBdDO imageClassifyBdDO);
    int remove(Long icrId);
    int batchremove(Long[] icrIds);
}
