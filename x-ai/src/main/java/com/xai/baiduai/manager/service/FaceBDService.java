package com.xai.baiduai.manager.service;

import com.xai.baiduai.manager.domain.FaceBdDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${evaluate}
 *
 * @author 小帅丶
 * @create 2018-01-09 18:01
 **/
public interface FaceBDService {
    List<FaceBdDO> list();
    int save(FaceBdDO faceBdDO);
    int remove(Long faceId);
    int batchremove(Long[] faceIds);
}
