package cn.brotherChun.erp.auth.emp.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;

@Transactional
public interface EmpEbi {
	/**
	 * 根据用户名密码登录
	 * @param userName 用户名
	 * @param pwd 密码 
	 * @return 登录用户信息，如果返回null，表示登录失败
	 */
	public EmpModel login(String userName,String pwd);
}
