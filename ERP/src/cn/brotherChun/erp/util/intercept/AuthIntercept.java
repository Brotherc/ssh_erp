package cn.brotherChun.erp.util.intercept;

import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.util.exception.AppException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

//权限校验
public class AuthIntercept extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//1.获取本次操作



		
		String actionName=invocation.getProxy().getAction().getClass().getName();
		String methodName=invocation.getProxy().getMethod();
		String allName=actionName+"."+methodName;
		
		//全资源加载是不区分登录用户都要进行的操作
		//所有用户使用的该数据都相同
		//改良方案：将该数据提交获取完毕，放入指定范围，使用时直接获取
		//查询时机：服务器启动时完成————监听器
		//范围：ServletContext
		
		ServletContext context = ServletActionContext.getServletContext();
		String resAll = context.getAttribute("resAll").toString();
		//2.判断本次操作是否是被拦截操作
		if(!resAll.contains(allName)){
			return invocation.invoke();
		}
		
		//3.从session中获取当前登录人信息
		EmpModel emp=(EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_OBJECT_NAME);
			
		//4.获取当前登录人可执行的所有操作（资源-角色-员工）
		String resEmp = emp.getResAll();
		//5.判断当前登录人对应的所有可操作资源中是否包含有本次操作
			if(resEmp.contains(allName)){
				return invocation.invoke();
			}

		
		throw new AppException("对不起，您没有访问权限，请不要非法操作");
	}

}
