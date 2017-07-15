package cn.brotherChun.erp.auth.menu.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.brotherChun.erp.auth.menu.business.ebi.MenuEbi;
import cn.brotherChun.erp.auth.menu.dao.dao.MenuDao;
import cn.brotherChun.erp.auth.menu.vo.MenuModel;
import cn.brotherChun.erp.auth.menu.vo.MenuQueryModel;

public class MenuEbo implements MenuEbi{
	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void save(MenuModel mm) {
		menuDao.add(mm);
	}

	public void update(MenuModel mm) {
		MenuModel temp = menuDao.get(mm.getUuid());
		temp.setUrl(mm.getUrl());
		temp.setName(mm.getName());
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

}
