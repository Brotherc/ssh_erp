package cn.brotherChun.erp.invoice.orderdetail.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import cn.brotherChun.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class OrderDetailDaoImpl extends BaseDaoImpl<OrderDetailModel> implements OrderDetailDao{

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		
	}

}
