package cn.brotherChun.erp.invoice.store.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import cn.brotherChun.erp.invoice.store.dao.dao.StoreDao;
import cn.brotherChun.erp.invoice.store.vo.StoreModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class StoreDaoImpl extends BaseDaoImpl<StoreModel> implements StoreDao{

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		
	}

}
