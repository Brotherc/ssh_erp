package cn.brotherChun.erp.invoice.order.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
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
		if(oqm.getType()!=null&&oqm.getType()!=-1)
			dc.add(Restrictions.eq("type", oqm.getType()));	
		if(oqm.getOrderType()!=null&&oqm.getOrderType()!=-1)
			dc.add(Restrictions.eq("orderType", oqm.getOrderType()));
		if(oqm.getCreater()!=null&&oqm.getCreater().getName()!=null&&oqm.getCreater().getName().trim().length()>0){
			dc.createAlias("creater", "c1");
			dc.add(Restrictions.like("c1.name", "%"+oqm.getCreater().getName().trim()+"%"));
		}
		if(oqm.getChecker()!=null&&oqm.getChecker().getName()!=null&&oqm.getChecker().getName().trim().length()>0){
			dc.createAlias("checker", "c2");
			dc.add(Restrictions.like("c2.name", "%"+oqm.getChecker().getName().trim()+"%"));
		}
		dc.createAlias("supplier", "s");
		dc.createAlias("completer", "c");
		if(oqm.getCompleter()!=null&&oqm.getCompleter().getName()!=null&&oqm.getCompleter().getName().trim().length()>0){
			dc.add(Restrictions.like("c.name", "%"+oqm.getCompleter().getName().trim()+"%"));
		}
		if(oqm.getSupplier()!=null&&oqm.getSupplier().getUuid()!=null&&oqm.getSupplier().getUuid()!=-1){
			dc.add(Restrictions.eq("s.uuid", oqm.getSupplier().getUuid()));
		}
		if(oqm.getSupplier()!=null&&oqm.getSupplier().getNeeds()!=null&&oqm.getSupplier().getNeeds()!=-1){
			dc.add(Restrictions.eq("s.needs", oqm.getSupplier().getNeeds()));
		}
		if(oqm.getCompleter()!=null&&oqm.getCompleter().getUuid()!=null&&oqm.getCompleter().getUuid()!=-1){
			dc.add(Restrictions.eq("c.uuid", oqm.getCompleter().getUuid()));
		}
	}

	private void doCriteria2(DetachedCriteria dc, BaseQueryModel bqm,Integer[] orderType){
		doCriteria(dc, bqm);
		dc.add(Restrictions.in("orderType", orderType));
	}
	
	public Integer getCountBuyCheck(OrderQueryModel oqm, Integer[] orderType) {
		DetachedCriteria dc=DetachedCriteria.forClass(OrderModel.class);
		doCriteria2(dc, oqm, orderType);
		dc.setProjection(Projections.rowCount());
		List <Long>rowCount = this.getHibernateTemplate().findByCriteria(dc);
		return rowCount.get(0).intValue();
	}

	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm,
		Integer pageNum, Integer pageCount, Integer[] orderType) {
		DetachedCriteria dc=DetachedCriteria.forClass(OrderModel.class);
		doCriteria2(dc, oqm, orderType);
		return this.getHibernateTemplate().findByCriteria(dc);
	}

	private void doCriteria3(DetachedCriteria dc, BaseQueryModel bqm,Integer[] type){
		doCriteria(dc, bqm);
		dc.add(Restrictions.in("type", type));
	}
	
	public Integer getCountTask(OrderQueryModel oqm, Integer[] taskType) {
		DetachedCriteria dc=DetachedCriteria.forClass(OrderModel.class);
		doCriteria3(dc, oqm, taskType);
		dc.setProjection(Projections.rowCount());
		List <Long>rowCount = this.getHibernateTemplate().findByCriteria(dc);
		return rowCount.get(0).intValue();
	}

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, Integer[] taskType) {
		DetachedCriteria dc=DetachedCriteria.forClass(OrderModel.class);
		doCriteria3(dc, oqm, taskType);
		return this.getHibernateTemplate().findByCriteria(dc);
	}
	
	public List<OrderModel> getAllTaskByCompleter(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[] tasksType) {
		DetachedCriteria dc=DetachedCriteria.forClass(OrderModel.class);
		doCriteria3(dc, oqm, tasksType);
		return this.getHibernateTemplate().findByCriteria(dc);
	}

	public Integer getCountTaskByCompleter(OrderQueryModel oqm,
			Integer[] tasksType) {
		DetachedCriteria dc=DetachedCriteria.forClass(OrderModel.class);
		doCriteria3(dc, oqm, tasksType);
		dc.setProjection(Projections.rowCount());
		List <Long>rowCount = this.getHibernateTemplate().findByCriteria(dc);
		return rowCount.get(0).intValue();
	}

}
