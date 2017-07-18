package cn.brotherChun.erp.invoice.supplier.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.brotherChun.erp.invoice.supplier.dao.dao.SupplierDao;
import cn.brotherChun.erp.invoice.supplier.vo.SupplierModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class SupplierDaoImpl extends BaseDaoImpl<SupplierModel> implements SupplierDao{

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		SupplierModel sm=(SupplierModel) bqm;
		if(sm.getName()!=null&&sm.getName().trim().length()>0)
			dc.add(Restrictions.like("name", "%"+sm.getName().trim()+"%"));
		if(sm.getContact()!=null&&sm.getContact().trim().length()>0)
			dc.add(Restrictions.like("contact", "%"+sm.getContact().trim()+"%"));
		if(sm.getTele()!=null&&sm.getTele().trim().length()>0)
			dc.add(Restrictions.like("tele", "%"+sm.getTele().trim()+"%"));
		if(sm.getNeeds()!=null&&sm.getNeeds()!=-1)
			dc.add(Restrictions.eq("needs", sm.getNeeds()));
	}

	public List<SupplierModel> getAllUnion() {
		String hql="select distinct sm from GoodsTypeModel gtm join gtm.supplier sm";
		return this.getHibernateTemplate().find(hql);
	}

}
