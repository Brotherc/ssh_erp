package cn.brotherChun.erp.auth.menu.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.brotherChun.erp.auth.menu.business.ebi.MenuEbi;
import cn.brotherChun.erp.auth.menu.vo.MenuModel;
import cn.brotherChun.erp.auth.menu.vo.MenuQueryModel;
import cn.brotherChun.erp.auth.role.business.ebi.RoleEbi;
import cn.brotherChun.erp.auth.role.vo.RoleModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class MenuAction extends BaseAction{
	
	public MenuModel mm = new MenuModel();
	public MenuQueryModel mqm = new MenuQueryModel();

	private RoleEbi roleEbi;
	private MenuEbi menuEbi;
	
	public Long[] roleUuids;
	
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	//列表
	public String list(){
		setDataTotal(menuEbi.getCount(mqm));
		
		List<MenuModel> temp = menuEbi.getAllOneLevel();
		put("parentMenuList", temp);
		
		List<MenuModel> menuList = menuEbi.getAll(mqm, pageNum, pageCount);
		put("menuList", menuList);

		return LIST;
	}

	//到添加
	public String input(){
		
		List<RoleModel> roleTemp = roleEbi.getAll();
		put("roleList", roleTemp);
		
		List<MenuModel> temp = menuEbi.getAllOneLevel();
		put("parentMenuList", temp);
		
		if(mm.getUuid()!=null){
			mm = menuEbi.get(mm.getUuid());
			
			Set<RoleModel> roles = mm.getRoles();
			roleUuids=new Long[roles.size()];
			int i=0;
			for(RoleModel role:roles){
				roleUuids[i++]=role.getUuid();
			}
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(mm.getUuid() == null){
			menuEbi.save(mm,roleUuids);
		}else{
			menuEbi.update(mm,roleUuids);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		menuEbi.delete(mm);
		return TO_LIST;
	}

	public void showMenu() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String root=request.getParameter("root");
		
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		System.out.println(root);
		if("source".equals(root)){
			List<MenuModel> menuTemp=menuEbi.getAllOneLevel2(getLogin().getUuid());
			for(MenuModel menu:menuTemp){
				sb.append("{\"text\": \"");
				sb.append(menu.getName());
				sb.append("\",\"classes\": \"folder\",\"hasChildren\": true,\"id\":");
				sb.append(menu.getUuid().toString());
				sb.append("},");
			}
		}else{
			List<MenuModel> menuTemp=menuEbi.getAllTwoLevel(getLogin().getUuid(),new Long(root));
			System.out.println(menuTemp.size());
			for(MenuModel menu:menuTemp){
				sb.append("{\"text\": \"<a class='hei' target='main' href='");
				sb.append(menu.getUrl());
				sb.append("'>&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append(menu.getName());
				sb.append("</a>\",\"classes\": \"file\",\"hasChildren\": false},");
			}
		}
		
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		
		pw.write(sb.toString());
		pw.flush();
		pw.close();
	}
}
