package com.xai.baiduai.manager.service.impl;

import com.xai.baiduai.manager.dao.ImageClassifyBDDao;
import com.xai.baiduai.manager.domain.ImageClassifyBdDO;
import com.xai.baiduai.manager.service.ImageClassifyBDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 图像识别实现类
 *
 * @author 小帅丶
 * @create 2018-02-07 15:50
 **/
@Service
public class ImageClassifyBDServiceImpl implements ImageClassifyBDService{
    @Autowired
    ImageClassifyBDDao classifyBDMapper;
    @Override
    public List<ImageClassifyBdDO> list() {
        List<ImageClassifyBdDO> imageClassifyBdDO = classifyBDMapper.list(new HashMap<>(16));
        return imageClassifyBdDO;
    }

    @Override
    public int save(ImageClassifyBdDO imageClassifyBdDO) {
        return classifyBDMapper.save(imageClassifyBdDO);
    }

    @Override
    public int remove(Long icrId) {
        return classifyBDMapper.remove(icrId);
    }

    @Override
    public int batchremove(Long[] icrIds) {
        int count = classifyBDMapper.batchremove(icrIds);
        return count;
    }
}
