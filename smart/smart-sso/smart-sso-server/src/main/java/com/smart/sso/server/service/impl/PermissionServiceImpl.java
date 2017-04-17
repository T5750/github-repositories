package com.smart.sso.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.sso.rpc.RpcPermission;
import com.smart.sso.server.common.Permissible;
import com.smart.sso.server.dao.PermissionDao;
import com.smart.sso.server.model.Permission;
import com.smart.sso.server.service.PermissionService;
import com.smart.sso.server.service.RolePermissionService;

@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission, Integer> implements PermissionService {

	@Resource
	private RolePermissionService rolePermissionService;
	@Resource
	private PermissionService permissionService;

	@Autowired
	public void setDao(PermissionDao dao) {
		this.dao = dao;
	}

	@Permissible
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(dao.enable(isEnable, idList), idList.size(), "权限数据库更新失败");
	}
	
	@Permissible
	public void save(Permission t) {
		super.save(t);
	}

	public List<Permission> findByName(String name, Integer appId, Boolean isEnable) {
		return dao.findByName(name, appId, isEnable);
	}

	@Permissible
	@Transactional
	public void deletePermission(Integer id, Integer appId) {
		List<Integer> idList = new ArrayList<Integer>();
		
		List<Permission> list = permissionService.findByName(null, appId, null);
		loopSubList(id, idList, list);
		idList.add(id);
		
		rolePermissionService.deleteByPermissionIds(idList);
		
		verifyRows(dao.deleteById(idList), idList.size(), "权限数据库删除失败");
	}

	// 递归方法，删除子权限
	protected void loopSubList(Integer id, List<Integer> idList, List<Permission> list) {
		for (Permission p : list) {
			if (id.equals(p.getParentId())) {
				idList.add(p.getId());
				loopSubList(p.getId(), idList, list);
			}
		}
	}
	
	public void deleteByAppIds(List<Integer> idList) {
		dao.deleteByAppIds(idList);
	}

	public List<RpcPermission> findListById(String appCode, Integer userId) {
		return dao.findListById(appCode, userId);
	}
}
