package cn.brotherChun.erp.auth.emp.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.brotherChun.erp.auth.emp.business.ebi.EmpEbi;
import cn.brotherChun.erp.auth.emp.vo.EmpModel;

public class EmpAction extends ActionSupport {
	
	public EmpModel emp=new EmpModel();
	private EmpEbi empEbi;

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	
	//页面收集用户名，密码信息
	//将数据传递到业务层
	//业务层转到数据层
	//进行业务查询，查询结果返回
	//判断查询结果，如果查询到
	//登录成功，否则登录失败
	public String login(){
		EmpModel loginEmp=empEbi.login(emp.getUserName(),emp.getPwd());
		if(loginEmp==null){
			this.addActionError("对不起，用户名密码错误！");
			return "loginFail";
		}
		else {
			ActionContext.getContext().getSession().put(EmpModel.EMP_OBJECT_NAME, loginEmp);
			return "loginSuccess";
		}
	}
}
