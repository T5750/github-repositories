package com.xai.baiduai.manager.service;

import com.xai.baiduai.manager.domain.OCRBdDO;

import java.util.List;

/**
 * ${evaluate}
 *
 * @author 小帅丶
 * @create 2018-04-04 11:03
 **/
public interface OCRBDService {
    List<OCRBdDO> list();
    int save(OCRBdDO ocrBdDO);
    int remove(Long icrId);
    int batchremove(Long[] icrIds);
}
