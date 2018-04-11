package com.xai.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xai.common.service.DictService;
import com.xai.common.utils.DateUtils;
import com.xai.common.utils.PageUtils;
import com.xai.oa.dao.NotifyDao;
import com.xai.oa.dao.NotifyRecordDao;
import com.xai.oa.domain.NotifyDO;
import com.xai.oa.domain.NotifyDTO;
import com.xai.oa.domain.NotifyRecordDO;
import com.xai.oa.service.NotifyService;
import com.xai.system.dao.UserDao;

@Service
public class NotifyServiceImpl implements NotifyService {
	@Autowired
	private NotifyDao notifyDao;
	@Autowired
	private NotifyRecordDao recordDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DictService dictService;

	@Override
	public NotifyDO get(Long id) {
		NotifyDO rDO =  notifyDao.get(id);
		rDO.setType(dictService.getName("oa_notify_type", rDO.getType()) );
		return rDO;
	}

	@Override
	public List<NotifyDO> list(Map<String, Object> map) {
		List<NotifyDO> notifys = notifyDao.list(map);
		for (NotifyDO notifyDO : notifys) {
			notifyDO.setType(dictService.getName("oa_notify_type", notifyDO.getType()) );
		}
		return notifys;
	}

	@Override
	public int count(Map<String, Object> map) {
		return notifyDao.count(map);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int save(NotifyDO notify) {
		notify.setUpdateDate(new Date());
		int r = notifyDao.save(notify);
		// 保存到接受者列表中
		Long[] userIds = notify.getUserIds();
		Long notifyId = notify.getId();
		List<NotifyRecordDO> records = new ArrayList<>();
		for (Long userId : userIds) {
			NotifyRecordDO record = new NotifyRecordDO();
			record.setNotifyId(notifyId);
			record.setUserId(userId);
			record.setIsRead(0);
			records.add(record);
		}
		recordDao.batchSave(records);
		return r;

	}

	@Override
	public int update(NotifyDO notify) {
		return notifyDao.update(notify);
	}

	@Transactional
	@Override
	public int remove(Long id) {
		recordDao.removeByNotifbyId(id);
		return notifyDao.remove(id);
	}

	@Transactional
	@Override
	public int batchRemove(Long[] ids) {
		recordDao.batchRemoveByNotifbyId(ids);
		return notifyDao.batchRemove(ids);
	}

//	@Override
//	public Map<String, Object> message(Long userId) {
//		Map<String, Object> param = new HashMap<>(16);
//		Map<String, Object> map = new HashMap<>(16);
//		param.put("userId", userId);
//		param.put("isRead", 0);
//		int messageNum = recordDao.count(param);
//		Long[] notifyIds = recordDao.listNotifyIds(param);
//		List<NotifyDO> messages = notifyDao.listByIds(notifyIds);
//		map.put("messageNum", messageNum);
//		map.put("messages", messages);
//		return map;
//	}

	@Override
	public PageUtils selfList(Map<String, Object> map) {
		List<NotifyDTO> rows = notifyDao.listDTO(map);
		for (NotifyDTO notifyDTO : rows) {
			notifyDTO.setBefore(DateUtils.getTimeBefore(notifyDTO.getUpdateDate()));
			notifyDTO.setSender(userDao.get(notifyDTO.getCreateBy()).getName());
		}
		PageUtils page = new PageUtils(rows, notifyDao.countDTO(map));
		return page;
	}

}
