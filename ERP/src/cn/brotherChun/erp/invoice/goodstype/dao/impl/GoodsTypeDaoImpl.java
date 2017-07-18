package cn.brotherChun.erp.invoice.goodstype.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.brotherChun.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import cn.brotherChun.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class GoodsTypeDaoImpl extends BaseDaoImpl<GoodsTypeModel> implements GoodsTypeDao{

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		GoodsTypeModel gtm=(GoodsTypeModel) bqm;
		if(gtm.getName()!=null&&gtm.getName().trim().length()>0)
			dc.add(Restrictions.like("name", "%"+gtm.getName().trim()+"%"));
		if(gtm.getSupplier()!=null&&gtm.getSupplier().getUuid()!=-1)
			dc.add(Restrictions.eq("supplier", gtm.getSupplier()));
	}

	public List<GoodsTypeModel> getAllBySupplierUuid(Long uuid) {
		String hql="from GoodsTypeModel where supplier.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

}
