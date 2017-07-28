package cn.brotherChun.erp.util.intercept;

import cn.brotherChun.erp.util.exception.AppException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionIntercept extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (AppException e) {
			//记录日志
			//发送日志到程序员邮箱
			//报警
			ActionSupport as = (ActionSupport) invocation.getAction();
			as.addActionError(as.getText(e.getMessage()));
			return "error";
		}catch (Exception e) {
			
			//上线期
			//ActionSupport as = (ActionSupport) invocation.getAction();
			//as.addActionError("对不起，服务器已关闭，请联系管理员!");
			//return "error";
			
			//记录日志
			//发送日志到程序员邮箱
			//报警
			
			//开发期
			e.printStackTrace();
			return invocation.invoke();
		}
		
	}

}
