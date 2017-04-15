package com.giit.www.system.dao;

import java.util.List;

import com.giit.www.entity.Resource;

/**
 * <p>
 * Resource: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public interface ResourceDao {
	public Resource createResource(Resource resource);

	public Resource updateResource(Resource resource);

	public void deleteResource(Long resourceId);

	Resource findOne(Long resourceId);

	List<Resource> findAll();
}
