package com.giit.www.college.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.giit.www.college.dao.DeptDao;
import com.giit.www.college.service.DeptBiz;
import com.giit.www.entity.Dept;

/**
 * Created by c0de8ug on 16-2-11.
 */
@Service
public class DeptBizImpl implements DeptBiz {
	@Resource
	private DeptDao deptDao;

	public List<Dept> findAll() {
		return deptDao.findAll();
	}

	public void add(String deptName) {
		deptDao.add(deptName);
	}

	public void update(Dept dept) {
		deptDao.update(dept);
	}

	public void delete(int deptId) {
		deptDao.delete(deptId);
	}
}
