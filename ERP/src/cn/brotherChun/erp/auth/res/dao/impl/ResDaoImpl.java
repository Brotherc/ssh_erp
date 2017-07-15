package cn.brotherChun.erp.auth.res.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.brotherChun.erp.auth.res.dao.dao.ResDao;
import cn.brotherChun.erp.auth.res.vo.ResModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class ResDaoImpl extends BaseDaoImpl<ResModel> implements ResDao{

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		ResModel res=(ResModel) bqm;
		if(res.getName()!=null&&res.getName().trim().length()>0)
			dc.add(Restrictions.like("name", "%"+res.getName().trim()+"%"));
		if(res.getUrl()!=null&&res.getUrl().trim().length()>0)
			dc.add(Restrictions.like("url", "%"+res.getUrl().trim()+"%"));
	}

	public List<ResModel> getAllByEmpUuid(Long uuid) {
		String hql="select reses from EmpModel emp join emp.roles role join role.reses reses where emp.uuid = ? ";
		return this.getHibernateTemplate().find(hql, uuid);
	}

}
