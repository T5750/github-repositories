package cn.ictgu.service;

import cn.ictgu.service.model.HubItem;

import java.util.List;

/**
 * 分类内容操作
 */
public interface HubItemService {

    /**
     * 查询仓库内的内容
     */
    List<HubItem> list(Long hubId, Long userId);

    /**
     * 插入一条记录
     */
    void insert(HubItem item);

    /**
     * 删除一条记录
     */
    void delete(Long id, Long userId);

}
