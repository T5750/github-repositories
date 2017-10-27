package cn.ictgu.service.impl;

import cn.ictgu.service.mapper.TipMapper;
import cn.ictgu.service.model.Tip;
import cn.ictgu.service.TipService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipServiceImpl implements TipService {

  private final TipMapper mapper;

  @Override
  public List<Tip> list() {
    return mapper.list();
  }

}
