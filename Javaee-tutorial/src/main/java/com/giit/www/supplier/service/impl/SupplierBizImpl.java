package com.giit.www.supplier.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.giit.www.college.dao.OrderBookDao;
import com.giit.www.entity.custom.ReviewedBookVo;
import com.giit.www.supplier.service.SupplierBiz;

/**
 * Created by c0de8ug on 16-2-16.
 */
@Service
public class SupplierBizImpl implements SupplierBiz {
	@Resource
	private OrderBookDao orderBookDao;

	// TODO 这个代码偷懒了！！！！！要多烂有多烂直接拷贝的。。
	public List<ReviewedBookVo> findAllReviewedBook() {
		List<ReviewedBookVo> reviewedBookVoList = orderBookDao
				.findAllReviewedBook();
		return reviewedBookVoList;
	}
}
