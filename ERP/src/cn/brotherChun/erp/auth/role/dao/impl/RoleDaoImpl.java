package cn.brotherChun.erp.auth.role.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.brotherChun.erp.auth.role.dao.dao.RoleDao;
import cn.brotherChun.erp.auth.role.vo.RoleModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class RoleDaoImpl extends BaseDaoImpl<RoleModel> implements RoleDao{

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		RoleModel role=(RoleModel) bqm;
		if(role.getName()!=null&&role.getName().trim().length()>0)
			dc.add(Restrictions.like("name", "%"+role.getName().trim()+"%"));
		if(role.getCode()!=null&&role.getCode().trim().length()>0)
			dc.add(Restrictions.like("code", "%"+role.getCode().trim()+"%"));
	}

}
