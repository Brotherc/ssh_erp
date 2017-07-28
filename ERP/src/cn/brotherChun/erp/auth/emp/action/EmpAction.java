package cn.brotherChun.erp.auth.emp.action;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.brotherChun.erp.auth.dep.business.ebi.DepEbi;
import cn.brotherChun.erp.auth.dep.vo.DepModel;
import cn.brotherChun.erp.auth.emp.business.ebi.EmpEbi;
import cn.brotherChun.erp.auth.emp.vo.EmpModel;
import cn.brotherChun.erp.auth.emp.vo.EmpQueryModel;
import cn.brotherChun.erp.auth.res.business.ebi.ResEbi;
import cn.brotherChun.erp.auth.res.vo.ResModel;
import cn.brotherChun.erp.auth.role.business.ebi.RoleEbi;
import cn.brotherChun.erp.auth.role.vo.RoleModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class EmpAction extends BaseAction {
	
	public EmpQueryModel eqm=new EmpQueryModel();
	public EmpModel emp=new EmpModel();
	
	private EmpEbi empEbi;
	private DepEbi depEbi;
	private RoleEbi roleEbi;
	private ResEbi resEbi;
	
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}

	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	//列表
	public String list(){
		//加载所有部门的信息数据
		List<DepModel> depTemp = depEbi.getAll();
		put("depList", depTemp);
		setDataTotal(empEbi.getCount(eqm));
		//根据查询条件获取数据(查询条件封装在depQ对象中)
		List<EmpModel> temp=empEbi.getAll(eqm,pageNum,pageCount);
		//放入指定范围
		put("empList", temp);
		//跳转页面
		return LIST;
	}
	
	public Long[] roles;
	//跳转到添加页面
	public String input(){
		//加载所有角色信息数据
		List<RoleModel> roleTemp = roleEbi.getAll();
		put("roleList", roleTemp);
		//加载所有部门信息数据
		List<DepModel> depTemp = depEbi.getAll();
		put("depList", depTemp);
		
		if(emp.getUuid()!=null){
			emp=empEbi.get(emp.getUuid());
			Set<RoleModel> setRole = emp.getRoles();
			//此时roles中无数据，必须对其进行初始化才可以进行数据回显
			roles=new Long[setRole.size()];
			//set->array
			int i=0;
			for(RoleModel role:setRole){
				roles[i++]=role.getUuid();
			}
		}
		return INPUT;
	}
	//添加功能
	public String save(){
		//根据页面传递的参数判断当前操作时添加还是修改，依据是否提供emp.uuid
		if(emp.getUuid()==null){
			//添加功能
			//将收集的值传递到业务层，完成保存功能
			empEbi.save(emp,roles);		
		}else {
			//修改功能
			empEbi.update(emp,roles);
		}

		return TO_LIST;
	}
	
	//登出/注销
	public String logout(){
		//1.获得session.removeAtrribute("name");
		//2.所谓登录失败指loginEm == null,setAttribute("name",null)
		putSession(EmpModel.EMP_OBJECT_NAME, null);
		return "noLogin";
	}
	//跳转到修改密码
	public String toChangePwd(){
		return "toChangePwd";
	}
	
	public String newPwd;
	
	public String changePwd(){
		System.out.println(newPwd+" "+getLogin().getUserName()+getLogin().getPwd());
		//执行update ...  set pwd = newPwd where userName = session[userName] and pwd = em.pwd
		Boolean flag=empEbi.changePwd(getLogin().getUserName(),getLogin().getPwd(),newPwd);
		if(flag){
			//修改成功
			//重新登录
			return logout();
		}else{
			//提示用户当前信息输入有误
			//信息：自己处理
			return toChangePwd();
		}
	}
	

	//页面收集用户名，密码信息
	//将数据传递到业务层
	//业务层转到数据层
	//进行业务查询，查询结果返回
	//判断查询结果，如果查询到
	//登录成功，否则登录失败
	public String login(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String loginIp = request.getHeader("x-forwarded-for"); 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getRemoteAddr(); 
		}

		EmpModel loginEmp=empEbi.login(emp.getUserName(),emp.getPwd(),loginIp);
		if(loginEmp==null){
			this.addActionError("对不起，用户名密码错误！");
			return "loginFail";
		}
		else {
			//加载当前登录人对应的所有可操作资源信息
			List<ResModel> resTemp = resEbi.getAllByEmpUuid(loginEmp.getUuid());
			StringBuilder sb=new StringBuilder();
			for(ResModel res:resTemp){
				sb.append(res.getUrl());
				sb.append(",");
			}
			loginEmp.setResAll(sb.toString());
			putSession(EmpModel.EMP_OBJECT_NAME, loginEmp);
			return "loginSuccess";
		}
	}
}
