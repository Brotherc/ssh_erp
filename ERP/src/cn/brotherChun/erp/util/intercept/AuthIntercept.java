package cn.brotherChun.erp.util.intercept;

import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.util.exception.AppException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthIntercept extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String actionName=invocation.getProxy().getAction().getClass().getName();
		String methodName=invocation.getProxy().getMethod();
		String allName=actionName+"."+methodName;
		
		ServletContext context = ServletActionContext.getServletContext();
		String resAll = context.getAttribute("resAll").toString();
		if(!resAll.contains(allName)){
			return invocation.invoke();
		}
		
		EmpModel emp=(EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_OBJECT_NAME);
			
		
		String resEmp = emp.getResAll();

			if(resEmp.contains(allName)){
				return invocation.invoke();
			}

		
		throw new AppException("对不起，您没有访问权限，请不要非法操作");
	}

}
