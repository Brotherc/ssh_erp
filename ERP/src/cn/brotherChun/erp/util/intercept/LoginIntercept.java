package cn.brotherChun.erp.util.intercept;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginIntercept extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//执行除了登录操作之前的所有操作做登录校验
		//获取本次操作的信息
		String actionName=invocation.getProxy().getAction().getClass().getName();
		String methodName=invocation.getProxy().getMethod();
		String allName=actionName+"."+methodName;
		
		String operName=invocation.getProxy().getActionName();
		if("page_login".equals(operName)){
			return invocation.invoke();
		}
		
		if("cn.brotherChun.erp.auth.emp.action.EmpAction.login".equals(allName)){
			return invocation.invoke();
		}
		//获取当前登录人信息
		EmpModel temp = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_OBJECT_NAME);
		//如果当前没有登录，跳转到登录页面
		if(temp==null)
			return "noLogin";
		//执行原始操作
		return invocation.invoke();
	}

}
