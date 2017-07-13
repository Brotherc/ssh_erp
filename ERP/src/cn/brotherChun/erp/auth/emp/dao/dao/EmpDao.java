package cn.brotherChun.erp.auth.emp.dao.dao;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.util.base.BaseDao;

public interface EmpDao extends BaseDao<EmpModel>{
	public EmpModel getByUserNameAndPwd(String userName,String pwd);

	public Boolean updatePwdByUserNameAndPwd(String userName, String pwd,
			String newPwd);
}
