package com.xai.baiduai.manager.service.impl;

import com.xai.baiduai.manager.dao.OCRBDDao;
import com.xai.baiduai.manager.domain.OCRBdDO;
import com.xai.baiduai.manager.service.OCRBDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${evaluate}
 *
 * @author 小帅丶
 * @create 2018-04-04 11:04
 **/
@Service
public class OCRBDServiceImpl implements OCRBDService{
    @Autowired
    OCRBDDao ocrbdMapper;
    @Override
    public List<OCRBdDO> list() {
        return ocrbdMapper.list();
    }

    @Override
    public int save(OCRBdDO ocrBdDO) {
        return ocrbdMapper.save(ocrBdDO);
    }

    @Override
    public int remove(Long ocrId) {
        return ocrbdMapper.remove(ocrId);
    }

    @Override
    public int batchremove(Long[] ocrIds) {
        return ocrbdMapper.batchremove(ocrIds);
    }
}
