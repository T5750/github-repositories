package com.giit.www.college.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.giit.www.entity.Clazz;

/**
 * Created by c0de8ug on 16-2-11.
 */
public interface ClazzDao {
	public void add(Clazz clazz);

	public void delete(int clazzId);

	public List<Class> findAll();

	public int getClassCount(@Param("specName") String specName,
			@Param("year") String year);
}
