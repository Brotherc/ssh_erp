package cn.brotherChun.erp.auth.emp.business.ebo;

import cn.brotherChun.erp.auth.emp.business.ebi.EmpEbi;
import cn.brotherChun.erp.auth.emp.dao.dao.EmpDao;
import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.util.format.MD5Utils;

public class EmpEbo implements EmpEbi {
	private EmpDao empDao;

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}

	public EmpModel login(String userName, String pwd) {
		pwd=MD5Utils.md5(pwd);
		return empDao.getByUserNameAndPwd(userName,pwd);
	}
}
