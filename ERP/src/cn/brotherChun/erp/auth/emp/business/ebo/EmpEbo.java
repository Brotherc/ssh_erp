package cn.brotherChun.erp.auth.emp.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.auth.emp.business.ebi.EmpEbi;
import cn.brotherChun.erp.auth.emp.dao.dao.EmpDao;
import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.auth.emp.vo.EmpQueryModel;
import cn.brotherChun.erp.util.exception.AppException;
import cn.brotherChun.erp.util.format.MD5Utils;

public class EmpEbo implements EmpEbi {
	private EmpDao empDao;

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}

	public EmpModel login(String userName, String pwd,String loginIp) {
		pwd=MD5Utils.md5(pwd);
		EmpModel loginEmp = empDao.getByUserNameAndPwd(userName,pwd);
		if(loginEmp!=null){
			//登录成功
			//添加登录信息
			//登录次数+1
			loginEmp.setLoginTimes(loginEmp.getLoginTimes()+1);
			loginEmp.setLastLoginTime(System.currentTimeMillis());
			loginEmp.setLastLoginIp(loginIp);
		}
		return loginEmp;
	}

	public void save(EmpModel t) {
		if(t.getUserName()==null||t.getUserName().trim().length()==0){
			throw new AppException("INFO_EMP_NAME_IS_EMPTY");
		}
		//对密码加密
		t.setPwd(MD5Utils.md5(t.getPwd()));
		//设置默认值
		t.setLastLoginTime(System.currentTimeMillis());
		t.setLastLoginIp("-");
		t.setLoginTimes(0);
		empDao.add(t);
	}

	public void delete(EmpModel t) {
		empDao.delete(t);
	}

	public void update(EmpModel t) {
//		int n=1/0;
		EmpModel temp = empDao.get(t.getUuid());
		temp.setAddress(t.getAddress());
		temp.setDep(t.getDep());
		temp.setEmail(t.getEmail());
		temp.setTele(t.getTele());
	}

	public EmpModel get(Serializable uuid) {
		return empDao.get(uuid);
	}

	public List<EmpModel> getAll() {
		return empDao.getAll();
	}

	public List<EmpModel> getAll(EmpQueryModel q, Integer pageNum,
			Integer pageCount) {
		return empDao.getAll(q, pageNum, pageCount);
	}

	public Integer getCount(EmpQueryModel q) {
		return empDao.getCount(q);
	}

	public Boolean changePwd(String userName, String pwd, String newPwd) {
		//对密码加密
		newPwd=MD5Utils.md5(newPwd);
		return empDao.updatePwdByUserNameAndPwd(userName,pwd,newPwd);
	}
}
