package cn.brotherChun.erp.invoice.order.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.brotherChun.erp.invoice.order.dao.dao.OrderDao;
import cn.brotherChun.erp.invoice.order.vo.OrderModel;
import cn.brotherChun.erp.invoice.order.vo.OrderQueryModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class OrderDaoImpl extends BaseDaoImpl<OrderModel> implements OrderDao{

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		OrderQueryModel oqm=(OrderQueryModel) bqm;
		if(oqm.getOrderType()!=null&&oqm.getOrderType()!=-1)
			dc.add(Restrictions.eq("orderType", oqm.getOrderType()));
		if(oqm.getCreater()!=null&&oqm.getCreater().getName()!=null&&oqm.getCreater().getName().trim().length()>0){
			dc.createAlias("creater", "c1");
			dc.add(Restrictions.like("c1.name", "%"+oqm.getCreater().getName().trim()+"%"));
		}

	}

}
