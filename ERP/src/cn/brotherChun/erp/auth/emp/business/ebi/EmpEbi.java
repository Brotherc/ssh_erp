package cn.brotherChun.erp.auth.emp.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.auth.emp.vo.EmpQueryModel;
import cn.brotherChun.erp.util.base.BaseEbi;

@Transactional
public interface EmpEbi extends BaseEbi<EmpModel, EmpQueryModel>{
	/**
	 * 根据用户名密码登录
	 * @param userName 用户名
	 * @param pwd 密码 
	 * @param loginIp 登录ip地址
	 * @return 登录用户信息，如果返回null，表示登录失败
	 */
	public EmpModel login(String userName,String pwd,String loginIp);

	/**
	 * 根据用户信息及新密码修改密码
	 * @param userName 旧用户名
	 * @param pwd 旧密码
	 * @param newPwd 新密码
	 * @return 是否修改成功，如果返回false，表示修改失败
	 */
	public Boolean changePwd(String userName, String pwd, String newPwd);
	
	public void save(EmpModel emp,Long[] roles);
	
	public void update(EmpModel emp,Long[] roles);
}
