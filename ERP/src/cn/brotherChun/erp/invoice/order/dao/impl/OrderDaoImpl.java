package cn.brotherChun.erp.invoice.order.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import cn.brotherChun.erp.invoice.order.dao.dao.OrderDao;
import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class OrderDaoImpl extends BaseDaoImpl<OrderModel> implements OrderDao{

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		
	}

}
