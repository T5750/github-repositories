package cn.ictgu.service;

import cn.ictgu.service.model.FriendLink;

import java.util.List;

/**
 * 友情链接 Service
 */
public interface FriendLinkService {

    // 得到首页友情链接
    List<FriendLink> listHome();

}
