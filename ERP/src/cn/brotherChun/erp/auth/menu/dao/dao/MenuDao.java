package cn.brotherChun.erp.auth.menu.dao.dao;

import java.util.List;

import cn.brotherChun.erp.auth.menu.vo.MenuModel;
import cn.brotherChun.erp.util.base.BaseDao;

public interface MenuDao extends BaseDao<MenuModel> {

	public List<MenuModel> getByPuuidIsOneOrZero();

}
