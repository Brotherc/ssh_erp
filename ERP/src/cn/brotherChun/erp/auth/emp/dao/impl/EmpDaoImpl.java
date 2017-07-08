package cn.brotherChun.erp.auth.emp.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.brotherChun.erp.auth.emp.dao.dao.EmpDao;
import cn.brotherChun.erp.auth.emp.vo.EmpModel;

public class EmpDaoImpl extends HibernateDaoSupport implements EmpDao {

	public EmpModel getByUserNameAndPwd(String userName, String pwd) {
		String hql="from EmpModel where userName = ? and pwd = ?";
		List<EmpModel> temp=this.getHibernateTemplate().find(hql,userName,pwd);
		return temp.size()>0?temp.get(0):null;
	}

}
