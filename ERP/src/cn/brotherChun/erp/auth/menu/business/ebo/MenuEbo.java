package cn.brotherChun.erp.auth.menu.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.brotherChun.erp.auth.menu.business.ebi.MenuEbi;
import cn.brotherChun.erp.auth.menu.dao.dao.MenuDao;
import cn.brotherChun.erp.auth.menu.vo.MenuModel;
import cn.brotherChun.erp.auth.menu.vo.MenuQueryModel;
import cn.brotherChun.erp.auth.role.vo.RoleModel;

public class MenuEbo implements MenuEbi{
	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	//废弃
	public void save(MenuModel mm) {
//		menuDao.add(mm);
	}

	//废弃
	public void update(MenuModel mm) {
/*		MenuModel temp = menuDao.get(mm.getUuid());
		temp.setUrl(mm.getUrl());
		temp.setName(mm.getName());*/
	}

	public void delete(MenuModel mm) {
		MenuModel temp = menuDao.get(mm.getUuid());
		menuDao.delete(temp);
	}

	public MenuModel get(Serializable uuid) {
		return menuDao.get(uuid);
	}

	public List<MenuModel> getAll() {
		return menuDao.getAll();
	}

	public List<MenuModel> getAll(MenuQueryModel q, Integer pageNum,
			Integer pageCount) {
		return menuDao.getAll(q, pageNum, pageCount);
	}

	public Integer getCount(MenuQueryModel q) {
		return menuDao.getCount(q);
	}

	public List<MenuModel> getAllOneLevel() {
		return menuDao.getByPuuidIsOneOrZero();
	}

	public void save(MenuModel mm, Long[] roleUuids) {
		Set<RoleModel> roles=new HashSet<RoleModel>();
		for(Long uuid:roleUuids){
			RoleModel role=new RoleModel();
			role.setUuid(uuid);
			roles.add(role);
		}
		mm.setRoles(roles);
		menuDao.add(mm);
	}

	public void update(MenuModel mm, Long[] roleUuids) {
		MenuModel temp = menuDao.get(mm.getUuid());
		
		Set<RoleModel> roles=new HashSet<RoleModel>();
		for(Long uuid:roleUuids){
			RoleModel role=new RoleModel();
			role.setUuid(uuid);
			roles.add(role);
		}
		
		temp.setRoles(roles);
		temp.setUrl(mm.getUrl());
		temp.setName(mm.getName());
	}

	public List<MenuModel> getAllOneLevel2(Long uuid) {
		return menuDao.getAllByPuuidAndEmp(uuid);
	}

	public List<MenuModel> getAllTwoLevel(Long uuid, Long puuid) {
		return menuDao.getAllTwoLevelByEmpAndPuuid(uuid,puuid);
	}

}
