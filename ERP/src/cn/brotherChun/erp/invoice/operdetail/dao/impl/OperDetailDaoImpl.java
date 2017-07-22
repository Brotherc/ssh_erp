package cn.brotherChun.erp.invoice.operdetail.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import cn.brotherChun.erp.invoice.operdetail.dao.dao.OperDetailDao;
import cn.brotherChun.erp.invoice.operdetail.vo.OperDetailModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class OperDetailDaoImpl extends BaseDaoImpl<OperDetailModel> implements OperDetailDao{

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		
	}

}
