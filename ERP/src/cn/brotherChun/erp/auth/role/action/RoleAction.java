package cn.brotherChun.erp.auth.role.action;

import java.util.List;
import java.util.Set;

import cn.brotherChun.erp.auth.menu.business.ebi.MenuEbi;
import cn.brotherChun.erp.auth.menu.vo.MenuModel;
import cn.brotherChun.erp.auth.res.business.ebi.ResEbi;
import cn.brotherChun.erp.auth.res.vo.ResModel;
import cn.brotherChun.erp.auth.role.business.ebi.RoleEbi;
import cn.brotherChun.erp.auth.role.vo.RoleModel;
import cn.brotherChun.erp.auth.role.vo.RoleQueryModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class RoleAction extends BaseAction {
	
	private ResEbi resEbi;
	private RoleEbi roleEbi;
	private MenuEbi menuEbi;
	public RoleModel role=new RoleModel();
	public RoleQueryModel roleQ=new RoleQueryModel();
	
	public Long[] reses;
	public Long[] menus;
	
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}
	public String list(){
		setDataTotal(roleEbi.getCount(roleQ));
		List<RoleModel> temp = roleEbi.getAll(roleQ, pageNum, pageCount);
		put("roleList", temp);
		return LIST;
	}
	public String input(){
		List<ResModel> temp = resEbi.getAll();
		put("resList", temp);
		List<MenuModel> menuTemp = menuEbi.getAll();
		put("menuList", menuTemp);
		
		if(role.getUuid()!=null){
			role=roleEbi.get(role.getUuid());
			
			Set<ResModel> setRes = role.getReses();
			reses=new Long[setRes.size()];
			int i=0;
			for(ResModel res : setRes){
				reses[i++]=res.getUuid();
			}
			
			Set<MenuModel> setMenu = role.getMenus();
			menus=new Long[setMenu.size()];
			int j=0;
			for(MenuModel menu : setMenu){
				menus[j++]=menu.getUuid();
			}
		}	
		return INPUT;
	}
	public String save(){
		if(role.getUuid()!=null)
			roleEbi.update(role,reses,menus);
		else 
			roleEbi.save(role,reses,menus);
		return TO_LIST;
	}
	public String delete(){
		roleEbi.delete(role);
		return TO_LIST;
	}
	public Long[] getReses(){
		return reses;
	}
}
