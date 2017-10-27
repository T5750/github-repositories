package cn.ictgu.service;

import cn.ictgu.service.model.Tip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TipService {

    List<Tip> list();

}
