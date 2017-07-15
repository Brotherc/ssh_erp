package cn.brotherChun.erp.util.intercept;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginIntercept extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String actionName=invocation.getProxy().getAction().getClass().getName();
		String methodName=invocation.getProxy().getMethod();
		String allName=actionName+"."+methodName;
		if("cn.brotherChun.erp.auth.emp.action.EmpAction.login".equals(allName)){
			return invocation.invoke();
		}
		EmpModel temp = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_OBJECT_NAME);
		if(temp==null)
			return "noLogin";
		return invocation.invoke();
	}

}
