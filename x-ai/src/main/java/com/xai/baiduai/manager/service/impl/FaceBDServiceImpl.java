package com.xai.baiduai.manager.service.impl;

import com.xai.baiduai.manager.dao.FaceBDDao;
import com.xai.baiduai.manager.domain.FaceBdDO;
import com.xai.baiduai.manager.service.FaceBDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * ${evaluate}
 *
 * @author 小帅丶
 * @create 2018-01-09 18:08
 **/
@Service
public class FaceBDServiceImpl implements FaceBDService{
    @Autowired
    FaceBDDao faceBDMapper;
    @Override
    public List<FaceBdDO> list() {
        List<FaceBdDO> faceBdDOList = faceBDMapper.list(new HashMap<>(16));
        return faceBdDOList;
    }
    @Override
    public int save(FaceBdDO faceBdDO) {
        return faceBDMapper.save(faceBdDO);
    }

    @Override
    public int remove(Long faceId) {
        return faceBDMapper.remove(faceId);
    }
    @Transactional
    @Override
    public int batchremove(Long[] userIds) {
        int count = faceBDMapper.batchRemove(userIds);
        return count;
    }
}
