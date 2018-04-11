package com.xai.baiduai.manager.service;


import com.xai.baiduai.manager.domain.ImageClassifyBdDO;

import java.util.List;

/**
 * ${evaluate}
 *
 * @author 小帅丶
 * @create 2018-02-07 15:18
 **/
public interface ImageClassifyBDService {
    List<ImageClassifyBdDO> list();
    int save(ImageClassifyBdDO imageClassifyBdDO);
    int remove(Long icrId);
    int batchremove(Long[] icrIds);
}
