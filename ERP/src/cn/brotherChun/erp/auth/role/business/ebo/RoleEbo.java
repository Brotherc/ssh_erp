package cn.brotherChun.erp.auth.role.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.brotherChun.erp.auth.menu.vo.MenuModel;
import cn.brotherChun.erp.auth.res.vo.ResModel;
import cn.brotherChun.erp.auth.role.business.ebi.RoleEbi;
import cn.brotherChun.erp.auth.role.dao.dao.RoleDao;
import cn.brotherChun.erp.auth.role.vo.RoleModel;
import cn.brotherChun.erp.auth.role.vo.RoleQueryModel;

public class RoleEbo implements RoleEbi{

	private RoleDao roleDao;
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public void save(RoleModel t) {
		roleDao.add(t);
	}

	public void delete(RoleModel t) {
		roleDao.delete(t);
	}

	//废弃
	public void update(RoleModel t) {
/*		RoleModel role = roleDao.get(t.getUuid());
		role.setName(t.getName());
		role.setCode(t.getCode());*/
	}

	public RoleModel get(Serializable uuid) {
		return roleDao.get(uuid);
	}

	public List<RoleModel> getAll() {
		return roleDao.getAll();
	}

	public List<RoleModel> getAll(RoleQueryModel q, Integer pageNum,
			Integer pageCount) {
		return roleDao.getAll(q, pageNum, pageCount);
	}

	public Integer getCount(RoleQueryModel q) {
		return roleDao.getCount(q);
	}

	//废弃
	public void save(RoleModel role, Long[] reses) {
/*		Set<ResModel> resSet =new HashSet<ResModel>();
		for (Long res : reses) {
			ResModel temp=new ResModel();
			temp.setUuid(res);
			resSet.add(temp);
		}
		role.setReses(resSet);
		save(role);*/
	}

	//废弃
	public void update(RoleModel t, Long[] reses) {
		
/*		RoleModel role = roleDao.get(t.getUuid());
		role.setName(t.getName());
		role.setCode(t.getCode());
		
		Set<ResModel> resSet=new HashSet<ResModel>();
		for(Long uuid:reses){
			ResModel res=new ResModel();
			res.setUuid(uuid);
			resSet.add(res);
		}
		role.setReses(resSet);*/
	}

	public void update(RoleModel t, Long[] reses, Long[] menus) {
		
		RoleModel role = roleDao.get(t.getUuid());
		role.setName(t.getName());
		role.setCode(t.getCode());
		
		Set<ResModel> resSet=new HashSet<ResModel>();
		for(Long uuid:reses){
			ResModel res=new ResModel();
			res.setUuid(uuid);
			resSet.add(res);
		}
		
		Set<MenuModel> menuSet=new HashSet<MenuModel>();
		for(Long uuid:menus){
			MenuModel menu=new MenuModel();
			menu.setUuid(uuid);
			menuSet.add(menu);
		}
		role.setReses(resSet);
		role.setMenus(menuSet);
	}

	public void save(RoleModel role, Long[] reses, Long[] menus) {
		Set<ResModel> resSet =new HashSet<ResModel>();
		for (Long res : reses) {
			ResModel temp=new ResModel();
			temp.setUuid(res);
			resSet.add(temp);
		}
		role.setReses(resSet);
		
		Set<MenuModel> menuSet =new HashSet<MenuModel>();
		for (Long uuid : menus) {
			MenuModel temp=new MenuModel();
			temp.setUuid(uuid);
			menuSet.add(temp);
		}
		role.setMenus(menuSet);
		
		save(role);
	}

}
