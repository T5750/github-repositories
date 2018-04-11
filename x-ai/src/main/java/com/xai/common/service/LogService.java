package com.xai.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xai.common.domain.LogDO;
import com.xai.common.domain.PageDO;
import com.xai.common.utils.Query;
@Service
public interface LogService {
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
