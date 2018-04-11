package com.xai.baiduai.manager.dao;

import com.xai.baiduai.manager.domain.OCRBdDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ${evaluate}
 *
 * @author 小帅丶
 * @create 2018-04-04 11:05
 **/
@Mapper
public interface OCRBDDao {
    List<OCRBdDO> list();
    int save(OCRBdDO ocrBdDO);
    int remove(Long icrId);
    int batchremove(Long[] icrIds);
}
