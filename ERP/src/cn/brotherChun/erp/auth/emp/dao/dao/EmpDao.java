package cn.brotherChun.erp.auth.emp.dao.dao;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;

public interface EmpDao {
	public EmpModel getByUserNameAndPwd(String userName,String pwd);
}
