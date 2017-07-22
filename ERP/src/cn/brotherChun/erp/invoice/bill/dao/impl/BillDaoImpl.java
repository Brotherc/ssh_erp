package cn.brotherChun.erp.invoice.bill.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.brotherChun.erp.invoice.bill.dao.dao.BillDao;
import cn.brotherChun.erp.invoice.bill.vo.BillQueryModel;
import cn.brotherChun.erp.invoice.orderdetail.vo.OrderDetailModel;

public class BillDaoImpl extends HibernateDaoSupport implements BillDao{

	public List<Object[]> getAll(BillQueryModel bqm) {
		DetachedCriteria dc=DetachedCriteria.forClass(OrderDetailModel.class);
		dc.createAlias("order", "o");
		dc.createAlias("goods", "g");
		dc.createAlias("g.gtm", "gt");
		dc.createAlias("gt.supplier", "s");
		if(bqm.getType()!=null&&bqm.getType()!=-1)
			dc.add(Restrictions.eq("o.type", bqm.getType()));
		if(bqm.getSupplierUuid()!=null&&bqm.getSupplierUuid()!=-1)
			dc.add(Restrictions.eq("s.uuid", bqm.getSupplierUuid()));
		
		ProjectionList pList=Projections.projectionList();
		pList.add(Projections.groupProperty("goods"));
		pList.add(Projections.sum("num"));
		dc.setProjection(pList);
		
		return this.getHibernateTemplate().findByCriteria(dc);
	}

	public List<OrderDetailModel> getBillByOrderDetail(BillQueryModel bqm) {
		DetachedCriteria dc=DetachedCriteria.forClass(OrderDetailModel.class);
		dc.createAlias("order", "o");
		if(bqm.getType()!=null&&bqm.getType()!=-1)
			dc.add(Restrictions.eq("o.type", bqm.getType()));
		if(bqm.getGoodsUuid()!=null&&bqm.getGoodsUuid()!=-1)
			dc.add(Restrictions.eq("goods.uuid", bqm.getGoodsUuid()));
		
		return this.getHibernateTemplate().findByCriteria(dc);
	}

}
