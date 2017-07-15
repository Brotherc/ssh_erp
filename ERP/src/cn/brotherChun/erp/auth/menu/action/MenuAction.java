package cn.brotherChun.erp.auth.menu.action;

import java.util.ArrayList;
import java.util.List;

import cn.brotherChun.erp.auth.menu.business.ebi.MenuEbi;
import cn.brotherChun.erp.auth.menu.vo.MenuModel;
import cn.brotherChun.erp.auth.menu.vo.MenuQueryModel;
import cn.brotherChun.erp.util.base.BaseAction;

public class MenuAction extends BaseAction{
	
	public MenuModel mm = new MenuModel();
	public MenuQueryModel mqm = new MenuQueryModel();

	
	private MenuEbi menuEbi;
	
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
		List<MenuModel> temp = menuEbi.getAllOneLevel();
		put("parentMenuList", temp);
		if(mm.getUuid()!=null){
			mm = menuEbi.get(mm.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(mm.getUuid() == null){
			menuEbi.save(mm);
		}else{
			menuEbi.update(mm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		menuEbi.delete(mm);
		return TO_LIST;
	}

}
