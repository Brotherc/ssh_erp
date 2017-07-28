package cn.brotherChun.erp.auth.emp.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.brotherChun.erp.auth.dep.vo.DepQueryModel;
import cn.brotherChun.erp.auth.emp.dao.dao.EmpDao;
import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.auth.emp.vo.EmpQueryModel;
import cn.brotherChun.erp.util.base.BaseDaoImpl;
import cn.brotherChun.erp.util.base.BaseQueryModel;

public class EmpDaoImpl extends BaseDaoImpl<EmpModel> implements EmpDao {

	public EmpModel getByUserNameAndPwd(String userName, String pwd) {
		String hql="from EmpModel where userName = ? and pwd = ?";
		List<EmpModel> temp=this.getHibernateTemplate().find(hql,userName,pwd);
		return temp.size()>0?temp.get(0):null;
	}

	@Override
	public void doCriteria(DetachedCriteria dc, BaseQueryModel bqm) {
		EmpQueryModel eqm=(EmpQueryModel) bqm;
		if(eqm.getName()!=null&&eqm.getName().trim().length()>0)
			dc.add(Restrictions.like("name", "%"+eqm.getName().trim()+"%"));
		if(eqm.getTele()!=null&&eqm.getTele().trim().length()>0)
			dc.add(Restrictions.like("tele", "%"+eqm.getTele().trim()+"%"));
		if(eqm.getUserName()!=null&&eqm.getUserName().trim().length()>0)
			dc.add(Restrictions.eq("userName", eqm.getUserName().trim()));
		if(eqm.getGender()!=null&&eqm.getGender()!=-1)
			dc.add(Restrictions.eq("gender", eqm.getGender()));
		if(eqm.getEmail()!=null&&eqm.getEmail().trim().length()>0)
			dc.add(Restrictions.like("email", "%"+eqm.getEmail().trim()+"%"));
		if(eqm.getDep()!=null&&eqm.getDep().getUuid()!=null&&eqm.getDep().getUuid()!=-1)
			dc.add(Restrictions.eq("dep", eqm.getDep()));
		//birthday
		if(eqm.getBirthday()!=null)
			dc.add(Restrictions.ge("birthday", eqm.getBirthday()));
		//birthday2
		if(eqm.getBirthday2()!=null)
			dc.add(Restrictions.le("birthday", eqm.getBirthday2()+86400000-1));
	}

	public Boolean updatePwdByUserNameAndPwd(String userName, String pwd,
			String newPwd) {
		String hql=" update EmpModel set pwd = ? where userName = ? and pwd = ?";
		int flag= this.getHibernateTemplate().bulkUpdate(hql,newPwd,userName,pwd);
		return flag > 0;
	}

	public List<EmpModel> getAllByDepUuid(Long uuid) {
		String hql="from EmpModel where dep.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

}
