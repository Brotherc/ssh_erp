package cn.brotherChun.erp.util.base;

import cn.brotherChun.erp.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	//定义页面跳转常量
	protected static final String LIST="list";
	protected static final String TO_LIST="toList";
	protected static final String INPUT="input";
	//定义分页使用的常量
	public Integer pageNum=1;
	public Integer pageCount=10;
	public Integer maxPageNum;
	public Integer dataTotal;
	
	protected String actionName;
	
	{
		String temp=getClass().toString();
		temp=temp.substring(temp.lastIndexOf(".")+1);
		temp=temp.substring(0, temp.length()-6);
		temp=(char)(temp.charAt(0)+32)+temp.substring(1);
		actionName=temp;
	}
	
	public String getActionName() {
		return actionName;
	}
	//存放数据
	protected void put(String key,Object value){
		ActionContext.getContext().put(key, value);
	}
	protected void putSession(String key,Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}
	protected Object getSession(String key) {
		return ActionContext.getContext().getSession().get(key);
	}
	protected EmpModel getLogin(){
		return (EmpModel) getSession(EmpModel.EMP_OBJECT_NAME);
	}
	//分页数据初始化
	protected void setDataTotal(Integer dataInteger) {
		this.dataTotal=dataInteger;
		pageCount=10;
		maxPageNum=(dataTotal+this.pageCount-1)/pageCount;
		//兼容页码值初始化错误
		if(pageNum<1) pageNum=1;
		if(pageNum>maxPageNum)pageNum=maxPageNum;
	}
}
