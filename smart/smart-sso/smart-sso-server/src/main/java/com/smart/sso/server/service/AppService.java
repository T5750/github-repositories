package com.smart.sso.server.service;

import java.util.List;
import java.util.Set;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;
import com.smart.sso.server.model.App;

/**
 * 应用服务接口
 * 
 * @author Joe
 */
public interface AppService extends Service<App, Integer> {
	
	/**
	 * 启用禁用操作
	 * @param isEnable 是否启用
	 * @param idList 应用ID集合
	 * @return
	 */
	public void enable(Boolean isEnable, List<Integer> idList);
	
	/**
	 * 根据名称查询
	 * @param name 应用名称
	 * @return
	 */
	public List<App> findByAll(String name);
	
	/**
	 * 根据名称分页查询
	 * @param name 应用名称
	 * @return
	 */
	public Pagination<App> findPaginationByName(String name, Pagination<App> p);
	
	/**
	 * 根据应用编码查询
	 * @param code 应用编码
	 * @return
	 */
	public App findByCode(String code);
	
	/**
	 * 根据管理员ID查询已分配应用
	 * @param userId 管理员ID
	 * @return
	 */
	public List<App> findByUserId(Boolean isEnable, Integer userId);
	
	/**
	 * 根据管理员ID查询已分配应用编码
	 * @param userId
	 * @return
	 */
	public Set<String> findAppCodeByUserId(Boolean isEnable, Integer userId);
}