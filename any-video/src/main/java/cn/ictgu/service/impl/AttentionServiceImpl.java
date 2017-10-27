package cn.ictgu.service.impl;

import cn.ictgu.service.mapper.AttentionMapper;
import cn.ictgu.service.model.Attention;
import cn.ictgu.service.AttentionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttentionServiceImpl implements AttentionService {

    private final AttentionMapper attentionMapper;

    @Override
    public boolean attention(Long userId, Long otherId) {
        Attention attention = attentionMapper.select(userId, otherId);

        if (attention == null){
            // 未关注，则关注
            attentionMapper.insert(new Attention(userId, otherId));
            return true;
        }

        // 已关注，则取消关注
        attentionMapper.delete(userId, otherId);
        return false;
    }

    @Override
    public boolean isAttention(Long userId, Long otherId) {
        return attentionMapper.select(userId, otherId) != null;
    }

}
