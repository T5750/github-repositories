package com.giit.www.college.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.giit.www.college.dao.DeptDao;
import com.giit.www.college.dao.SpecDao;
import com.giit.www.college.service.SpecBiz;
import com.giit.www.entity.Spec;
import com.giit.www.entity.custom.DeptAndSpec;

/**
 * Created by c0de8ug on 16-2-11.
 */
@Service
public class SpecBizImpl implements SpecBiz {
	@Resource
	private SpecDao specDao;
	@Resource
	private DeptDao deptDao;

	public List<DeptAndSpec> findDeptAndSpec() {
		return specDao.findDeptAndSpec();
	}

	@Override
	public void update(String specName, String newSpecName) {
		specDao.update(specName, newSpecName);
	}

	@Override
	public void add(Spec spec) {
		specDao.add(spec);
	}

	@Override
	public void delete(String specName) {
		specDao.delete(specName);
	}

	@Override
	public List<String> findDpet() {
		return deptDao.findAllDeptName();
	}
}
