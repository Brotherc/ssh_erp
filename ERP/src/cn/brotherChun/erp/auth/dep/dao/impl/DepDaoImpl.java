package cn.brotherChun.erp.auth.dep.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.brotherChun.erp.auth.dep.dao.dao.DepDao;
import cn.brotherChun.erp.auth.dep.vo.DepModel;
import cn.brotherChun.erp.auth.dep.vo.DepQueryModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class DepDaoImpl extends BaseDaoImpl<DepModel> implements DepDao{

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		
		DepQueryModel dqm=(DepQueryModel) bqm;
		
		if(dqm.getName()!=null&&dqm.getName().trim().length()>0)
			dc.add(Restrictions.like("name", "%"+dqm.getName().trim()+"%"));
		
		if(dqm.getTele()!=null&&dqm.getTele().trim().length()>0)
			dc.add(Restrictions.like("tele", "%"+dqm.getTele().trim()+"%"));
	}
	
}
